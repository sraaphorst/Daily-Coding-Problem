package dcp.day219;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

class Connect4Model {
    // The contents of the tile.
    static int ROWS = 6;
    static int COLS = 7;

    List<List<Player>> board;

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
        // Start at bottom, work to top.
        for (int row = ROWS-1; row >= 0; --row) {
            if (board.get(row).get(column) == Player.UNVISISTED) {
                board.get(row).set(column, player);
                return OptionalInt.of(row);
            }
        }
        return OptionalInt.empty();
    }
}
