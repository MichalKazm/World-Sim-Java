package main.java.wordsim;

import main.java.wordsim.animals.Wolf;
import main.java.wordsim.worlds.WorldGrid;

public class Main {
    public static void main(String[] args) {
        WorldGrid world = new WorldGrid(30,50);

        world.addOrganism(new Wolf(0, 0));
        world.addOrganism(new Wolf(0, 1));
        world.addOrganism(new Wolf(1, 0));

        world.updateGame();
    }
}
