package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Utils.Coordinates;

public class Predator extends Creature {

    public Predator(Coordinates entityCoords, int moveSpeed) {
        super(entityCoords);
        this.moveSpeed = moveSpeed;
    }

    // private void interactWithFood(Herbivore foodObj)

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}