package Game.Entities.Dynamic;

import Game.Entities.EntitiesRepresentation;
import Game.Entities.WorldMap;
import Game.Entities.Coordinates;

public class Predator extends Creature<Herbivore> {

    public Predator(Coordinates entityCoords, WorldMap mapInstance,
                    int moveSpeed,
                    int maxHealthPoints,
                    int maxHungry) {
        super(entityCoords, mapInstance, Herbivore.class, moveSpeed, maxHealthPoints, maxHungry);
        this.moveSpeed = moveSpeed;
    }

    // private void interactWithFood(Herbivore foodObj)



    @Override
    protected void interactWithFood(Herbivore prey) {
        System.out.println(this + "Interact with: " + prey);
        prey.setCurrentHealthPoints(-25);
        if (!prey.isAlive()) {
            this.setHungryValue(100);
        }
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.PREDATOR.getEntityRepresentation();
    }
}