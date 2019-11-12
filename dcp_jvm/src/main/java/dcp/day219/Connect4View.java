package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Connect4View extends JPanel {
    final Connect4 connect4;

    private static final int PAD  = 5;

    final List<JComponent> header;
    final List<List<JComponent>> components;

    public Connect4View(final Connect4 connect4) {
        this.connect4 = connect4;

        // TODO: WHY the adjustments of +2 and -2 to get the correct grid?
        //setLayout(new GridLayout(Connect4.ROWS+2, Connect4.COLS-2, PAD, PAD));
        setLayout(new GridLayout(0, Connect4.COLS, PAD, PAD));

        // Create the components.
        components = new ArrayList<>(Connect4.ROWS);

        // Create the headers.
        header = new ArrayList<>(Connect4.COLS);
        for (int i = 0; i < Connect4.COLS; ++i) {
            final JButton button = new JButton(String.valueOf(i+1));
            button.setBorder(BorderFactory.createLoweredSoftBevelBorder());
            add(button);
            header.add(button);

            // Add the controlling behaviour for the button.
            //button.addActionListener(evt -> );
        }
        components.add(header);

        // Create the JLabel rows.
        for (int i = 0; i < Connect4.ROWS; ++i) {
            final List<JComponent> row = new ArrayList<>(Connect4.COLS);
            for (int c = 0; c < Connect4.COLS; ++c) {
                final JLabel label = new JLabel("LABEL");
                label.setOpaque(true);
                label.setForeground(Color.BLACK);
                label.setBackground(Tile.GREEN.getColor());
                label.setBorder(BorderFactory.createRaisedSoftBevelBorder());
                add(label);
                row.add(label);
                components.add(row);
            }
        }

    }
}
