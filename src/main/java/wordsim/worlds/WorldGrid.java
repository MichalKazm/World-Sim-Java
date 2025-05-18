package main.java.wordsim.worlds;

import main.java.wordsim.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldGrid extends World {
    public WorldGrid(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    protected void initWindow() {
        // Initialize frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                cell.setPreferredSize(new Dimension(25, 25));
                cell.add(text);
                gamePanel.add(cell);
                cells[i][j] = cell;
            }
        }

        gamePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
        frame.add(gamePanel, BorderLayout.CENTER);

        // Initialize button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JButton nextTurnButton = new JButton("Next turn");
        nextTurnButton.setFocusable(false);

        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeTurn();
            }
        });
        buttonPanel.add(nextTurnButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        updateGame();
        frame.pack();
        frame.setLocationRelativeTo(null);
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
    public void updateGame() {
        clearGame();

        for (Organism organism : order) {
            if (!organism.isDead()) {
                JPanel cell = cells[organism.getY()][organism.getX()];
                JLabel text = (JLabel) cell.getComponent(0);
                text.setText(Character.toString(organism.getSymbol()));
            }
        }

        frame.revalidate();
        frame.repaint();
    }

    @Override
    protected void takeTurn() {
        sortOrder();

        // Number of organisms before the turn
        int n = order.size();

        // Only organisms that are alive and created before the turn will take action
        for (int i = 0; i < n; i++) {
            if (!order.get(i).isDead()) {
                order.get(i).action();
            }
        }

        removeDead();
        updateGame();
        turn++;
    }
}
