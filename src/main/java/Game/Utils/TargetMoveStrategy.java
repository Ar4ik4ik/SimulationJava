package Game.Utils;

import Game.Entities.Coordinates;
import Game.Entities.Entity;
import Game.Entities.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class TargetMoveStrategy<T extends Entity> implements MovementStrategy{
    Class<T> target;
    int moveSpeed;

    public TargetMoveStrategy(Class<T> target, int moveSpeed) {
        this.target = target;
        this.moveSpeed = moveSpeed;
    }

    @Override
    public Coordinates move(Coordinates startCoordinates, WorldMap worldMap) {
        T nearestEntity = PathFinderService.findNearestEntity(startCoordinates, target, worldMap);
        List<Coordinates> path = new ArrayList<>();
        if (nearestEntity != null) {
            path = PathFinderService.createPath(startCoordinates, nearestEntity.getCoordinates(), worldMap);
        }

        return path.isEmpty() ? startCoordinates : path.get(Math.min(moveSpeed - 1, path.size() - 1));
    }
}
