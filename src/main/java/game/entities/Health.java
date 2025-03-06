package game.entities;

public class Health {
    private final int maxHealth;
    public int health;
    private final static int HEALTH_REDUCING_RATE = -5;
    private final static int HEALTH_INCREASING_RATE = 10;


    public Health(int health) {
        this.health = health;
        this.maxHealth = health;
    }

    public void adjustHealth(int value) {
        if (health + value <= 0) {
            health = 0;
        } else if (health + value >= maxHealth) {
            health = maxHealth;
        } else {
            health += value;
        }
    }

    public void reduceHealth() {
        adjustHealth(HEALTH_REDUCING_RATE);
    }

    public void increaseHealth() {
        adjustHealth(HEALTH_INCREASING_RATE);
    }

    public boolean isDead() {
        return health == 0;
    }
}
