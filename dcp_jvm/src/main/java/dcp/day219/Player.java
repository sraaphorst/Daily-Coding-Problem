package dcp.day219;

import java.awt.*;

// These are the possible tiles marked by a player.
enum Player {
    YELLOW(Color.YELLOW),
    BLUE(Color.CYAN),
    UNVISISTED(Color.GREEN),
    WINNER(Color.RED);

    final Color color;

    Player(final Color color) {
        this.color = color;
    }
}