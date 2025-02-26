package Game.Utils;

import Game.Entities.Coordinates;
import Game.Entities.Entity;
import Game.Entities.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    WorldMap mapInstance;

    public Renderer(WorldMap mapInstance) {
        this.mapInstance = mapInstance;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void renderMap() {

        List<String> rows = new ArrayList<>();
        rows.add("=".repeat(mapInstance.WIDTH).repeat(2));


        for (int row = 0; row < mapInstance.HEIGHT; row++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int col = 0; col < mapInstance.WIDTH; col++) {
                Entity entity = mapInstance.getEntityByCoords(new Coordinates(row, col));
                if (entity != null) {
                    rowBuilder.append(entity);
                } else {
                    rowBuilder.append("⬜");
                }
            }
            rows.add(rowBuilder.toString());
        }
        rows.add("=".repeat(mapInstance.WIDTH).repeat(2));

        for (String row: rows) {
            System.out.println(row);
        }
    }

}
