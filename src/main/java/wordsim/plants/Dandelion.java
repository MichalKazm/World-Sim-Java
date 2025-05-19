package main.java.wordsim.plants;

import main.java.wordsim.Organism;

import java.awt.*;

public class Dandelion extends Plant{
    public Dandelion(int y, int x) {
        super(0, y, x, 'D', Color.YELLOW.darker());
    }

    @Override
    public String getName() {
        return "Dandelion (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Dandelion(y, x);
    }

    @Override
    public void action() {
        for (int i = 0; i < 3; i++) {
            super.action();
        }

        // Age is incremented 3 times in loop
        age = age - 2;
    }
}
