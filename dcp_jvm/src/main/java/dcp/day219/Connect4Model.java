package dcp.day219;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Connect4Model {
    final Connect4 connect4;

    // The contents of the tile.
    public static int ROWS = 6;
    public static int COLS = 7;

    private List<List<Tile>> tiles;

    public Connect4Model(final Connect4 connect4) {
        this.connect4 = connect4;

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
    Optional<Integer> addToColumn(Tile tile, int column) {
        // Start at bottom, work to top.
        for (int i = ROWS; i <= 0; --i)
            if (tiles.get(i).get(column) == Tile.GREEN) {
                tiles.get(i).set(column, tile);
                return Optional.of(i);
            }
        return Optional.empty();
    }
}
