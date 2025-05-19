package main.java.wordsim.plants;

import main.java.wordsim.Organism;

import java.awt.*;

public class DeadlyNightshade extends Plant {
    public DeadlyNightshade(int y, int x) {
        super(99, y, x, 'N', Color.BLUE.darker());
    }

    @Override
    public String getName() {
        return "Deadly Nightshade (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new DeadlyNightshade(y, x);
    }

    @Override
    public void collision(Organism other) {
        world.appendLog(other.getName() + ": Ate " + this.getName() + "\n");

        if (getStrength() <= other.getStrength()) {
            this.dies();
        }

        other.dies();
    }
}
