package game.utils.actions;

import game.entities.Coordinates;
import game.entities.WorldMap;
import game.entities.statics.Grass;
import game.entities.statics.Rock;
import game.entities.statics.Tree;
import game.utils.HerbivoreSpawner;
import game.utils.PredatorSpawner;
import game.utils.Spawner;

import java.util.List;

public class SpawnAction implements Action {
    private final Config config;

    public SpawnAction(Config config) {
        this.config = config;
    }

    @Override
    public void execute(WorldMap worldMap) {
        Coordinates defaultCoordinates = new Coordinates(-1, -1);

        List<Spawner> spawners = List.of(
                new Spawner(worldMap, ()-> new Tree(defaultCoordinates), calcBalance(config.balance.tree)),
                new Spawner(worldMap, ()-> new Rock(defaultCoordinates), calcBalance(config.balance.rock)),
                new Spawner(worldMap, ()-> new Grass(
                        defaultCoordinates,
                        config.entitiesSettings.grass.maxHealthPoints),
                        calcBalance(config.balance.grass)
                        ),
                new HerbivoreSpawner(
                        worldMap,
                        calcBalance(config.balance.herbivore),
                        config.entitiesSettings.herbivore.moveSpeed,
                        config.entitiesSettings.herbivore.maxHealthPoints,
                        config.entitiesSettings.herbivore.maxHungry
                        ),
                new PredatorSpawner(
                        worldMap,
                        calcBalance(config.balance.predator),
                        config.entitiesSettings.predator.moveSpeed,
                        config.entitiesSettings.predator.maxHealthPoints,
                        config.entitiesSettings.predator.maxHungry
                )
        );
        spawners.forEach(Spawner::spawn);
    }

    private int calcBalance(double k) {
        return (int) ((config.mapSize.mapHeight * config.mapSize.mapWidth) * k);
    }

}