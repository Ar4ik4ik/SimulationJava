package Entities;
import Utils.Coordinates;


public abstract class Entity {
    protected Coordinates entityCoords;

    protected Entity(Coordinates entityCoords) {
        this.entityCoords = entityCoords;
    }

    abstract public String toString();
}
