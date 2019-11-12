package dcp.day219;

import java.awt.*;

// These are the possible tiles.
enum Tile {
    YELLOW(Color.YELLOW),
    BLUE(Color.CYAN),
    GREEN(Color.GREEN);

    final Color color;

    Tile(final Color color) {
        this.color = color;
    }

    Color getColor() {
        return color;
    }
}