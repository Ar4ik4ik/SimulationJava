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

    public void renderMap() {

        List<String> rows = new ArrayList<>();
        rows.add("=".repeat(mapInstance.WIDTH));


        for (int i = 0; i < mapInstance.HEIGHT; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < mapInstance.WIDTH; j++) {
                Entity entity = mapInstance.getEntityByCoords(new Coordinates(i, j));
                if (entity != null) {
                    row.append(entity);
                } else {
                    row.append("⬜");
                }
            }
            rows.add(row.toString());
        }
        rows.add("=".repeat(mapInstance.WIDTH));

        for (String row: rows) {
            System.out.println(row);
        }
    }

}
