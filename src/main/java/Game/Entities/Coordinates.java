package Game.Entities;

public record Coordinates(int x, int y) {

    public Coordinates {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Attempted to set negative value for coords: \n" +
                    ("X: %s\nY: %s").formatted(x, y));
        }
    }
}