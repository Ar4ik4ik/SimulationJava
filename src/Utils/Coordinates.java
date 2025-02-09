package Utils;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        setCoords(x, y);
    }

    public int[] getCoords() {
        return new int[]{x, y};
    }

    public boolean setCoords(int newX, int newY) {
        if (newX >= 0 && newY >= 0) {
            this.x = newX;
            this.y = newY;
            return true;
        } else throw new RuntimeException("Attempted to set negative value for coords: \n" +
                        ("Old x {%s} to new x {%s}\n" +
                        "Old y {%s} to new y {%s}").formatted(this.x, newX, this.y, newY));
    }

}
