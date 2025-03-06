package game.utils;

import game.entities.Coordinates;
import game.entities.WorldMap;
import game.entities.dynamic.Predator;

public class PredatorSpawner extends Spawner {

    public PredatorSpawner(WorldMap worldMap, int amount, int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(
                worldMap,
                () -> new Predator(
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
