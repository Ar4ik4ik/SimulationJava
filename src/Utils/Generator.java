package Utils;

import Entities.Dynamic.Herbivore;
import Entities.Dynamic.Predator;
import Entities.Entity;
import Entities.Static.Grass;
import Entities.Static.Rock;
import Entities.Static.Tree;
import Entities.WorldMap;

import java.io.IOException;

public abstract class Generator<T extends Entity> {
    protected static WorldMap mapInstance;

    public static Config config;

    static {
        try {
            config = ConfigLoader.loadConfig("config.json");
        } catch (IOException e) {
            System.out.println("Конфиг не загружен");
            throw new RuntimeException(e);
        }
    }
    public abstract T generateEntity(Coordinates coordinates);

}

class HerbivoreGenerator extends Generator<Herbivore> {

    public HerbivoreGenerator(WorldMap mapInstance) {
        Generator.mapInstance = mapInstance;
    }

    @Override
    public Herbivore generateEntity(Coordinates coordinates) {
        return new Herbivore(coordinates, mapInstance,
                config.entitiesSettings.herbivore.moveSpeed,
                config.entitiesSettings.herbivore.maxHealthPoints,
                config.entitiesSettings.herbivore.maxHungry);
    }
}
class PredatorGenerator extends Generator<Predator> {

    public PredatorGenerator(WorldMap mapInstance) {
        Generator.mapInstance = mapInstance;
    }

    @Override
    public Predator generateEntity(Coordinates coordinates) {
        return new Predator(coordinates, mapInstance,
                config.entitiesSettings.herbivore.moveSpeed,
                config.entitiesSettings.herbivore.maxHealthPoints,
                config.entitiesSettings.herbivore.maxHungry);
    }
}
class GrassGenerator extends Generator<Grass> {

    @Override
    public Grass generateEntity(Coordinates coordinates) {
        return new Grass(coordinates, config.entitiesSettings.grass.maxHealthPoints);
    }
}
class TreeGenerator extends Generator<Tree> {

    @Override
    public Tree generateEntity(Coordinates coordinates) {
        return new Tree(coordinates);
    }
}
class RockGenerator extends Generator<Rock> {

    @Override
    public Rock generateEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}








