package Entities.Static;

import Entities.DynamicalHealth;
import Entities.EntitiesRepresentation;
import Entities.Entity;
import Utils.Coordinates;

public class Grass extends Entity implements DynamicalHealth {

    int healthPoints = 4;
    boolean isAlive = true;

    Grass (Coordinates entityCoords) {
        super(entityCoords);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.GRASS.getEntityRepresentation();
    }

    @Override
    public void setHealthPoints(int value) {
        if (healthPoints + value <= 0) {
            isAlive = false;
        } else if (healthPoints + value >= 100) {
            healthPoints = 100;
        } else {
            healthPoints += value;
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

}
