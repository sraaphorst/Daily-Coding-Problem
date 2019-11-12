package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.util.OptionalInt;

final public class Connect4 {

    static final int ROWS = 6;
    static final int COLS = 7;

    private final Connect4Model model;
    private final Connect4View view;
    private final Connect4Controller controller;
    private Tile player;

    public Connect4() {
        model = new Connect4Model();
        view = new Connect4View();
        controller = new Connect4Controller();
        player = Tile.BLUE;

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

                            // Repaint and swap players.
                            view.repaint();
                            swapPlayer();
                        });
                    }
            ));
        });
    }

    void swapPlayer() {
        player = player == Tile.BLUE ? Tile.YELLOW : Tile.BLUE;
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