package game.utils;

import game.entities.Coordinates;
import game.entities.Entity;
import game.entities.WorldMap;
import game.entities.dynamic.Herbivore;
import game.entities.dynamic.Predator;
import game.entities.statics.Grass;
import game.entities.statics.Rock;
import game.entities.statics.Tree;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    WorldMap worldMap;

    private static final String TREE = "\uD83D\uDFEB";      // 🟫
    private static final String GRASS = "\uD83D\uDFE9";     // 🟩
    private static final String ROCK = "\u2B1B";            // ⬛
    private static final String HERBIVORE = "\uD83D\uDC10"; // 🐐
    private static final String PREDATOR = "\uD83D\uDC3A";  // 🐺

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
                rowBuilder.append(getSprite(entity));
            }
            rows.add(rowBuilder.toString());
        }
        rows.add("=".repeat(worldMap.width).repeat(2));

        for (String row: rows) {
            System.out.println(row);
        }
    }

    private String getSprite(Entity entity) {
        return switch (entity) {
            case Rock rock -> ROCK;
            case Tree tree -> TREE;
            case Grass grass -> GRASS;
            case Herbivore herbivore -> HERBIVORE;
            case Predator predator -> PREDATOR;
            case null, default -> "⬜";
        };
    }

}
