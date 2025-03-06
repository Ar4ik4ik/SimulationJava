package game.entities;

public class Hungry {
    private final int maxHungry;
    private int hungryPoint;

    private static final int HUNGRY_REDUCING_RATE = -5;

    public Hungry(int hungryPoint) {
        this.hungryPoint = hungryPoint;
        this.maxHungry = hungryPoint;
    }

    public void adjustHungry(int value, Health health) {
        if (hungryPoint + value <= 0) {
            hungryPoint = 0;
            health.reduceHealth();
        } else if (hungryPoint + value >= maxHungry) {
            hungryPoint = maxHungry;
        } else {
            hungryPoint += value;
        }
        if (hungryPoint >= (maxHungry / 2)) {
            health.increaseHealth();
        }
    }

    public void starve(Health health) {
        adjustHungry(HUNGRY_REDUCING_RATE, health);
    }

    public boolean isHungry() {
        return hungryPoint <= maxHungry / 2;
    }

}
