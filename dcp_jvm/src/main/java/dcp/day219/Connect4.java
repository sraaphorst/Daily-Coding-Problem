package dcp.day219;

import javax.swing.*;
import java.awt.*;

final public class Connect4 {

    static final int ROWS = 6;
    static final int COLS = 7;

    final Connect4Model model;
    final Connect4View view;
    final Connect4Controller controller;

    Connect4() {
        model = new Connect4Model(this);
        view = new Connect4View(this);
        controller = new Connect4Controller(this);
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