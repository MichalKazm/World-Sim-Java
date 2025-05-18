package main.java.wordsim.animals;

import main.java.wordsim.Organism;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {
    public Turtle(int y, int x) {
        super(2, 1, y, x, 'T', Color.GREEN);
    }

    @Override
    public boolean didDeflectAttack(Organism attacker) {
        if (attacker.getStrength() < 5) {
            world.appendLog(this.getName() + ": Pushed " + attacker.getName() + " back\n");
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String getName() {
        return "Turtle (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Turtle(y, x);
    }

    @Override
    public void action() {
        // 25% chance
        Random rand = new Random();
        if (rand.nextInt(4) == 0) {
            super.action();
        }
    }
}
