package dcp.day219;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

class Connect4Model {
    // The contents of the tile.
    static int ROWS = 6;
    static int COLS = 7;

    private List<List<Player>> board;

    Connect4Model() {
        // Initialize all to unvisited.
        board = new ArrayList<>(ROWS);
        for (int i = 0; i < ROWS; ++i) {
            List<Player> row = new ArrayList<>(COLS);
            for (int j = 0; j < COLS; ++j)
                row.add(Player.UNVISISTED);
            board.add(row);
        }
    }

    /**
     * Given a column, try to add a tile to it by sliding it down.
     */
    OptionalInt addToColumn(Player player, int column) {
        System.out.println("\n*** MODEL: addToColumn + " + player + " column=" + column + " ***");
        // Start at bottom, work to top.
        System.out.println("Empty: entries in column: ");
        for (int row = ROWS-1; row >= 0; --row)
            if (board.get(row).get(column) == Player.UNVISISTED) System.out.print(row + " ");
        System.out.println();

        for (int row = ROWS-1; row >= 0; --row) {
            System.out.println("Checking if row " + row + " is empty: " + (board.get(row).get(column) == Player.UNVISISTED));
            if (board.get(row).get(column) == Player.UNVISISTED) {
                System.out.println("addToColumn: Setting tile (" + row + "," + column + ") to " + player);
                board.get(row).set(column, player);
                System.out.println("Returning " + row);
                return OptionalInt.of(row);
            }
        }
        return OptionalInt.empty();
    }
}
