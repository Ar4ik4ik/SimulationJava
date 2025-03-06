package game.entities.dynamic;

import game.entities.*;
import game.utils.MovementStrategy;

public abstract class Creature<T extends Entity> extends Entity implements LiveNature{

    private final Hungry hungry;
    private final Health health;

    private final int moveSpeed;
    private final WorldMap worldMap;

    private final Class<T> food;
    private MovementStrategy movementStrategy;

    public Class<T> getFood() {
        return food;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    protected Creature(Coordinates entityCoords, WorldMap worldMap, Class<T> food,
                       int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(entityCoords);
        this.worldMap = worldMap;
        this.food = food;
        this.health = new Health(maxHealthPoints);
        this.hungry = new Hungry(maxHungry);
        this.moveSpeed = moveSpeed;
    }

    public void makeMove() {
        Coordinates nextStepCoords = movementStrategy.move(this.getCoordinates(), worldMap);
        T targetEntity = worldMap.getEntityByCoords(nextStepCoords, food);
        if (targetEntity != null) {
            processFoodInteraction(targetEntity);
            return;
        }
        makeStep(nextStepCoords);
        hungry.starve(health);
    }

    protected void makeStep(Coordinates newCoordinates) {
        if (worldMap.isCoordsValid(newCoordinates)) {
            worldMap.delete(this);
            this.updateCoordinates(newCoordinates);
            worldMap.place(this);
        }
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
