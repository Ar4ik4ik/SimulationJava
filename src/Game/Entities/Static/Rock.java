package Game.Entities.Static;

import Game.Entities.EntitiesRepresentation;
import Game.Entities.Entity;
import Game.Entities.Coordinates;

public class Rock extends Entity {

    public Rock(Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.ROCK.getEntityRepresentation();
    }
}
