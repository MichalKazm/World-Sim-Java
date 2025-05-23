package main.java.wordsim.animals;

import main.java.wordsim.Organism;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(int y, int x) {
        super(4, 4, y, x, 'S', Color.PINK);
    }

    @Override
    public String getName() {
        return "Sheep (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Sheep(y, x);
    }
}
