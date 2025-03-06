package game.entities.dynamic;

import game.entities.EntitiesRepresentation;
import game.entities.statics.Grass;
import game.entities.WorldMap;
import game.entities.Coordinates;

public class Herbivore extends Creature<Grass> {

    private final static int FOOD_HEALTH_REDUCE_RATE = -1;
    private final static int HUNGRY_INCREASE_RATE = 25;

    public Herbivore(Coordinates entityCoords, WorldMap worldMap, int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(entityCoords, worldMap, Grass.class, moveSpeed , maxHealthPoints, maxHungry);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.HERBIVORE.getEntityRepresentation();
    }

    @Override
    protected void processFoodInteraction(Grass prey) {
        if (!prey.getHealth().isDead()) {
            prey.getHealth().adjustHealth(FOOD_HEALTH_REDUCE_RATE);
            this.getHungry().adjustHungry(HUNGRY_INCREASE_RATE, this.getHealth());
        }
    }

}
