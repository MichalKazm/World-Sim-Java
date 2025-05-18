package main.java.wordsim.animals;

import main.java.wordsim.Organism;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope(int y, int x) {
        super(4, 4, y, x, 'A', Color.YELLOW);
    }

    @Override
    public boolean didDeflectAttack(Organism attacker) {
        // 50% chance
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            int newY = y;
            int newX = x;

            boolean success = false;
            if (y > 0) {
                if (world.getOrganism(y - 1, x) == null) {
                    newY = y - 1;
                    success = true;
                }
            }
            if (y < world.getRows() - 1) {
                if ((!success) && (world.getOrganism(y + 1, x) == null)) {
                    newY = y + 1;
                    success = true;
                }
            }
            if (x > 0) {
                if ((!success) && (world.getOrganism(y, x - 1) == null)) {
                    newX = x - 1;
                    success = true;
                }
            }
            if (x < world.getCols() - 1) {
                if ((!success) && (world.getOrganism(y, x + 1) == null)) {
                    newX = x + 1;
                    success = true;
                }
            }

            if (success) {
                world.appendLog(getName() + ": Fled to (" + Integer.toString(newY) + ", " + Integer.toString(newX) + ")\n");
                world.appendLog(attacker.getName() + ": Moved to (" + Integer.toString(y) + ", " + Integer.toString(x) + ")\n");
                y = newY;
                x = newX;
                return true;
            }
        }

        return false;
    }

    @Override
    public String getName() {
        return "Antelope (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Antelope(y, x);
    }

    @Override
    public void action() {
        int newX = x;
        int newY = y;

        // Count available directions
        int available = 0;

        if (y > 1) {
            available++;
        }
        if (y < world.getRows() - 2) {
            available++;
        }
        if (x > 1) {
            available++;
        }
        if (x < world.getCols() - 2) {
            available++;
        }

        // Move in a random available direction
        int chosenMove = 4;

        if (available != 0) {
            Random rand = new Random();
            chosenMove = rand.nextInt(available);
        }

        // Move up
        if (y > 1) {
            if (chosenMove == 0) {
                newY = y - 2;
            }

            chosenMove--;
        }
        // Move down
        if (y < world.getRows() - 2) {
            if (chosenMove == 0) {
                newY = y + 2;
            }

            chosenMove--;
        }

        // Move left
        if (x > 1) {
            if (chosenMove == 0) {
                newX = x - 2;
            }

            chosenMove--;
        }
        // Move right
        if (x < world.getCols() - 2) {
            if (chosenMove == 0) {
                newX = x + 2;
            }

            chosenMove--;
        }

        if (chosenMove < 0) {
            Organism other = world.getOrganism(newY, newX);

            if (other == null) {
                world.appendLog(this.getName() + ": Moved to (" + Integer.toString(newY) + ", " + Integer.toString(newX) + ")\n");
                y = newY;
                x = newX;
            }
            else {
                collision(other);
            }
        }

        age++;
    }
}
