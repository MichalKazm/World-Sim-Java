package main.java.wordsim.animals;

import main.java.wordsim.Organism;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(int y, int x) {
        super(9, 5, y, x, 'W', Color.GRAY);
    }

    @Override
    public String getName() {
        return "Wolf (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Wolf(y, x);
    }
}
