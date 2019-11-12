package dcp.day219;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

class Connect4Model {
    // The contents of the tile.
    static int ROWS = 6;
    static int COLS = 7;

    private List<List<Tile>> tiles;

    Connect4Model() {
        // Initialize all to unvisisted.
        tiles = new ArrayList<>(ROWS);
        for (int i = 0; i < ROWS; ++i) {
            List<Tile> row = new ArrayList<>(COLS);
            for (int j = 0; j < COLS; ++j)
                row.add(Tile.GREEN);
            tiles.add(row);
        }
    }

    /**
     * Given a column, try to add a tile to it by sliding it down.
     */
    OptionalInt addToColumn(Tile tile, int column) {
        System.out.println("\n*** MODEL addToColumn + " + tile.getColor() + " c=" + column + " ***");
        System.out.println("Trying to add to column " + column);
        // Start at bottom, work to top.
        System.out.println("Empty: ");
        for (int row = ROWS-1; row > 0; --row) {
            if (tiles.get(row).get(column) == Tile.GREEN) System.out.print(row + " ");
        }
        System.out.println();

        for (int row = ROWS-1; row > 0; --row) {
            System.out.println("Checking if row " + row + " is empty: " + (tiles.get(row).get(column) == Tile.GREEN));
            if (tiles.get(row).get(column) == Tile.GREEN) {
                System.out.println("addToColumn: Setting tile (" + row + "," + column + ") to " + tile);
                tiles.get(row).set(column, tile);
                System.out.println("Returning " + row);
                return OptionalInt.of(row);
            }
        }
        return OptionalInt.empty();
    }
}
