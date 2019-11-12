package dcp.day219;

import java.util.Optional;

public class Connect4Controller {
    final Connect4 connect4;

    public Connect4Controller(final Connect4 connect4) {
        this.connect4 = connect4;
    }

    Optional<Integer> columnAdded(int column) {
        System.out.println("Column added!");
        return Optional.empty();
    }
}
