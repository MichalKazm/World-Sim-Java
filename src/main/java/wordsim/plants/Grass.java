package main.java.wordsim.plants;

import main.java.wordsim.Organism;

import java.awt.*;

public class Grass extends Plant {
    public Grass(int y, int x) {
        super(0, y, x, 'G', Color.GREEN.darker());
    }

    @Override
    public String getName() {
        return "Grass (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Grass(y, x);
    }
}
