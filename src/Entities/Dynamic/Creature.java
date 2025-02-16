package Entities.Dynamic;

import Entities.Entity;
import Utils.Coordinates;

public abstract class Creature<T extends Entity> extends Entity {

    // think about loading from cfg file
    int healthPoints = 100;
    int hunger = 200;
    int moveSpeed;

    boolean isAlive = true; // think about observer pattern

    // makeMove
    public void makeMove() {
        if (this.healthPoints <= 50) {
            // think about return type & method parameter type (smthing like interface)
            T foodObj = searchFood();
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
    // think about method parameter type (smthing like interface)
    protected Creature(Coordinates entityCoords) {
        super(entityCoords);
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


    // think about return type & method parameter type (smthing like interface)
    public abstract T searchFood();

    protected abstract void interactWithFood(T prey);


}
