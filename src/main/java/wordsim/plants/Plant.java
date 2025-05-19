package main.java.wordsim.plants;

import main.java.wordsim.Organism;
import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism {
    public Plant(int strength, int y, int x, char symbol, Color color) {
        super(strength, 0, y, x, symbol, color);
    }

    @Override
    public void collision(Organism other) {
        world.appendLog(other.getName() + ": Ate " + getName());
        this.dies();
    }

    @Override
    public void action() {
        // 10% chance
        Random rand = new Random();
        if (rand.nextInt(10) == 0) {
            // Count available directions
            int available = 0;
            int newY = y;
            int newX = x;

            if ((y > 0) && (world.getOrganism(y - 1, x) == null)) {
                available++;
            }
            if ((y < world.getRows() - 1) && (world.getOrganism(y + 1, x) == null)) {
                available++;
            }
            if ((x > 0) && (world.getOrganism(y, x - 1) == null)) {
                available++;
            }
            if ((x < world.getCols() - 1) && (world.getOrganism(y, x + 1) == null)) {
                available++;
            }

            // Create a new plant in a random empty adjacent cell
            int chosenCell = 4;

            if (available != 0) {
                chosenCell = rand.nextInt(available);
            }

            // Cell above
            if ((y > 0) && (world.getOrganism(y - 1, x) == null)) {
                if (chosenCell == 0) {
                    newY = y - 1;
                    world.addOrganism(createNew(newY, newX));
                }

                chosenCell--;
            }
            // Cell below
            if ((y < world.getRows() - 1) && (world.getOrganism(y + 1, x) == null))  {
                if (chosenCell == 0) {
                    newY = y + 1;
                    world.addOrganism(createNew(newY, newX));
                }

                chosenCell--;
            }

            // Cell to the left
            if ((x > 0) && (world.getOrganism(y, x - 1) == null)) {
                if (chosenCell == 0) {
                    newX = x - 1;
                    world.addOrganism(createNew(newY, newX));
                }

                chosenCell--;
            }
            // Cell to the right
            if ((x < world.getCols() - 1) && (world.getOrganism(y, x + 1) == null)) {
                if (chosenCell == 0) {
                    newX = x + 1;
                    world.addOrganism(createNew(newY, newX));
                }

                chosenCell--;
            }

            if (chosenCell < 0) {
                world.appendLog(this.getName() + ": Spread to (" + Integer.toString(newY) + ", " + Integer.toString(newX) + ")");
            }
        }

        age++;
    }
}
