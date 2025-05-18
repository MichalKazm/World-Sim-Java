package main.java.wordsim.worlds;

import main.java.wordsim.Organism;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class World {
    protected int rows, cols;
    protected ArrayList<Organism> order;
    protected JPanel[][] cells;
    protected JFrame frame;

    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.order = new ArrayList<>();
        this.cells = new JPanel[rows][cols];
        this.frame = new JFrame("Simulator");

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

    protected abstract void clearGame();

    protected abstract void updateGame();
}
