package Game.Entities.Dynamic;

import Game.Entities.*;
import Game.Utils.MovementStrategy;

public abstract class Creature<T extends Entity> extends Entity implements LiveNature{

    private final Hungry hungry;
    private final Health health;

    private final int moveSpeed;
    private final WorldMap mapInstance;

    public Class<T> getFood() {
        return food;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    private final Class<T> food;
    private MovementStrategy movementStrategy;

    protected Creature(Coordinates entityCoords, WorldMap mapInstance, Class<T> food,
                       int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(entityCoords);
        this.mapInstance = mapInstance;
        this.food = food;
        this.health = new Health(maxHealthPoints);
        this.hungry = new Hungry(maxHungry);
        this.moveSpeed = moveSpeed;
    }

    public void makeMove() {
        Coordinates nextStepCoords = movementStrategy.move(this.getCoordinates(), mapInstance);
        T targetEntity = mapInstance.getEntityByCoords(nextStepCoords, food);
        if (targetEntity != null) {
            processFoodInteraction(targetEntity);
            return;
        }
        mapInstance.moveEntity(this, nextStepCoords);

        hungry.starve(health);
    }

    @Override
    public Health getHealth() {
        return health;
    }

    public Hungry getHungry() {
        return hungry;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    protected abstract void processFoodInteraction(T prey);

}
