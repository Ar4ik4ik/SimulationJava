package game.utils;

import game.entities.Coordinates;
import game.entities.WorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.utils.PathFinderService.getNeighbors;

public class RandomMoveStrategy implements MovementStrategy{
    private static final Random RANDOM = new Random();
    @Override
    public Coordinates move(Coordinates startCoordinates, WorldMap worldMap) {
        List<Coordinates> neighbors = new ArrayList<>(getNeighbors(startCoordinates, worldMap, false, null).keySet());
        if (!neighbors.isEmpty()) {
            return neighbors.get(RANDOM.nextInt(neighbors.size()));
        }
        return startCoordinates;
    }
}
