package main.java.wordsim.animals;

import main.java.wordsim.Organism;

public class Sheep extends Animal {
    public Sheep(int y, int x) {
        super(4, 4, y, x, 'S');
    }

    @Override
    public Organism createNew(int y, int x) {
        return new Sheep(y, x);
    }
}
