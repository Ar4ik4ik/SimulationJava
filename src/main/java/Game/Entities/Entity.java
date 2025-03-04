package Game.Entities;


import java.util.Objects;

public abstract class Entity {
    private Coordinates coordinates;

    protected Entity(Coordinates initCoords) {
        this.coordinates = Objects.requireNonNull(initCoords);
    }

    abstract public String toString();

    protected void updateCoordinates(Coordinates newEntityCoords) {
        this.coordinates = newEntityCoords;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
