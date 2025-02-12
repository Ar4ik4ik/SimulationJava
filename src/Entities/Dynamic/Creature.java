package Entities.Dynamic;

import Entities.Entity;
import Utils.Coordinates;

import java.util.Optional;

public abstract class Creature extends Entity {

    public final Entity food;
    int healthPoints = 100;
    int hunger = 200;
    int moveSpeed;
    boolean isAlive = true;

    // makeMove
    public void makeMove() {
        if (this.healthPoints <= 50) {
            Entity foodObj = searchFood();
            if (foodObj != null) {
                // create path
                // if creature near food -> interactWithFood
            } else {
                toHungry();
            }
        } else {
            toHungry();
        }
    }

    protected Creature(Coordinates entityCoords, Entity food) {
        super(entityCoords);
        this.food = food;
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

    protected boolean setHealthPoints(int value) {
        if (healthPoints + value <= 0) {
            isAlive = false;
            return false;
        } else if (healthPoints + value >= 100) {
            healthPoints = 100;
            return true;
        } else {
            healthPoints += value;
            return true;
        }
    }

    private void toHeal() {
        setHealthPoints(5);
    }

    private void toHungry() {
        setHungerValue(-10);
    }

    public abstract Entity searchFood();

    protected abstract void interactWithFood();


}
