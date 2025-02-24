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
        this.moveSpeed = moveSpeed;
    }

    @Override
    protected void interactWithFood(Herbivore prey) {
        if (!prey.health.isDead()) {
            prey.health.adjustHealth(-25);
            this.hungry.adjustHungry(100, this.health);
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}