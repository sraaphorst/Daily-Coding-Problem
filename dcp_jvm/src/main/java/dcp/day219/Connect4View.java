package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class Connect4View extends JPanel implements Connect4Constants {

    private static final int PAD = 5;

    final List<JComponent> header;
    private final List<List<JComponent>> components;

    Connect4View() {
        setLayout(new GridLayout(0,Connect4.COLS, PAD, PAD));

        // Create the components.
        components = new ArrayList<>(ROWS);

        // Create the headers.
        header = new ArrayList<>(COLS);
        for (int c = 0; c < COLS; ++c) {
            final JButton button = new JButton(String.valueOf(c + 1));
            button.setBorder(BorderFactory.createLoweredSoftBevelBorder());
            add(button);
            header.add(button);
        }
        //components.add(header);

        // Create the JLabel rows.
        for (int r = 0; r < ROWS; ++r) {
            final List<JComponent> row = new ArrayList<>(COLS);
            for (int c = 0; c < COLS; ++c) {
                final JLabel label = new JLabel("(" + (r + 1) + "," + (c + 1) + ")");
                label.setOpaque(true);
                label.setForeground(Color.BLACK);
                label.setBackground(Player.UNVISISTED.color);
                label.setBorder(BorderFactory.createRaisedSoftBevelBorder());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                add(label);
                row.add(label);
            }
            components.add(row);
        }
    }

    /**
     * Set a tile to a player.
     */
    void setTileToPlayer(final Player player, int row, int col) {
        final JLabel label = ((JLabel)components.get(row).get(col));
        label.setBackground(player.color);
        repaint();
    }

    /**
     * Given a header, convert it to a column index 0 <= colIdx < COL.
     */
    OptionalInt headerToColumn(final JComponent c) {
        return IntStream.range(0, header.size()).filter(i -> header.get(i).equals(c)).findFirst();
    }
}
