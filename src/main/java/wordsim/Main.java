package main.java.wordsim;

import main.java.wordsim.animals.*;
import main.java.wordsim.plants.*;

public class Main {
    public static void main(String[] args) {
        World world = new World(30,50);

        world.addOrganism(new Wolf(0, 0));
        world.addOrganism(new Wolf(1, 0));
        world.addOrganism(new Sheep(10, 10));
        world.addOrganism(new Sheep(11, 11));
        world.addOrganism(new Sheep(10, 11));
        world.addOrganism(new Fox(5,0));
        world.addOrganism(new Turtle(7, 7));
        world.addOrganism(new Antelope(12, 12));
        world.addOrganism(new Grass(25, 45));
        world.addOrganism(new Dandelion(0, 35));
        world.addOrganism(new Guarana(10, 20));
        world.addOrganism(new DeadlyNightshade(20, 30));
        world.addOrganism(new Hogweed(15, 40));
        world.addOrganism(new Human(20, 20));

        world.updateGame();
    }
}
