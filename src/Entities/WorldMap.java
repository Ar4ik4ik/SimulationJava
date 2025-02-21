package Entities;

import Entities.Static.Grass;
import Utils.PathFinder;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    public final int WIDTH; // x coordinate
    public final int HEIGHT; // y coordinate

    // think about replace Creature to key value for fast coords replacing
    private final Map<Coordinates, Entity> worldMap = new HashMap<>();

    public WorldMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public boolean isWithinBorders(Coordinates coords) {
        // Negative check in Coordinates.java (x & y can't be like -1)
        return coords.x() <= WIDTH && coords.y() <= HEIGHT;
    }

    private void replaceCoords(Entity entity, Coordinates newEntityCoords) {
        if (isWithinBorders(newEntityCoords)) entity.setEntityCoords(newEntityCoords);
    }

    public boolean placeOnMap(Entity entity) {
        if (isEmpty(entity.getEntityCoords())) {
            worldMap.put(entity.getEntityCoords(), entity);
            return true;
        }
        return false;
    }

    public void deleteFromMap(Entity entity) {
        worldMap.remove(entity.getEntityCoords());
    }

    public boolean moveEntity(Entity entity, Coordinates newEntityCoords) {
        if (isWithinBorders(newEntityCoords) && isEmpty(newEntityCoords)) {
            replaceCoords(entity, newEntityCoords);
            return true;
        }
        return false;
    }

    public boolean isEmpty(Coordinates coords) {
        return !worldMap.containsKey(coords);
    }

    public <T extends Entity> Map<Coordinates, T> collectTargetEntity(Class<T> entityType) {
        Map<Coordinates, T> outputMap = new HashMap<>();
        for (Map.Entry<Coordinates, Entity> entry: this.worldMap.entrySet()) {
            if (entityType.isInstance(entry.getValue())) {
                outputMap.put(entry.getKey(), entityType.cast(entry.getValue()));
            }
        }
        return outputMap;
    }

    public Entity getEntityByCoords(Coordinates coords) {
        return worldMap.get(coords);
    }

    public Map<Coordinates, Entity> getWorldMap() {
        return new HashMap<>(worldMap);
    }

    public <T extends Entity> T findNearestEntity(Coordinates seekerCoordinates, Class<T> target) {
        return PathFinder.findNearestEntity(seekerCoordinates, target, this);
    }

    public boolean isWalkableObj(Coordinates objCoords) {
        return worldMap.get(objCoords) instanceof Grass;
    }
}
