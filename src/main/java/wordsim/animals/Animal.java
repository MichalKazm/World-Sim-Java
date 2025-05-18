package main.java.wordsim.animals;

import main.java.wordsim.Organism;

import java.awt.*;
import java.util.Random;

public abstract class Animal extends Organism {
    public Animal(int strength, int initiative, int y, int x, char symbol, Color color) {
        super(strength, initiative, y, x, symbol, color);
    }

    @Override
    public void collision(Organism other) {
        if (this.getClass() == other.getClass()) {
            boolean success = false;
            if (world.getOrganism(y - 1, x) == null) {
                success = world.addOrganism(createNew(y - 1, x));
            }
            if ((!success) && (world.getOrganism(y + 1, x) == null)) {
                success = world.addOrganism(createNew(y + 1, x));
            }
            if ((!success) && (world.getOrganism(y, x - 1) == null)) {
                success = world.addOrganism(createNew(y, x - 1));
            }
            if ((!success) && (world.getOrganism(y, x + 1) == null)) {
                success = world.addOrganism(createNew(y, x + 1));
            }
            if ((!success) && (world.getOrganism(other.getY() + 1, other.getX()) == null)) {
                success = world.addOrganism(createNew(other.getY() + 1, other.getX()));
            }
            if ((!success) && (world.getOrganism(other.getY() - 1, other.getX()) == null)) {
                success = world.addOrganism(createNew(other.getY() - 1, other.getX()));
            }
            if ((!success) && (world.getOrganism(other.getY(), other.getX() + 1) == null)) {
                success = world.addOrganism(createNew(other.getY(), other.getX() + 1));
            }
            if ((!success) && (world.getOrganism(other.getY(), other.getX() - 1) == null)) {
                success = world.addOrganism(createNew(other.getY(), other.getX() - 1));
            }

            if (success) {
                world.appendLog(this.getName() + ": Reproduce with " + other.getName() + "\n");
            }
        }
        else {
            world.appendLog(this.getName() + ": Attacked " + other.getName() + "\n");

            if (!other.didDeflectAttack(this)) {
                y = other.getY();
                x = other.getX();
                if (strength >= other.getStrength()) {
                    other.dies();
                } else {
                    this.dies();
                }
            }
        }
    }

    @Override
    public void action() {
        int newX = x;
        int newY = y;

        // Count available directions
        int available = 0;

        if (y > 0) {
            available++;
        }
        if (y < world.getRows() - 1) {
            available++;
        }
        if (x > 0) {
            available++;
        }
        if (x < world.getCols() - 1) {
            available++;
        }

        // Move in a random available direction
        int chosenMove = 4;

        if (available != 0) {
            Random rand = new Random();
            chosenMove = rand.nextInt(available);
        }

        // Move up
        if (y > 0) {
            if (chosenMove == 0) {
                newY = y - 1;
            }

            chosenMove--;
        }
        // Move down
        if (y < world.getRows() - 1) {
            if (chosenMove == 0) {
                newY = y + 1;
            }

            chosenMove--;
        }

        // Move left
        if (x > 0) {
            if (chosenMove == 0) {
                newX = x - 1;
            }

            chosenMove--;
        }
        // Move right
        if (x < world.getCols() - 1) {
            if (chosenMove == 0) {
                newX = x + 1;
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
