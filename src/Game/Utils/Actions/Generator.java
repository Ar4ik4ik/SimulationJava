package Game.Utils.Actions;

import Game.Entities.Coordinates;
import Game.Entities.Dynamic.Herbivore;
import Game.Entities.Dynamic.Predator;
import Game.Entities.Entity;
import Game.Entities.Static.Grass;
import Game.Entities.Static.Rock;
import Game.Entities.Static.Tree;
import Game.Entities.WorldMap;

import java.io.IOException;

public abstract class Generator<T extends Entity> {

    protected final WorldMap mapInstance;
    protected final Config config;

    {
        try {
            config = ConfigLoader.loadConfig("src/Game/config.json");
        } catch (IOException e) {
            System.out.println("Конфиг не загружен");
            throw new RuntimeException(e);
        }
    }

    protected Object getEntitySettings(Class<T> entityClass) {
        if (entityClass == Herbivore.class) return config.entitiesSettings.herbivore;
        if (entityClass == Predator.class) return config.entitiesSettings.predator;
        if (entityClass == Grass.class) return config.entitiesSettings.grass;
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
        Config.EntitiesSettings.HerbivoreSettings settings = (Config.EntitiesSettings.HerbivoreSettings) getEntitySettings(Herbivore.class);
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
        Config.EntitiesSettings.PredatorSettings settings = (Config.EntitiesSettings.PredatorSettings) getEntitySettings(Predator.class);
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
        Config.EntitiesSettings.GrassSettings settings = (Config.EntitiesSettings.GrassSettings) getEntitySettings(Grass.class);
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








