package main.java.wordsim;

public abstract class Organism {
    protected int strength, initiative, y, x, age;
    protected char symbol;
    protected boolean alive;
    protected World world;

    public Organism (int strength, int initiative, int y, int x, char symbol) {
        this.strength = strength;
        this.initiative = initiative;
        this.y = y;
        this.x = x;
        this.age = 0;
        this.symbol = symbol;
        this.alive = true;
        this.world = null;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAge() {
        return age;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isDead() {
        return !alive;
    }

    public void dies() {
        alive = false;
    }

    public abstract void Action();

    public abstract void Collision();
    
}
