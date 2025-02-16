package Entities.Static;

import Entities.EntitiesRepresentation;
import Entities.Entity;
import Utils.Coordinates;

public class Rock extends Entity {

    public Rock(Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.ROCK.getEntityRepresentation();
    }
}
