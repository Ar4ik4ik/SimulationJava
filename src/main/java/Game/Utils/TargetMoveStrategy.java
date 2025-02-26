package Game.Utils;

import Game.Entities.Coordinates;
import Game.Entities.Entity;
import Game.Entities.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class TargetMoveStrategy<T extends Entity> implements MovementStrategy {
    Class<T> target;
    int moveSpeed;

    public TargetMoveStrategy(Class<T> target, int moveSpeed) {
        this.target = target;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public Coordinates move(Coordinates startCoordinates, WorldMap worldMap) {
        T nearestEntity = PathFinderService.findNearestEntity(startCoordinates, target, worldMap);
        if (nearestEntity != null) {
            List<Coordinates> path = PathFinderService.createPath(startCoordinates, nearestEntity.getCoordinates(), worldMap);
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
