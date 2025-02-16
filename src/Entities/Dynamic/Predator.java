package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Utils.Coordinates;

public class Predator extends Creature<Herbivore> {

    public Predator(Coordinates entityCoords, int moveSpeed) {
        super(entityCoords);
        this.moveSpeed = moveSpeed;
    }

    // private void interactWithFood(Herbivore foodObj)



    @Override
    protected void interactWithFood(Herbivore pray) {
        pray.setHealthPoints(-25);
        if (!pray.isAlive) {
            this.setHungerValue(100);
            // think about observer
            // deleteFromMap(Herbivore pray)
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}