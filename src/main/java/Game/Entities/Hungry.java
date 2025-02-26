package Game.Entities;

public class Hungry {
    private final int maxHungry;
    private int hungry;

    public Hungry(int hungry) {
        this.hungry = hungry;
        this.maxHungry = hungry;
    }

    public int getHungry() {
        return hungry;
    }

    public void adjustHungry(int value, Health health) {
        if (hungry + value <= 0) {
            hungry = 0;
            health.adjustHealth(-5);
        } else if (hungry + value >= maxHungry) {
            hungry = maxHungry;
        } else {
            hungry += value;
        }
        if (hungry >= maxHungry / 2) {
            health.heal(10);
        }
    }

    public void starve(Health health) {
        adjustHungry(-5, health);
    }

    public boolean isHungry() {
        return hungry <= maxHungry / 2;
    }

}
