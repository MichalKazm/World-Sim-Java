package main.java.wordsim.worlds;

import main.java.wordsim.Organism;

import javax.swing.*;
import java.awt.*;

public class WorldGrid extends World {
    public WorldGrid(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    protected void initWindow() {
        // Initialize frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(this.rows * 26 - 1, this.cols * 26 - 1);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // Initialize game panel
        JPanel gamePanel = new JPanel(new GridLayout(this.rows, this.cols, 1, 1));
        gamePanel.setBackground(Color.BLACK);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                // Creating and adding cells to a grid
                JPanel cell = new JPanel();
                JLabel text = new JLabel();
                cell.add(text);
                gamePanel.add(cell);
                cells[i][j] = cell;
            }
        }

        frame.add(gamePanel);
        frame.setVisible(true);
    }

    @Override
    protected void clearGame() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                JPanel cell = cells[i][j];
                cell.setBackground(Color.WHITE);
                JLabel text = (JLabel) cell.getComponent(0);
                text.setText("");
            }
        }
    }

    @Override
    protected void updateGame() {
        clearGame();

        for (Organism organism : order) {
            if (!organism.isDead()) {
                JPanel cell = cells[organism.getY()][organism.getX()];
                JLabel text = (JLabel) cell.getComponent(0);
                text.setText(Character.toString(organism.getSymbol()));
            }
        }
    }
}
