package Entities.Dynamic;

import Entities.DynamicalHealth;
import Entities.Entity;
import Entities.WorldMap;
import Utils.Coordinates;
import Utils.PathFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Utils.PathFinder.getNeighbors;

public abstract class Creature<T extends Entity> extends Entity implements DynamicalHealth {

    // think about loading from cfg file
    int healthPoints = 100;
    int hunger = 200;
    int moveSpeed;
    final WorldMap mapInstance;
    Class<T> food;

    private boolean isAlive = true; // think about observer pattern

    // makeMove
    public void makeMove() {
        if (this.hunger <= 50) {
            // think about return type & method parameter type (smthing like interface)
            T foodObj = searchFood();
            if (foodObj != null) {
                // create path
                List<Coordinates> path = PathFinder.createPath(this.getEntityCoords(), foodObj.getEntityCoords(), mapInstance);
                if (!path.isEmpty()) {
                    if (path.size() == 1) {
                        this.interactWithFood(foodObj);
                        return;
                    } else {
                        int nextStepIndex = Math.min(moveSpeed - 1, path.size() - 1);
                        mapInstance.moveEntity(this, path.get(nextStepIndex));
                    }
                }
            }
        }
        doRandomMove();
        toHungry();
    }
    // think about method parameter type (smthing like interface)
    protected Creature(Coordinates entityCoords, WorldMap mapInstance, Class<T> food) {
        super(entityCoords);
        this.mapInstance = mapInstance;
        this.food = food;
    }

    protected void doRandomMove() {

        List<Coordinates> neighbors = new ArrayList<>(getNeighbors(this.getEntityCoords(), mapInstance).keySet());
        if (!neighbors.isEmpty()) {
            Random random = new Random();
            mapInstance.moveEntity(this, neighbors.get(random.nextInt(neighbors.size())));
        }

    }

    protected void setHungerValue(int value) {
        if (hunger + value <= 0) {
            setHealthPoints(-5);
        } else if (hunger + value >= 200) {
            hunger = 200;
        } else {
            hunger += value;
        }
        if (hunger >= 100 && healthPoints < 100) {
            toHeal();
        }
    }

    public void setHealthPoints(int value) {
        if (healthPoints + value <= 0) {
            isAlive = false;
        } else if (healthPoints + value >= 100) {
            healthPoints = 100;
        } else {
            healthPoints += value;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void toHeal() {
        setHealthPoints(5);
    }

    private void toHungry() {
        setHungerValue(-10);
    }


    // think about return type & method parameter type (smthing like interface)
    protected T searchFood() {
        return mapInstance.findNearestEntity(this.getEntityCoords(), food);
    }

    protected abstract void interactWithFood(T pray);


}
