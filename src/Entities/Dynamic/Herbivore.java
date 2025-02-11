package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Utils.Coordinates;

public class Herbivore extends Creature {

    public Herbivore(Coordinates entityCoords, int moveSpeed) {
        super(entityCoords);
        this.moveSpeed = moveSpeed;
    }

    // private void interactWithFood(Grass foodObj)

    @Override
    public String toString() {
        return EntitiesRepresentation.HERBIVORE.getEntityRepresentation();
    }
}
