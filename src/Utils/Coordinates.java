package Utils;

import java.util.Objects;

public record Coordinates(int x, int y) {

    public Coordinates {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Attempted to set negative value for coords: \n" +
                    ("X: %s\nY: %s").formatted(x, y));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
