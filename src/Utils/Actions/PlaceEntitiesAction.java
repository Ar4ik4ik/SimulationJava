package Utils.Actions;

import Entities.Coordinates;
import Entities.Dynamic.Herbivore;
import Entities.Dynamic.Predator;
import Entities.Entity;
import Entities.Static.Grass;
import Entities.Static.Rock;
import Entities.Static.Tree;

import java.util.Map;
import java.util.Random;

public class PlaceEntitiesAction implements Action{
    @Override
    public void execute(Simulation simulation) {
        for (Map.Entry<Class<? extends Entity>, Double> entry:
                ConfigLoader.getEntitiesBalanceFromConfig("config.json").entrySet()) {
            int calculatedBalance = calcBalance(simulation.worldMap.HEIGHT, simulation.worldMap.WIDTH, entry.getValue());
            int counter = 0;
            Generator<?> generator = getGenerator(simulation, entry);
            while (counter != calculatedBalance) {
                int randX = new Random().nextInt(simulation.worldMap.HEIGHT);
                int randY = new Random().nextInt(simulation.worldMap.WIDTH);
                
                if (simulation.worldMap.placeOnMap(generator.generateEntity(new Coordinates(randX, randY)))) counter++;
            }

        }
    }

    private static int calcBalance(int HEIGHT, int WIDTH, double entityBalance) {
        return (int) ((HEIGHT * WIDTH) / entityBalance);
    }

    private static Generator<?> getGenerator(Simulation simulation, Map.Entry<Class<? extends Entity>, Double> entry) {
        Generator<?> generator = null;

        if (entry.getKey().isInstance(Herbivore.class)) generator = new HerbivoreGenerator(simulation.worldMap);
        if (entry.getKey().isInstance(Predator.class)) generator = new PredatorGenerator(simulation.worldMap);
        if (entry.getKey().isInstance(Grass.class)) generator = new GrassGenerator(simulation.worldMap);
        if (entry.getKey().isInstance(Tree.class)) generator = new TreeGenerator(simulation.worldMap);
        if (entry.getKey().isInstance(Rock.class)) generator = new RockGenerator(simulation.worldMap);
        return generator;
    }
}
