package Game.Entities;


public abstract class Entity {
    private Coordinates entityCoords;

    protected Entity(Coordinates initCoords) {
        this.entityCoords = initCoords;
    }

    abstract public String toString();

    public int getX() {
        return entityCoords.x();
    }

    public int getY() {
        return entityCoords.y();
    }

    protected void setEntityCoords(Coordinates newEntityCoords) {
        this.entityCoords = newEntityCoords;
    }

    public Coordinates getEntityCoords() {
        return entityCoords;
    }


}
