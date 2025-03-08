package game.entities.statics;

import game.entities.*;

public class Grass extends Entity implements LiveNature{

    public Health health;

    public Grass(int maxHealthPoints) {
        this.health = new Health(maxHealthPoints);
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
