package main.java.wordsim.animals;

import main.java.wordsim.Organism;
import main.java.wordsim.plants.Plant;

import java.awt.*;
import java.util.Random;

public class Human extends Animal {
    private char nextMove;
    private int abilityTimer;
    private Color abilityColor;

    public Human(int y, int x) {
        super(5, 4, y, x, '@', Color.CYAN);
        nextMove = ' ';
        abilityTimer = -5;
        abilityColor = Color.CYAN.darker();
    }

    public int getAbilityTimer() {
        return abilityTimer;
    }

    public Color getAbilityColor() {
        return abilityColor;
    }

    public void setAbilityTimer(int abilityTimer) {
        this.abilityTimer = abilityTimer;
    }

    public void setNextMove(char nextMove) {
        this.nextMove = nextMove;
    }

    @Override
    public String getName() {
        return "Human (" + Integer.toString(y) + ", " + Integer.toString(x) + ")";
    }

    @Override
    public Organism createNew(int y, int x) {
        return null;
    }

    @Override
    public void collision(Organism other) {
        world.appendLog(this.getName() + ": Attacked " + other.getName() + "\n");

        if (other instanceof Antelope) {
            y = other.getY();
            x = other.getX();
        }
        if (!other.didDeflectAttack(this)) {
            y = other.getY();
            x = other.getX();

            if (strength >= other.getStrength()) {
                other.dies();
            }
            else {
                this.dies();
            }
        }
    }

    @Override
    public void action() {
        int newX = x;
        int newY = y;
        boolean moved = false;
        boolean doubleMove = false;

        // First 3 turns when ability is active
        if (abilityTimer > 2) {
            doubleMove = true;
        }
        else if (abilityTimer > 0) {
            // Last 2 turns when ability is active
            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                doubleMove = true;
            }
        }

        if (doubleMove) {
            switch (nextMove) {
                case 'w':
                    if (y > 1) {
                        newY = y - 2;
                        moved = true;
                    }
                    break;
                case 's':
                    if (y < world.getRows() - 2) {
                        newY = y + 2;
                        moved = true;
                    }
                    break;
                case 'a':
                    if (x > 1) {
                        newX = x - 2;
                        moved = true;
                    }
                    break;
                case 'd':
                    if (x < world.getCols() - 2) {
                        newX = x + 2;
                        moved = true;
                    }
                    break;
            }
        }
        else {
            switch (nextMove) {
                case 'w':
                    if (y > 0) {
                        newY = y - 1;
                        moved = true;
                    }
                    break;
                case 's':
                    if (y < world.getRows() - 1) {
                        newY = y + 1;
                        moved = true;
                    }
                    break;
                case 'a':
                    if (x > 0) {
                        newX = x - 1;
                        moved = true;
                    }
                    break;
                case 'd':
                    if (x < world.getCols() - 1) {
                        newX = x + 1;
                        moved = true;
                    }
                    break;
            }
        }

        if (moved) {
            Organism other = world.getOrganism(newY, newX);

            if (other == null) {
                world.appendLog(getName() + ": Moved to (" + Integer.toString(newY) + ", " + Integer.toString(newX) + ")\n");
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
                    collision(other);
                }
            }
        }

        if (abilityTimer > -5) {
            abilityTimer--;
        }
        age++;
    }
}
