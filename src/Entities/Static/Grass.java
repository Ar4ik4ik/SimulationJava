package Entities.Static;

import Entities.EntitiesRepresentation;
import Entities.Entity;
import Utils.Coordinates;

public class Grass extends Entity {

    int healthPoints = 4;

    Grass (Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.GRASS.getEntityRepresentation();
    }
}
