package game.entities.dynamic;

import game.entities.EntitiesRepresentation;
import game.entities.WorldMap;
import game.entities.Coordinates;

public class Predator extends Creature<Herbivore> {

    private final static int FOOD_HEALTH_REDUCE_RATE = -25;
    private final static int HUNGRY_INCREASE_RATE = 100;

    public Predator(Coordinates entityCoords, WorldMap worldMap,
                    int moveSpeed,
                    int maxHealthPoints,
                    int maxHungry) {
        super(entityCoords, worldMap, Herbivore.class, moveSpeed, maxHealthPoints, maxHungry);
    }

    @Override
    protected void processFoodInteraction(Herbivore prey) {
        if (!prey.getHealth().isDead()) {
            prey.getHealth().adjustHealth(FOOD_HEALTH_REDUCE_RATE);
        } else {
            this.getHungry().adjustHungry(HUNGRY_INCREASE_RATE, this.getHealth());
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}