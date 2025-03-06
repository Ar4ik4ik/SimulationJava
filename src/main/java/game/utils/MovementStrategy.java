package game.utils;

import game.entities.Coordinates;
import game.entities.WorldMap;

public interface MovementStrategy {
    Coordinates move(Coordinates startCoordinates, WorldMap worldMap);
}
