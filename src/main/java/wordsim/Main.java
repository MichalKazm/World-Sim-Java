package main.java.wordsim;

import main.java.wordsim.animals.*;
import main.java.wordsim.plants.Grass;
import main.java.wordsim.worlds.WorldGrid;

public class Main {
    public static void main(String[] args) {
        WorldGrid world = new WorldGrid(30,50);

        world.addOrganism(new Wolf(0, 0));
        world.addOrganism(new Wolf(0, 1));
        world.addOrganism(new Wolf(1, 0));
        world.addOrganism(new Sheep(10, 10));
        world.addOrganism(new Sheep(11, 11));
        world.addOrganism(new Sheep(10, 11));
        world.addOrganism(new Fox(5,0));
        world.addOrganism(new Turtle(7, 7));
        world.addOrganism(new Antelope(12, 12));
        world.addOrganism(new Grass(25, 45));

        world.updateGame();
    }
}
