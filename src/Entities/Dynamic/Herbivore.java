package Entities.Dynamic;

import Entities.EntitiesRepresentation;
import Entities.Static.Grass;
import Entities.WorldMap;
import Utils.Coordinates;

public class Herbivore extends Creature<Grass> {

    public Herbivore(Coordinates entityCoords, int moveSpeed, WorldMap mapInstance) {
        super(entityCoords, mapInstance, Grass.class);
        this.moveSpeed = moveSpeed;
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.HERBIVORE.getEntityRepresentation();
    }

    @Override
    protected void interactWithFood(Grass prey) {
        prey.setHealthPoints(-1);
        this.setHungerValue(25);
    }

}
