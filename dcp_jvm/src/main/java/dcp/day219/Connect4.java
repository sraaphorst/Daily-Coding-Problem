package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.List;

final public class Connect4 {

    static final int ROWS = 6;
    static final int COLS = 7;

    private final Connect4Model model;
    private final Connect4View view;
    private Player player;

    public Connect4() {
        model = new Connect4Model();
        view = new Connect4View();
        player = Player.BLUE;

        // Hook up the headers to the controllers.
        view.header.forEach(comp -> {
            // Convert the header to a column position.
            final OptionalInt colIdx = view.headerToColumn(comp);
            if (colIdx.isPresent()) System.out.println("PRESENT");
            else System.out.println("NOT PRESENT");

            // Hook up the button to invoke the controls for the column.
            colIdx.ifPresent(col -> ((JButton) comp).addActionListener(evt -> {
                        System.out.println("Clicked " + col);

                        // Set the model.
                        OptionalInt rowIdx = model.addToColumn(player, col);
                        rowIdx.ifPresent(row -> {
                            System.out.println("Setting colour of (" + row + "," + col + ") to " + player);
                            view.setToTile(player, row, col);

                            // Check if there is a win.
                            checkWinner(row, col).ifPresent(w -> {
                                for (Position p: w.positions)
                                    view.setToTile(Player.WINNER, p.row, p.column);
                                JOptionPane.showMessageDialog(null, "Winner!");
                                System.exit(0);
                            });

                            // Swap players.
                            swapPlayer();
                        });
                    }
            ));
        });
    }

    void swapPlayer() {
        player = player == Player.BLUE ? Player.YELLOW : Player.BLUE;
    }

    Player p(int r, int c) {
        return model.board.get(r).get(c);
    }

    // Check for a winner that passes through position (row, column).
    Optional<Winner> checkWinner(int row, int column) {
        // The potential winner.
        final Player winner = p(row, column);

        // Check horizonal line.
        List<Position> positions = new ArrayList<>(4);
        for (int c = 0; c < COLS; ++c) {
            if (p(row, c) != winner)
                positions.clear();
            else if (p(row, c) == winner)
                positions.add(new Position(row, c));
            if (positions.size() == 4)
                return Optional.of(new Winner(winner, positions));
        }

        // Check vertical line.
        positions.clear();
        for (int r = 0; r < ROWS; ++ r) {
            if (p(r, column) != winner)
                positions.clear();
            else if (p(r, column) == winner)
                positions.add(new Position(r, column));
            if (positions.size() == 4)
                return Optional.of(new Winner(winner, positions));
        }

        // Check diagonals. This is more difficult. We want to hit the wall where one point is 0.
        // Begin with \. Start at (row, column) and move (-1, -1) until one position is 0.
        int dr = row;
        int dc = column;
        while (dr > 0 && dc > 0) {
            --dr; --dc;
        }

        // Now traverse in the (1, 1) direction, collecting points as we did above.
        positions.clear();
        for (int d = 0; (dr + d) < ROWS && (dc + d) < COLS; ++d) {
            if (p(dr + d, dc + d) != winner)
                positions.clear();
            else if (p(dr + d, dc + d) == winner)
                positions.add(new Position(dr + d, dc + d));
            if (positions.size() == 4)
                return Optional.of(new Winner(winner, positions));
        }

        // Check the other diagonal.
        // Begin with /. Start at (row, column) and move (-1, 1) until we hit a row 5 or a column 6.
        dr = row;
        dc = column;
        while (dr > 0 && dc < COLS-1) {
            --dr; ++dc;
        }
        positions.clear();
        for (int d = 0; (dr + d) < ROWS && (dc - d) >= 0; ++d) {
            System.out.println("d=" + d + " dr = " + dr + " dc = " + dc);
            if (p(dr + d, dc - d) != winner)
                positions.clear();
            else if (p(dr + d, dc - d) == winner)
                positions.add(new Position(dr + d, dc - d));
            if (positions.size() == 4)
                return Optional.of(new Winner(winner, positions));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Connect 4");
        //frame.setSize(300, 300);
        //final JLabel label = new JLabel("Label");
        //frame.add(label);
        //label.setBorder(BorderFactory.createLoweredBevelBorder());
        //label.setSize(200, 200);
        //final Connect4View view = new Connect4View();
        final Connect4 connect4 = new Connect4();
        frame.setLayout(new BorderLayout());
        frame.add(connect4.view, BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}