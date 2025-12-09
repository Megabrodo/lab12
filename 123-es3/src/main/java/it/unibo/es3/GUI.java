package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private final transient Logics logics;
    private final int maxWidth;

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        this.maxWidth = width;
        this.logics = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final var pos = new Pair<>(i, j);
                final JButton button = new JButton(logics.checkCell(pos) ? "*" : " ");
                this.cells.add(button);
                panel.add(button);
            }
        }
        final JButton arrow = new JButton(">");
        arrow.addActionListener(e -> {
            logics.replicate();
            updateMatrix();
            if (logics.toQuit()) {
                dispose();
            }
        });
        this.getContentPane().add(BorderLayout.SOUTH, arrow);
        pack();
        this.setVisible(true);
    }

    private void updateMatrix() {
        final boolean[][] matrix = logics.getMatrix();
        for (int row = 0; row < maxWidth; row++) {
            for (int col = 0; col < maxWidth; col++) {
                final int i = row * maxWidth + col;
                cells.get(i).setText(matrix[row][col] ? "*" : " ");
            }
        }
    }
}
