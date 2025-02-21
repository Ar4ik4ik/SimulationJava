package Utils.Actions;

public class Config {
    public MapSize mapSize;
    public Balance balance;
    public EntitiesSettings entitiesSettings;

    public static class MapSize extends Config{
        public int mapHeight;
        public int mapWidth;
    }

    public static class Balance extends Config{
        public double herbivore;
        public double predator;
        public double tree;
        public double rock;
        public double grass;
    }
    public static class EntitiesSettings extends Config{

        Herbivore herbivore;
        Predator predator;
        Grass grass;

        public static class Herbivore extends EntitiesSettings {
            public int maxHealthPoints;
            public int maxHungry;
            public int moveSpeed;
        }

        public static class Predator extends EntitiesSettings{
            public int maxHealthPoints;
            public int maxHungry;
            public int moveSpeed;
        }

        public static class Grass extends EntitiesSettings{
            public int maxHealthPoints;
        }
    }
}

