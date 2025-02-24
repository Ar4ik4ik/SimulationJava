package Game;


import Game.Entities.Coordinates;
import Game.Entities.Static.Rock;
import Game.Entities.WorldMap;
import Game.Utils.PathFinder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PathFinderTest {

    @Test
    void testCreatePathFindsShortestPath() {
        // Подготовка карты
        WorldMap map = new WorldMap();
        Coordinates start = new Coordinates(2, 2);
        Coordinates target = new Coordinates(5, 5);

        // Добавляем препятствие
        map.placeOnMap(new Rock(new Coordinates(3, 3)));
        map.placeOnMap(new Rock(new Coordinates(4, 4)));

        // Выполняем поиск пути
        List<Coordinates> path = PathFinder.createPath(start, target, map);

        // Проверяем, что путь не пустой и ведет к цели
        assertFalse(path.isEmpty(), "Путь не должен быть пустым");
        assertNotEquals(target, path.getLast(), "Последняя точка пути не должна совпадать с целью");
    }

    @Test
    void testCreatePathReturnsEmptyIfNoPath() {
        WorldMap map = new WorldMap();
        Coordinates start = new Coordinates(1, 1);
        Coordinates target = new Coordinates(3, 3);

        // Полностью блокируем путь
        map.placeOnMap(new Rock(new Coordinates(2, 2)));
        map.placeOnMap(new Rock(new Coordinates(2, 3)));
        map.placeOnMap(new Rock(new Coordinates(2, 4)));
        map.placeOnMap(new Rock(new Coordinates(3, 2)));
        map.placeOnMap(new Rock(new Coordinates(3, 4)));
        map.placeOnMap(new Rock(new Coordinates(4, 4)));
        map.placeOnMap(new Rock(new Coordinates(4, 3)));
        map.placeOnMap(new Rock(new Coordinates(4, 2)));

        List<Coordinates> path = PathFinder.createPath(start, target, map);

        assertTrue(path.isEmpty(), "Путь должен быть пустым, если нет возможного маршрута");
    }
}
