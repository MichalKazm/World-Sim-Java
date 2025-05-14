package main.java.wordsim;

import java.util.ArrayList;
import java.util.Collections;

public class World {
    private int rows, cols;
    ArrayList<Organism> order;

    public World(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
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

    public void sortOrder() {
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
}
