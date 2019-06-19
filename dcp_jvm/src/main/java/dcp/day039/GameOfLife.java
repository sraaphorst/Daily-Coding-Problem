// GameOfLife.java
//
// By Sebastian Raaphorst, 2019.

package dcp.day039;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public final class GameOfLife extends JFrame {
    /**
     * A canvas to display the current game of board state.
     */
    private static class LifeCanvas extends JPanel {
        // The size in pixels of a cell.
        private final static int SIZE = 6;

        // The reference to the board.
        HashSet<Cell> board;

        /**
         * OVerride the component painting to draw the cells.
         */
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final Graphics2D g2d = (Graphics2D) g;

            // Get the size and the center.
            final var size = getSize();
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, size.width, size.height);

            // We want (0, 0) at the center.
            final var cx = (size.width - SIZE) / 2;
            final var cy = (size.height - SIZE) / 2;

            // Draw all of the living cells, treating the center of the window as the (0, 0) point.
            g2d.setColor(Color.yellow);
            board.forEach(p -> g2d.fillRect(p.x * SIZE + cx, p.y * SIZE + cy, SIZE, SIZE));
        }

        /**
         * For a board, trigger a repaint.
         */
        void draw(final HashSet<Cell> board) {
            this.board = board;
            repaint();
        }
    }


    /**
     * A representation of a cell, which includes the (x,y) coordinate.
     */
    private final static class Cell {
        final int x;
        final int y;

        Cell(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Cell)) return false;
            final Cell p = (Cell) obj;
            return p.x == x && p.y == y;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }

        /**
         * Comparators to fetch the maximum and minimum x and y coordinates of the cells,
         * so that we can determine the size of the board.
         */
        static final Comparator<Cell> X_COMPARATOR = Comparator.comparingInt(p -> p.x);
        static final Comparator<Cell> Y_COMPARATOR = Comparator.comparingInt(p -> p.y);

        // A cell representing zero.
        static final Cell ZERO = new Cell(0, 0);
    }

    private final LifeCanvas canvas;
    private HashSet<Cell> board;

    /**
     * Conway's Game of Life, with a Swing GUI impmlementation.
     * @param board the initial configuration of living points.
     * @param speed the number of milliseconcs between updates.
     */
    public GameOfLife(final HashSet<Cell> board, int speed) {
        super("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.board = board;

        canvas = new LifeCanvas();
        add(canvas);

        // Repaint every speed ms. We begin with a call to tick() to draw the original configuration.
        tick();
        new Timer(speed, evt -> tick()).start();
    }

    /**
     * Perform a single tick, i.e. time notices.
     */
    private void tick() {
        canvas.draw(board);
        canvas.repaint();
        board = calculateNextBoard();
        getContentPane().repaint();
        repaint();
    }

    /**
     * A static method to count the number of living neighbours of a cell in a board.
     * @param board the board.
     * @param cell the cell whose neighbours to count.
     * @return
     */
    private static int countNeighbours(final HashSet<Cell> board, final Cell cell) {
        var nbrs = 0;
        for (var deltax = -1; deltax <= 1; ++deltax)
            for (var deltay = -1; deltay <= 1; ++deltay)
                // Something cannot be a neighbour to itself.
                if ((deltax != 0 || deltay != 0) && board.contains(new Cell(cell.x + deltax, cell.y + deltay)))
                    ++nbrs;
        return nbrs;
    }

    private HashSet<Cell> calculateNextBoard() {
        final var next_board = new HashSet<Cell>();

        // The next_board can extend or shrink only by 1 in each cardinal direction, so determine the min / max keys.
        final var maxx = board.stream().max(Cell.X_COMPARATOR).orElse(Cell.ZERO).x;
        final var minx = board.stream().min(Cell.X_COMPARATOR).orElse(Cell.ZERO).x;
        final var maxy = board.stream().max(Cell.Y_COMPARATOR).orElse(Cell.ZERO).y;
        final var miny = board.stream().min(Cell.Y_COMPARATOR).orElse(Cell.ZERO).y;
        for (var x = minx - 1; x <= maxx + 1; ++x) {
            for (var y = miny - 1; y <= maxy + 1; ++y) {
                final Cell pair = new Cell(x, y);
                final int nbrs = countNeighbours(board, pair);
                final boolean alive = board.contains(pair);

                // 1. If live cell has less than two neighbours, dies.
                //    Nothing to do here.
                // 2. If 2-3 nbrs and alive, live.
                if (alive && (nbrs ==2 || nbrs == 3))
                    next_board.add(new Cell(x, y));
                // 3. Any live cell with more than three neighbours dies.
                //    Nothing to do here.
                // 4. Dead cell with at most three neighbours is born.
                if (!alive && nbrs == 3)
                    next_board.add(new Cell(x, y));
            }
        }

        return next_board;
    }

    public static void main(final String[] args) {

        // Determine the range of the visible points.
        final var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension canvasSize = new Dimension(screenSize.width - 100, screenSize.height - 100);
        final var blockWidth = screenSize.width / LifeCanvas.SIZE - 50;
        final var blockHeight = screenSize.height / LifeCanvas.SIZE - 50;

        // Create a board and add points with given probability.
        final var board = new HashSet<Cell>();
        final Random r = new Random();
        for (var i = (-blockWidth/2); i < blockWidth/2; ++i)
            for (var j = (-blockHeight/2); j < blockHeight/2; ++j)
                if (r.nextDouble() < 0.5)
                    board.add(new Cell(i, j));

        final var gameOfLife = new GameOfLife(board, 50);
        gameOfLife.setSize(canvasSize);
        gameOfLife.setMinimumSize(canvasSize);
        gameOfLife.setPreferredSize(canvasSize);

        // Centre the window.
        final int xPos = (screenSize.width  - gameOfLife.getWidth())  / 2;
        final int yPos = (screenSize.height - gameOfLife.getHeight()) / 2;
        gameOfLife.setLocation(xPos, yPos);

        gameOfLife.setVisible(true);
    }
}
