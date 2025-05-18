package main.java.wordsim.worlds;

import main.java.wordsim.Organism;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class World {
    protected int rows, cols, turn;
    protected ArrayList<Organism> order;
    protected JTextArea logs;
    protected JPanel[][] cells;
    protected JFrame frame;

    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.turn = 1;
        this.order = new ArrayList<>();
        this.logs = new JTextArea();
        this.cells = new JPanel[rows][cols];
        this.frame = new JFrame("Simulator");

        logs.append("-- Turn 0 --\n");

        this.initWindow();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean addOrganism(Organism organism) {
        if (organism.getY() < rows && organism.getX() < cols && organism.getY() >= 0 && organism.getX() >= 0) {
            organism.setWorld(this);
            order.add(organism);
            appendLog(organism.getName() + ": Was created\n");
            return true;
        }
        else {
            return false;
        }
    }

    public Organism getOrganism(int y, int x) {
        for (Organism organism : order) {
            if (!organism.isDead() && organism.getY() == y && organism.getX() == x) {
                return organism;
            }
        }

        return null;
    }

    public void appendLog(String message) {
        logs.append(message);
    }

    protected void removeDead() {
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).isDead()) {
                order.remove(i);
                i--;
            }
        }
    }

    protected void sortOrder() {
        for (int i = 0; i < order.size() - 1; i++) {
            for (int j = i + 1; j < order.size(); j++) {
                if (order.get(j).getInitiative() > order.get(i).getInitiative()) {
                    Collections.swap(order, i, j);
                }
            else if (order.get(j).getInitiative() == order.get(i).getInitiative() && order.get(j).getAge() > order.get(i).getAge()) {
                    Collections.swap(order, i, j);
                }
            }
        }
    }

    protected abstract void initWindow();

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

    public void updateGame() {
        clearGame();

        for (Organism organism : order) {
            if (!organism.isDead()) {
                JPanel cell = cells[organism.getY()][organism.getX()];
                cell.setBackground(organism.getColor());
                JLabel text = (JLabel) cell.getComponent(0);
                text.setText(Character.toString(organism.getSymbol()));
            }
        }

        frame.revalidate();
        frame.repaint();
    }

    protected void takeTurn() {
        sortOrder();

        // Number of organisms before the turn
        int n = order.size();

        appendLog("-- Turn " + Integer.toString(turn) + " --\n");

        // Only organisms that are alive and created before the turn will take action
        for (int i = 0; i < n; i++) {
            if (!order.get(i).isDead()) {
                order.get(i).action();
            }
        }

        // Display latest logs
        logs.setCaretPosition(logs.getDocument().getLength());

        removeDead();
        updateGame();
        turn++;
    }
}
