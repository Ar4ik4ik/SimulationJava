package game.utils;

import game.entities.Coordinates;
import game.entities.Entity;
import game.entities.WorldMap;

import java.util.List;

public class TargetMoveStrategy<T extends Entity> implements MovementStrategy {
    private final Class<T> target;
    private final int moveSpeed;

    public TargetMoveStrategy(Class<T> target, int moveSpeed) {
        this.target = target;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public Coordinates move(Coordinates startCoordinates, WorldMap worldMap) {
        T nearestEntity = PathFinderService.findNearestEntity(startCoordinates, target, worldMap);
        if (nearestEntity != null) {
            List<Coordinates> path = PathFinderService.createPath(startCoordinates, worldMap.getCoordsByEntity(nearestEntity), worldMap);
            if (path.isEmpty()) {
                // fallback if entity is blocked
                return new RandomMoveStrategy().move(startCoordinates, worldMap);
            } else {
                int index = Math.min(moveSpeed - 1, path.size() - 1);
                if (index == path.size() - 1 && path.size() > 1) {
                    return path.get(index - 1);
                } else {
                    return path.get(index);
                }
            }
        } else {
            // fallback if no path
            return new RandomMoveStrategy().move(startCoordinates, worldMap);
        }
    }
}
