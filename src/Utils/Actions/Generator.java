package Utils.Actions;

import Entities.Coordinates;
import Entities.Dynamic.Herbivore;
import Entities.Dynamic.Predator;
import Entities.Entity;
import Entities.Static.Grass;
import Entities.Static.Rock;
import Entities.Static.Tree;
import Entities.WorldMap;

import java.io.IOException;

public abstract class Generator<T extends Entity> {

    protected final WorldMap mapInstance;
    protected final Config config;

    {
        try {
            config = ConfigLoader.loadConfig("config.json");
        } catch (IOException e) {
            System.out.println("Конфиг не загружен");
            throw new RuntimeException(e);
        }
    }

    protected Config.EntitiesSettings getEntitySettings(Class<T> entityClass) {
        if (entityClass.isInstance(Herbivore.class)) return config.entitiesSettings.herbivore;
        if (entityClass.isInstance(Predator.class)) return config.entitiesSettings.predator;
        if (entityClass.isInstance(Grass.class)) return config.entitiesSettings.grass;
        throw new IllegalArgumentException("Настройки не найдены для: " + entityClass.getSimpleName());
    }

    protected Generator(WorldMap mapInstance) {
        this.mapInstance = mapInstance;
    }

    public abstract T generateEntity(Coordinates coordinates);

}

class HerbivoreGenerator extends Generator<Herbivore> {

    public HerbivoreGenerator(WorldMap mapInstance) {
        super(mapInstance);
    }

    @Override
    public Herbivore generateEntity(Coordinates coordinates) {
        Config.EntitiesSettings.Herbivore settings = (Config.EntitiesSettings.Herbivore) getEntitySettings(Herbivore.class);
        return new Herbivore(coordinates, mapInstance,
                settings.moveSpeed,
                settings.maxHealthPoints,
                settings.maxHungry);
    }
}
class PredatorGenerator extends Generator<Predator> {

    public PredatorGenerator(WorldMap mapInstance) {
        super(mapInstance);
    }

    @Override
    public Predator generateEntity(Coordinates coordinates) {
        Config.EntitiesSettings.Predator settings = (Config.EntitiesSettings.Predator) getEntitySettings(Predator.class);
        return new Predator(coordinates, mapInstance,
                settings.moveSpeed,
                settings.maxHealthPoints,
                settings.maxHungry);
    }
}
class GrassGenerator extends Generator<Grass> {

    public GrassGenerator(WorldMap mapInstance) {
        super(mapInstance);
    }

    @Override
    public Grass generateEntity(Coordinates coordinates) {
        Config.EntitiesSettings.Grass settings = (Config.EntitiesSettings.Grass) getEntitySettings(Grass.class);
        return new Grass(coordinates, settings.maxHealthPoints);
    }
}
class TreeGenerator extends Generator<Tree> {

    public TreeGenerator(WorldMap mapInstance) {
        super(mapInstance);
    }

    @Override
    public Tree generateEntity(Coordinates coordinates) {
        return new Tree(coordinates);
    }
}
class RockGenerator extends Generator<Rock> {

    public RockGenerator(WorldMap mapInstance) {
        super(mapInstance);
    }

    @Override
    public Rock generateEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}








