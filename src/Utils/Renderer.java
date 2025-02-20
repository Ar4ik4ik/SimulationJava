package Utils;

import Entities.Entity;
import Entities.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    WorldMap mapInstance;

    public Renderer(WorldMap mapInstance) {
        this.mapInstance = mapInstance;
    }

    public void renderMap() {

        List<String> rows = new ArrayList<>();

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

        for (String row: rows) {
            System.out.println(row);
        }
    }

}
