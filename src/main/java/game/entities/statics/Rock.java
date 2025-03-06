package game.entities.statics;

import game.entities.EntitiesRepresentation;
import game.entities.Entity;
import game.entities.Coordinates;

public class Rock extends Entity {

    public Rock(Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.ROCK.getEntityRepresentation();
    }
}
