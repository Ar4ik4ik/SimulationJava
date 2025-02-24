package Game.Utils;

import Game.Entities.Coordinates;
import Game.Entities.WorldMap;

public interface MovementStrategy {
    public Coordinates move(Coordinates startCoordinates, WorldMap worldMap);
}
