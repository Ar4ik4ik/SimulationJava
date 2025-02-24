package Game.Entities;


import java.util.Objects;

public abstract class Entity {
    private Coordinates coordinates;

    protected Entity(Coordinates initCoords) {
        this.coordinates = Objects.requireNonNull(initCoords);;
    }

    abstract public String toString();

    public int getX() {
        return coordinates.x();
    }

    public int getY() {
        return coordinates.y();
    }

    protected void setCoordinates(Coordinates newEntityCoords) {
        this.coordinates = newEntityCoords;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
