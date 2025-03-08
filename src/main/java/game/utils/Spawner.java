package game.utils;

import game.entities.Coordinates;
import game.entities.Entity;
import game.entities.WorldMap;
import game.entities.dynamic.Creature;

import java.util.Random;
import java.util.function.Supplier;

public class Spawner {
    private static final Random RANDOM = new Random();
    private final WorldMap worldMap;
    private final Supplier<Entity> entitySupplier;
    private final int amount;

    public Spawner(WorldMap worldMap, Supplier<Entity> entitySupplier, int amount) {
        this.worldMap = worldMap;
        this.entitySupplier = entitySupplier;
        this.amount = amount;
    }

    public void spawn() {
        for (int i = 0; i < amount; i++) {
            Entity entity = entitySupplier.get();
            Coordinates coordinates = getRandomCoordinates(worldMap);
            worldMap.place(entity, coordinates);

            if (entity instanceof Creature<?>) {
                ((Creature<?>) entity).updateCoordinates(coordinates);
            }
        }
    }

    private Coordinates getRandomCoordinates(WorldMap worldMap) {
        while (true) {
            int randX = RANDOM.nextInt(worldMap.height);
            int randY = RANDOM.nextInt(worldMap.width);
            if (worldMap.isCoordsValid(new Coordinates(randX, randY))) {
                return new Coordinates(randX, randY);
            }
        }
    }
}