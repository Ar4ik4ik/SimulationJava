package Game.Entities.Static;

import Game.Entities.DynamicalHealth;
import Game.Entities.EntitiesRepresentation;
import Game.Entities.Entity;
import Game.Entities.Coordinates;

public class Grass extends Entity implements DynamicalHealth {
    int maxHealthPoints;
    int currentHealthPoints;
    boolean isAlive = true;

    public Grass(Coordinates entityCoords, int maxHealthPoints) {
        super(entityCoords);
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.GRASS.getEntityRepresentation();
    }

    @Override
    public void setCurrentHealthPoints(int value) {
        if (currentHealthPoints + value <= 0) {
            isAlive = false;
        } else if (currentHealthPoints + value >= maxHealthPoints) {
            currentHealthPoints = maxHealthPoints;
        } else {
            currentHealthPoints += value;
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

}
