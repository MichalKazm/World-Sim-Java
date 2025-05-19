package main.java.wordsim;

import java.awt.*;

public abstract class Organism {
    protected int strength, initiative, y, x, age;
    protected char symbol;
    protected Color color;
    protected boolean alive;
    protected World world;

    public Organism(int strength, int initiative, int y, int x, char symbol, Color color) {
        this.strength = strength;
        this.initiative = initiative;
        this.y = y;
        this.x = x;
        this.age = 0;
        this.symbol = symbol;
        this.color = color;
        this.alive = true;
        this.world = null;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getAge() {
        return age;
    }

    public char getSymbol() {
        return symbol;
    }

    public Color getColor() { return color; }

    public boolean didDeflectAttack(Organism attacker) {
        return false;
    }

    public boolean isDead() {
        return !alive;
    }

    public void dies() {
        world.appendLog(this.getName() + ": Died\n");
        this.alive = false;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setStrength(int strength) { this.strength = strength; }

    public abstract String getName();

    public abstract Organism createNew(int y, int x);

    public abstract void collision(Organism other);

    public abstract void action();
}
