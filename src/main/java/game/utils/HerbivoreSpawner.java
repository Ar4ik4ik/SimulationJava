package game.utils;

import game.entities.Coordinates;
import game.entities.WorldMap;
import game.entities.dynamic.Herbivore;

public class HerbivoreSpawner extends Spawner {

    public HerbivoreSpawner(WorldMap worldMap, int amount, int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(
                worldMap,
                () -> new Herbivore(
                        new Coordinates(-1, -1),
                        worldMap,
                        moveSpeed,
                        maxHealthPoints,
                        maxHungry
                ),
                amount
        );
    }
}
