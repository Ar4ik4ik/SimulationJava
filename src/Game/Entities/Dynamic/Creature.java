package Game.Entities.Dynamic;

import Game.Entities.DynamicalHealth;
import Game.Entities.Entity;
import Game.Entities.WorldMap;
import Game.Entities.Coordinates;
import Game.Utils.PathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Game.Utils.PathFinder.getNeighbors;

public abstract class Creature<T extends Entity> extends Entity implements DynamicalHealth {

    // think about loading from cfg file
    int maxHungry;
    int maxHealthPoints;

    int currentHealthPoints;
    int currentHungry;
    int moveSpeed;
    final WorldMap mapInstance;
    Class<T> food;

    private boolean isAlive = true; // think about observer pattern

    // makeMove
    public void makeMove() {
        if (this.currentHungry <= 50) {
            // think about return type & method parameter type (smthing like interface)
            T foodObj = searchFood();
            if (foodObj != null) {
                // create path
                List<Coordinates> path = PathFinder.createPath(this.getEntityCoords(), foodObj.getEntityCoords(), mapInstance);
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
        toHungry();
    }
    // think about method parameter type (smthing like interface)
    protected Creature(Coordinates entityCoords, WorldMap mapInstance, Class<T> food,
                       int moveSpeed, int maxHealthPoints, int maxHungry) {
        super(entityCoords);
        this.mapInstance = mapInstance;
        this.food = food;
        this.maxHealthPoints = maxHealthPoints;
        this.maxHungry = maxHungry;
        this.currentHungry = maxHungry;
        this.currentHealthPoints = maxHealthPoints;
        this.moveSpeed = moveSpeed;
    }

    protected void doRandomMove() {

        List<Coordinates> neighbors = new ArrayList<>(getNeighbors(this.getEntityCoords(), mapInstance).keySet());
        if (!neighbors.isEmpty()) {
            Random random = new Random();
            mapInstance.moveEntity(this, neighbors.get(random.nextInt(neighbors.size())));
        }

    }

    protected void setHungryValue(int value) {
        if (currentHungry + value <= 0) {
            currentHungry = 0;
            setCurrentHealthPoints(-5);
        } else if (currentHungry + value >= maxHungry) {
            currentHungry = maxHungry;
        } else {
            currentHungry += value;
        }
        if (currentHungry >= maxHungry && currentHealthPoints < maxHealthPoints) {
            toHeal();
        }
    }

    public void setCurrentHealthPoints(int value) {
        if (currentHealthPoints + value <= 0) {
            currentHealthPoints = 0;
            isAlive = false;
            mapInstance.deleteFromMap(this);
        } else if (currentHealthPoints + value >= maxHealthPoints) {
            currentHealthPoints = maxHealthPoints;
        } else {
            currentHealthPoints += value;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void toHeal() {
        setCurrentHealthPoints(5);
    }

    private void toHungry() {
        setHungryValue(-10);
    }


    // think about return type & method parameter type (smthing like interface)
    protected T searchFood() {
        return mapInstance.findNearestEntity(this.getEntityCoords(), food);
    }

    protected abstract void interactWithFood(T pray);


}
