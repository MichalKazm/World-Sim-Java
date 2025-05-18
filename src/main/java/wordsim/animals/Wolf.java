package main.java.wordsim.animals;

import main.java.wordsim.Organism;

public class Wolf extends Animal {
    public Wolf(int y, int x) {
        super(9, 5, y, x, 'W');
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Wolf(y, x);
    }
}
