package Entities.Dynamic;

import Entities.Entity;
import Utils.Coordinates;

public abstract class Creature extends Entity {

    int healthPoints = 100;
    int moveSpeed;

    // makeMove
    // protected abstract void interactWithFood();


    protected Creature(Coordinates entityCoords) {
        super(entityCoords);
    }


}
