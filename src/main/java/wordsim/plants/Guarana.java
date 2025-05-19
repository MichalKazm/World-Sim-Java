package main.java.wordsim.plants;

import main.java.wordsim.Organism;

import java.awt.*;

public class Guarana extends Plant {
    public Guarana(int y, int x) {
        super(0, y, x, '*', Color.RED);
    }

    @Override
    public String getName() {
        return "Guarana (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Guarana(y, x);
    }

    @Override
    public void collision(Organism other) {
        world.appendLog(other.getName() + ": Ate " + this.getName() + "\n");

        int newStrength = other.getStrength() + 3;

        world.appendLog(other.getName() + ": Strength increased from " + Integer.toString(other.getStrength()) + " to " + Integer.toString(newStrength) + "\n");

        other.setStrength(newStrength);

        this.dies();
    }
}
