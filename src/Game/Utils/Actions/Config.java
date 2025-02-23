package Game.Utils.Actions;

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
        public HerbivoreSettings herbivore;
        public PredatorSettings predator;
        public GrassSettings grass;

        public static class HerbivoreSettings {
            public int maxHealthPoints;
            public int maxHungry;
            public int moveSpeed;
        }

        public static class PredatorSettings {
            public int maxHealthPoints;
            public int maxHungry;
            public int moveSpeed;
        }

        public static class GrassSettings {
            public int maxHealthPoints;
        }
    }
}


