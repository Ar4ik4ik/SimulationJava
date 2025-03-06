package game.entities.statics;

import game.entities.EntitiesRepresentation;
import game.entities.Entity;
import game.entities.Coordinates;

public class Tree extends Entity {

    public Tree(Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.TREE.getEntityRepresentation();
    }
}
