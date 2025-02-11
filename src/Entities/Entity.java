package Entities;
import Utils.Coordinates;


public abstract class Entity {
    private Coordinates entityCoords;

    protected Entity(Coordinates initCoords) {
        this.entityCoords = initCoords;
    }

    abstract public String toString();

    public int getX() {
        return entityCoords.getX();
    }

    public int getY() {
        return entityCoords.getY();
    }

    protected void setEntityCoords(Coordinates newEntityCoords) {
        this.entityCoords = newEntityCoords;
    }

    public Coordinates getEntityCoords() {
        return entityCoords;
    }


}
