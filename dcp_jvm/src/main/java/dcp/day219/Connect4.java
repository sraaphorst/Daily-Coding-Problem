package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.List;

/**
 * The controller and main app for Connect4.
 */
final public class Connect4 implements Connect4Constants{

    private final Connect4Model model;
    private final Connect4View view;
    private Player player;

    private Connect4() {
        model = new Connect4Model();
        view = new Connect4View();
        player = Player.BLUE;

        // Hook up the headers to the controllers.
        view.header.forEach(comp -> {
            // Convert the header to a column position.
            // Hook up the button to invoke the controls for the column.
            view.headerToColumn(comp).
                    ifPresent(col -> ((JButton) comp).addActionListener(evt -> {
                        // Set the model.
                        OptionalInt rowIdx = model.addToColumn(player, col);
                        rowIdx.ifPresent(row -> {
                            view.setTileToPlayer(player, row, col);

                            // Check if there is a win.
                            checkWinner(row, col).ifPresent(w -> {
                                for (Position p: w.positions)
                                    view.setTileToPlayer(Player.WINNER, p.row, p.column);
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

    /**
     * Swap the players.
     */
    private void swapPlayer() {
        player = player == Player.BLUE ? Player.YELLOW : Player.BLUE;
    }

    /**
     * Shorthand to get the player at position (r, c).
     */
    private Player p(int r, int c) {
        return model.board.get(r).get(c);
    }

    /**
     * Determine if a player has won, and if so, return the Winner information.
     */
    // Check for a winner that passes through position (row, column).
    private Optional<Winner> checkWinner(int row, int column) {
        // The potential winner.
        final Player winner = p(row, column);

        // Check horizonal line.
        final List<Position> positions = new ArrayList<>(4);
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        final Connect4 connect4 = new Connect4();
        frame.add(connect4.view, BorderLayout.CENTER);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }
}