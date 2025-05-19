package main.java.wordsim.animals;

import main.java.wordsim.Organism;
import main.java.wordsim.plants.Plant;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal {
    public Fox(int y, int x) {
        super(3, 7, y, x, 'F', Color.ORANGE);
    }

    @Override
    public String getName() {
        return "Fox (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Fox(y, x);
    }

    @Override
    public void action() {
        int newX = x;
        int newY = y;

        Organism other = null;

        // Count available directions
        int available = 0;

        if (y > 0) {
            other = world.getOrganism(y - 1, x);
            if ((other == null) || (strength >= other.getStrength())) {
                available++;
            }
            other = null;
        }
        if (y < world.getRows() - 1) {
            other = world.getOrganism(y + 1, x);
            if ((other == null) || (strength >= other.getStrength())) {
                available++;
            }
            other = null;
        }
        if (x > 0) {
            other = world.getOrganism(y, x - 1);
            if ((other == null) || (strength >= other.getStrength())) {
                available++;
            }
            other = null;
        }
        if (x < world.getCols() - 1) {
            other = world.getOrganism(y, x + 1);
            if ((other == null) || (strength >= other.getStrength())) {
                available++;
            }
            other = null;
        }

        // Move in a random available direction
        int chosenMove = 4;

        if (available != 0) {
            Random rand = new Random();
            chosenMove = rand.nextInt(available);
        }

        // Move up
        if (y > 0) {
            other = world.getOrganism(y - 1, x);

            if ((other == null) || (strength >= other.getStrength())) {
                if (chosenMove == 0) {
                    newY = y - 1;
                }

                chosenMove--;
            }
            other = null;
        }
        // Move down
        if (y < world.getRows() - 1) {
            other = world.getOrganism(y + 1, x);

            if ((other == null) || (strength >= other.getStrength())) {
                if (chosenMove == 0) {
                    newY = y + 1;
                }

                chosenMove--;
            }
            other = null;
        }
        // Move left
        if (x > 0) {
            other = world.getOrganism(y, x - 1);

            if ((other == null) || (strength >= other.getStrength())) {
                if (chosenMove == 0) {
                    newX = x -1;
                }

                chosenMove--;
            }
            other = null;
        }
        // Move right
        if (x < world.getCols() - 1) {
            other = world.getOrganism(y, x + 1);

            if ((other == null) || (strength >= other.getStrength())) {
                if (chosenMove == 0) {
                    newX = x + 1;
                }

                chosenMove--;
            }
            other = null;
        }

        if (chosenMove < 0) {
            other = world.getOrganism(newY, newX);

            if (other == null) {
                world.appendLog(this.getName() + ": Moved to (" + Integer.toString(newY) + ", " + Integer.toString(newX) + ")\n");
                y = newY;
                x = newX;
            }
            else {
                if (other instanceof Plant) {
                    world.appendLog(getName() + ": Moved to (" + Integer.toString(newY) + ", " + Integer.toString(newX) + ")\n");
                    y = newY;
                    x = newX;
                    other.collision(this);
                }
                else {
                    this.collision(other);
                }
            }
        }

        age++;
    }
}
