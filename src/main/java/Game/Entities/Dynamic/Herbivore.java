package Game.Entities.Dynamic;

import Game.Entities.EntitiesRepresentation;
import Game.Entities.Static.Grass;
import Game.Entities.WorldMap;
import Game.Entities.Coordinates;

public class Herbivore extends Creature<Grass> {

    public Herbivore(Coordinates entityCoords, WorldMap mapInstance, int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(entityCoords, mapInstance, Grass.class, moveSpeed , maxHealthPoints, maxHungry);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.HERBIVORE.getEntityRepresentation();
    }

    @Override
    protected void processFoodInteraction(Grass prey) {
        if (!prey.getHealth().isDead()) {
            prey.getHealth().adjustHealth(-1);
            this.getHungry().adjustHungry(25, this.getHealth());
        }
    }

}
