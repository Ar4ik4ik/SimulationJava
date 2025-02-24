package Game.Entities.Static;

import Game.Entities.EntitiesRepresentation;
import Game.Entities.Entity;
import Game.Entities.Coordinates;

public class Tree extends Entity {

    public Tree(Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.TREE.getEntityRepresentation();
    }
}
