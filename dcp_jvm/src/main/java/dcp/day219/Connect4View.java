package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class Connect4View extends JPanel {

    private static final int PAD  = 5;

    final List<JComponent> header;
    final List<List<JComponent>> components;

    Connect4View() {
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
        }
        components.add(header);

        // Create the JLabel rows.
        for (int r = 0; r < Connect4.ROWS; ++r) {
            final List<JComponent> row = new ArrayList<>(Connect4.COLS);
            for (int c = 0; c < Connect4.COLS; ++c) {
                final JLabel label = new JLabel("LABEL (" + r + "," + c + ")");
                label.setOpaque(true);
                label.setForeground(Color.BLACK);
                label.setBackground(Tile.GREEN.getColor());

//                if (c == 0) label.setBackground(Color.RED);
//                if (c == 1) label.setBackground(Color.MAGENTA);
//                if (c == 2) label.setBackground(Color.ORANGE);
//                if (c == 3) label.setBackground(Color.GREEN);
//                if (c == 4) label.setBackground(Color.LIGHT_GRAY);

                label.setBorder(BorderFactory.createRaisedSoftBevelBorder());
                add(label);
                row.add(label);
                components.add(row);
            }
        }
    }

    void setToTile(final Tile tile, int row, int col) {
        System.out.println("\n*** SET TO TILE: " + tile + " r=" + row + " c=" + col + " ***");
        final JLabel label = ((JLabel)components.get(col).get(row));
        System.out.println("view " + label.getText() + ", setToTile: (" + row + "," + col + ") -> " + tile.getColor());
        System.out.println("\twas: " + label.getBackground());
        label.setBackground(tile.getColor());
        System.out.println("\tnow: " + label.getBackground());
    }

    // Convert a header to a column.
    OptionalInt headerToColumn(final JComponent c) {
        return IntStream.range(0, header.size()).filter(i -> header.get(i).equals(c)).findFirst();
    }
}
