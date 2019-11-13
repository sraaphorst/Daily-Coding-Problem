package dcp.day219;

import java.util.List;

class Winner {
    final Player player;
    final List<Position> positions;

    Winner(Player player, final List<Position> positions) {
        this.player = player;
        this.positions = positions;
    }
}
