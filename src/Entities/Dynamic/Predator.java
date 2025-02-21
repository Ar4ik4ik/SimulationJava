package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Entities.WorldMap;
import Entities.Coordinates;

public class Predator extends Creature<Herbivore> {

    public Predator(Coordinates entityCoords, WorldMap mapInstance,
                    int moveSpeed,
                    int maxHealthPoints,
                    int maxHungry) {
        super(entityCoords, mapInstance, Herbivore.class, moveSpeed, maxHealthPoints, maxHungry);
        this.moveSpeed = moveSpeed;
    }

    // private void interactWithFood(Herbivore foodObj)



    @Override
    protected void interactWithFood(Herbivore prey) {
        prey.setCurrentHealthPoints(-25);
        if (!prey.isAlive()) {
            this.setHungryValue(100);
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}