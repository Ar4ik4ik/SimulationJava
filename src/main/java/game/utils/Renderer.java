package game.utils;

import game.entities.Coordinates;
import game.entities.Entity;
import game.entities.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    WorldMap worldMap;

    public Renderer(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void renderMap() {

        List<String> rows = new ArrayList<>();
        rows.add("=".repeat(worldMap.width).repeat(2));


        for (int row = 0; row < worldMap.height; row++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int col = 0; col < worldMap.width; col++) {
                Entity entity = worldMap.getEntityByCoords(new Coordinates(row, col));
                if (entity != null) {
                    rowBuilder.append(entity);
                } else {
                    rowBuilder.append("⬜");
                }
            }
            rows.add(rowBuilder.toString());
        }
        rows.add("=".repeat(worldMap.width).repeat(2));

        for (String row: rows) {
            System.out.println(row);
        }
    }

}
