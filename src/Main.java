import Entities.Dynamic.Herbivore;
import Entities.Dynamic.Predator;
import Entities.Static.Grass;
import Entities.Static.Rock;
import Entities.Static.Tree;
import Entities.WorldMap;
import Utils.Coordinates;
import Utils.Renderer;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap(5, 5);
        worldMap.placeOnMap(new Rock(new Coordinates(0, 0)));
        worldMap.placeOnMap(new Tree(new Coordinates(0, 1)));
        worldMap.placeOnMap(new Grass(new Coordinates(0, 2)));
        worldMap.placeOnMap(new Herbivore(new Coordinates(0, 3), 2, worldMap));
        worldMap.placeOnMap(new Predator(new Coordinates(0, 4), 2, worldMap));
        Renderer renderer = new Renderer(worldMap);
        renderer.renderMap();
    }
}