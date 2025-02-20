package Utils;

public class Config {
    public MapSize mapSize;
    public Balance balance;
    public EntitiesSettings entitiesSettings;

    public static class MapSize {
        public int mapHeight;
        public int mapWidth;
    }

    public static class Balance {
        public double herbivore;
        public double predator;
        public double tree;
        public double rock;
        public double grass;
    }
    public static class EntitiesSettings {

        Herbivore herbivore;
        Predator predator;
        Grass grass;

        public static class Herbivore {
            public int maxHealthPoints;
            public int maxHungry;
            public int moveSpeed;
        }

        public static class Predator {
            public int maxHealthPoints;
            public int maxHungry;
            public int moveSpeed;
        }

        public static class Grass {
            public int maxHealthPoints;
        }
    }
}

