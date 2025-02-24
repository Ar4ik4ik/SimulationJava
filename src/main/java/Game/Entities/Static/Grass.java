package Game.Entities.Static;

import Game.Entities.*;

public class Grass extends Entity implements LiveNature{

    public Health health;

    public Grass(Coordinates entityCoords, int maxHealthPoints) {
        super(entityCoords);
        this.health = new Health(maxHealthPoints);
    }

    @Override
    public String toString() {
        return EntitiesRepresentation.GRASS.getEntityRepresentation();
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
