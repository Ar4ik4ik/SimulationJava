package Utils;

public final class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        if (x >= 0 || y >= 0) {
            this.x = x;
            this.y = y;
        } else throw new IllegalArgumentException("Attempted to set negative value for coords: \n" +
                ("X: %s\nY: %s").formatted(x, y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
