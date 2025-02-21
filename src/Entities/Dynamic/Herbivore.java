package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Entities.Static.Grass;
import Entities.WorldMap;
import Entities.Coordinates;

public class Herbivore extends Creature<Grass> {

    public Herbivore(Coordinates entityCoords, WorldMap mapInstance, int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(entityCoords, mapInstance, Grass.class, moveSpeed , maxHealthPoints, maxHungry);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.HERBIVORE.getEntityRepresentation();
    }

    @Override
    protected void interactWithFood(Grass prey) {
        prey.setCurrentHealthPoints(-1);
        this.setHungryValue(25);
        if (!prey.isAlive()) mapInstance.deleteFromMap(prey);
    }

}
