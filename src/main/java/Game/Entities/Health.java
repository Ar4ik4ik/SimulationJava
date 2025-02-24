package Game.Entities;

public class Health {
    private final int maxHealth;
    private int health;

    public Health(int health) {
        this.health = health;
        this.maxHealth = health;
    }

    public int getHealth() {
        return health;
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

    public void heal(int value) {
        adjustHealth(health + value);
    }

    public boolean isDead() {
        return health == 0;
    }
}
