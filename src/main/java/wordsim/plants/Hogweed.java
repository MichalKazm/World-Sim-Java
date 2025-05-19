package main.java.wordsim.plants;

import main.java.wordsim.Organism;
import main.java.wordsim.animals.Animal;

import java.awt.*;

public class Hogweed extends Plant {
    public Hogweed(int y, int x) {
        super(10, y, x, 'H', Color.pink.darker());
    }

    @Override
    public String getName() {
        return "Hogweed (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Hogweed(y, x);
    }

    @Override
    public void collision(Organism other) {
        world.appendLog(other.getName() + ": Ate " + this.getName() + "\n");

        if(this.getStrength() <= other.getStrength()) {
            this.dies();
        }

        other.dies();
    }

    @Override
    public void action() {
        // Kill animals adjacent to this plant
        int killed = 0;

        Organism other = world.getOrganism(y - 1, x);
        if (other instanceof Animal) {
            other.dies();
            killed++;
        }
        other = world.getOrganism(y + 1, x);
        if (other instanceof Animal) {
            other.dies();
            killed++;
        }
        other = world.getOrganism(y, x - 1);
        if (other instanceof Animal) {
            other.dies();
            killed++;
        }
        other = world.getOrganism(y, x + 1);
        if (other instanceof Animal) {
            other.dies();
            killed++;
        }

        if (killed > 0) {
            world.appendLog(getName() + ": Killed " + Integer.toString(killed) + " animals adjacent to it\n");
        }

        super.action();
    }
}
