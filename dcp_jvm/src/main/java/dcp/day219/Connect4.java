package dcp.day219;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Optional;

final public class Connect4 extends JPanel {
    /**
     * The tiles: players are yellow and blue, and unused space is green.
     */
    private enum Tile {
        YELLOW(Color.YELLOW),
        BLUE(Color.BLUE),
        UNVISITED(Color.GREEN);

        private final Color colour;

        Tile(final Color colour) {
            this.colour = colour;
        }

        Color getColour() {
            return colour;
        }
    }

    /**
     * An (x,y) pair for convenience.
     */
    final class Pair {
        final int x;
        final int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * The information that encapsulates a winner.
     */
    final class Connect4Winner {
        final java.util.List<Pair> coords;
        final Tile tile;

        Connect4Winner(final java.util.List<Pair> coords, final Tile tile) {
            this.coords = coords;
            this.tile = tile;
        }
    }

    /**
     * Model for the game.
     * 1. Places the tiles when possible.
     * 2. Checks for the winner.
     */
    final class Connect4Model {
        final java.util.List<java.util.List<Tile>> tiles;

        Connect4Model() {
            tiles = new ArrayList<>(6);
            for (int i = 0; i < 6; ++i) {
                tiles.add(new ArrayList<>());
                for (int j = 0; j < 7; ++j) {
                    tiles.get(i).add(Tile.UNVISITED);
                }
            }
        }

        Optional<Integer> addTileToColumn(final Tile t, final int column) {
            // Find the loweest position in the column that will accommodate the tile.
            for (var row = 0; row < 6; ++row)
                if (tiles.get(row).get(column) == Tile.UNVISITED) {
                    tiles.get(row).set(column, t);
                    return Optional.of(row);
                }

            // The column is full.
            return Optional.empty();
        }

        // Check for a winner for the specified tile at the given position.
        Optional<Connect4Winner> checkWinner(final Tile tile, int row, int column) {
            final java.util.List<Pair> points = new ArrayList<>();

            // Check column for win by examining rows.
            for (var r = 0; r < 6; ++r) {
                if (tiles.get(column).get(r) == tile) {
                    points.add(new Pair(r, column));
                    if (points.size() == 4)
                        return Optional.of(new Connect4Winner(points, tile));
                } else
                    tiles.clear();
            }

            // Check row for win by examining columns.
            tiles.clear();
            for (var c = 0; c < 7; ++c) {
                if (tiles.get(c).get(row) == tile) {
                    points.add(new Pair(row, c));
                    if (points.size() == 4)
                        return Optional.of(new Connect4Winner(points, tile));
                } else
                    tiles.clear();
            }

            // Check diagonal \
            tiles.clear();
            // Starting at (row, column), we move delta (1, -1) until we hit a wall, at which point
            // we start investigating. We could do this algebraically but due to the dimensions of
            // the board, this is easier. We now move up and to the left to check for a winner.
            int dr = row;
            int dc = column;
            while (dr < 6 || dc > 0) {
                ++dr;
                --dc;
            }

            // Our starting position is (dr, dc) and our direction is (-1, 1).
            for (int d = 0; d >= 0; ++d) {
                dr -= 1;
                dc += 1;
                if (dr < 0 || dr > 5 || dc < 0 || dc > 6)
                    break;
                if (tiles.get(dr).get(dc) == tile) {
                    points.add(new Pair(dr, dc));
                    if (points.size() == 4)
                        return Optional.of(new Connect4Winner(points, tile));
                } else
                    tiles.clear();
            }

            // Check diagonal /
            tiles.clear();
            // Starting at (row, column), we move delta (1, 1) until we hit a wall, at which point
            // we start investigating. Then we proceed as above.
            dr = row;
            dc = column;
            while (dr < 6 || dc < 7) {
                ++dr;
                ++dc;
            }

            // Our starting position is (dr, dc), and our direction is (-1, -1).
            for (int d = 0; d >= 0; ++d) {
                dr += 1;
                dc += 1;
                if (dr < 0 || dr > 5 || dc < 0 || dc > 6)
                    break;
                if (tiles.get(dr).get(dc) == tile) {
                    points.add(new Pair(dr, dc));
                    if (points.size() == 4)
                        return Optional.of(new Connect4Winner(points, tile));
                } else
                    tiles.clear();

            }

            return Optional.empty();
        }

        // Handles clicks.

    }


    /**
     * The Connect4 View.
     */
    final class Connect4View extends JPanel {
        final java.util.List<JLabel> header;
        private final java.util.List<java.util.List<JLabel>> labels;

        Connect4View() {
            setLayout(new GridLayout(7, 7, 5, 5));

            // Create the header.
            header = new ArrayList<>(7);
            for (var i = 0; i < 7; ++i) {
                final JLabel l = new JLabel(String.valueOf(i));
                l.setBackground(Color.ORANGE);
                header.add(l);

                l.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        for (var idx = 0; idx < header.size(); ++idx)
                            if (e.getComponent().equals(header.get(idx)))
                                controller.headerClicked(idx);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }

            // Create the labels.
            labels = new ArrayList<>(6);
            for (var c = 0; c < 7; ++c) {
                java.util.List<JLabel> row = new ArrayList<>();
                for (var r = 0; r < 6; ++r) {
                    final JLabel l = new JLabel();
                    l.setBackground(Tile.UNVISITED.getColour());
                    row.add(l);
                }
                labels.add(row);

//            labels = new ArrayList<>(6);
//            for (var r = 0; r < 6; ++r) {
//                labels.add(new ArrayList<>(7));
//                for (var c = 0; c < 7; ++ c) {
//                    JLabel l = new JLabel();
//                    l.setBackground(Tile.UNVISITED.getColour());
//                    labels.get(r).get(c).add(l);
//                }
//            }
            }
        }
    }


    /**
     * The Connect4 Controller
     */
    final class Connect4Controller {
        // The current player.
        Tile player;

        Connect4Controller() {
            player = Tile.YELLOW;
        }

        // Handle when the mouse is clicked on the header.
        void headerClicked(int column) {
            // Try to add the tile. If it the column is full, we get None back and return.
            var rowOpt = model.addTileToColumn(player, column);
            if (rowOpt.isEmpty())
                return;
            rowOpt.ifPresent(row -> {
                var winnerOpt = model.checkWinner(player, row, column);
                winnerOpt.ifPresent(player -> win(player));
            });
            flipPlayer();
        }

        void flipPlayer() {
            player = player == Tile.YELLOW ? Tile.BLUE : Tile.YELLOW;
        }

        void win(Connect4Winner player) {
            System.out.println("PLAYER WON!!!!");
        }
    }

    final Connect4Controller controller;
    final Connect4Model model;
    final Connect4View view;


    public Connect4() {
        controller = new Connect4Controller();
        model = new Connect4Model();
        view = new Connect4View();
    }

    public static void main(String[] args) {
        final Connect4 game = new Connect4();
        JFrame f = new JFrame("Connect 4");
        f.setLayout(new BorderLayout());
        f.add(game, BorderLayout.CENTER);
        f.setSize(600, 600);
        f.setVisible(true);
    }
}
