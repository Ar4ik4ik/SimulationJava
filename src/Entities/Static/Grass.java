package Entities.Static;

import Entities.EntitiesRepresentation;
import Entities.Entity;
import Utils.Coordinates;

public class Grass extends Entity {

    Grass (Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.GRASS.getEntityRepresentation();
    }
}
