package Game.Utils.Actions;

import Game.Entities.Coordinates;
import Game.Entities.Dynamic.Herbivore;
import Game.Entities.Dynamic.Predator;
import Game.Entities.Entity;
import Game.Entities.Static.Grass;
import Game.Entities.Static.Rock;
import Game.Entities.Static.Tree;
import Game.Entities.WorldMap;

import java.util.Map;
import java.util.Random;

public class PlaceEntitiesAction implements Action{
    private static final Random RANDOM = new Random();
    @Override
    public void execute(WorldMap worldMap) {
        for (Map.Entry<Class<? extends Entity>, Double> entry:
                ConfigLoader.getEntitiesBalanceFromConfig("src/main/java/Game/config.json").entrySet()) {
            int calculatedBalance = calcBalance(worldMap.HEIGHT, worldMap.WIDTH, entry.getValue());
            int counter = 0;
            Generator<?> generator = getGenerator(worldMap, entry);
            while (counter != calculatedBalance) {
                int randX = RANDOM.nextInt(worldMap.HEIGHT);
                int randY = RANDOM.nextInt(worldMap.WIDTH);
                
                if (worldMap.placeOnMap(generator.generateEntity(new Coordinates(randX, randY)))) counter++;
            }

        }
    }

    private static int calcBalance(int HEIGHT, int WIDTH, double entityBalance) {
        return (int) ((HEIGHT * WIDTH) * entityBalance);
    }

    private static Generator<?> getGenerator(WorldMap worldMap, Map.Entry<Class<? extends Entity>, Double> entry) {
        Generator<?> generator = null;

        if (entry.getKey() == Herbivore.class) generator = new HerbivoreGenerator(worldMap);
        if (entry.getKey() == Predator.class) generator = new PredatorGenerator(worldMap);
        if (entry.getKey() == Grass.class) generator = new GrassGenerator(worldMap);
        if (entry.getKey() == Tree.class) generator = new TreeGenerator(worldMap);
        if (entry.getKey() == Rock.class) generator = new RockGenerator(worldMap);
        return generator;
    }
}
