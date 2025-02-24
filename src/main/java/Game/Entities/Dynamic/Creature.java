package Game.Entities.Dynamic;

import Game.Entities.*;
import Game.Utils.PathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Game.Utils.PathFinder.getNeighbors;

public abstract class Creature<T extends Entity> extends Entity implements LiveNature{

    Hungry hungry;
    Health health;

    int moveSpeed;
    final WorldMap mapInstance;
    Class<T> food;

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
        if (hungry.getHungry() <= 50) {

            T foodObj = searchFood();
            if (foodObj != null) {

                List<Coordinates> path = PathFinder.createPath(this.getCoordinates(), foodObj.getCoordinates(), mapInstance);
                if (path.isEmpty()) {
                    this.interactWithFood(foodObj);
                } else {
                    int nextStepIndex = Math.min(moveSpeed - 1, path.size() - 1);
                    mapInstance.moveEntity(this, path.get(nextStepIndex));
                }
                return;
            }
        }
        doRandomMove();
        hungry.starve(health);
    }

    protected void doRandomMove() {

        List<Coordinates> neighbors = new ArrayList<>(getNeighbors(this.getCoordinates(), mapInstance, false, null).keySet());
        if (!neighbors.isEmpty()) {
            Random random = new Random();
            mapInstance.moveEntity(this, neighbors.get(random.nextInt(neighbors.size())));
        }

    }

    @Override
    public Health getHealth() {
        return health;
    }

    protected T searchFood() {
        return mapInstance.findNearestEntity(this.getCoordinates(), food);
    }

    protected abstract void interactWithFood(T pray);

}
