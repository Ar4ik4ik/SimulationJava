package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Entities.Entity;
import Entities.WorldMap;
import Utils.Coordinates;

public class Predator extends Creature<Herbivore> {

    public Predator(Coordinates entityCoords, int moveSpeed, WorldMap mapInstance) {
        super(entityCoords, mapInstance, Herbivore.class);
        this.moveSpeed = moveSpeed;
    }

    // private void interactWithFood(Herbivore foodObj)



    @Override
    protected void interactWithFood(Herbivore prey) {
        prey.setHealthPoints(-25);
        if (!prey.isAlive()) {
            this.setHungerValue(100);
            mapInstance.deleteFromMap(prey);
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}