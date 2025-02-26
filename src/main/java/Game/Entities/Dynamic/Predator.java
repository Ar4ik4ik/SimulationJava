package Game.Entities.Dynamic;

import Game.Entities.EntitiesRepresentation;
import Game.Entities.WorldMap;
import Game.Entities.Coordinates;

public class Predator extends Creature<Herbivore> {

    public Predator(Coordinates entityCoords, WorldMap mapInstance,
                    int moveSpeed,
                    int maxHealthPoints,
                    int maxHungry) {
        super(entityCoords, mapInstance, Herbivore.class, moveSpeed, maxHealthPoints, maxHungry);
    }

    @Override
    protected void processFoodInteraction(Herbivore prey) {
        if (!prey.getHealth().isDead()) {
            prey.getHealth().adjustHealth(-25);
            this.getHungry().adjustHungry(100, this.getHealth());
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}