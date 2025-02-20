package Entities.Static;

import Entities.EntitiesRepresentation;
import Entities.Entity;
import Utils.Coordinates;

public class Tree extends Entity {

    public Tree(Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.TREE.getEntityRepresentation();
    }
}
