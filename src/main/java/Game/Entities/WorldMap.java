package Game.Entities;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    public final int WIDTH; // x coordinate
    public final int HEIGHT; // y coordinate

    private final Map<Coordinates, Entity> entitiesByCoords = new HashMap<>();

    public WorldMap(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public boolean isWithinBorders(Coordinates coords) {
        // Negative check in Coordinates.java (x & y can't be like -1)
        return coords.x() < WIDTH && coords.y() < HEIGHT;
    }

    private boolean replaceCoords(Entity entity, Coordinates newEntityCoords) {
        if (isWithinBorders(newEntityCoords) && isEmpty(newEntityCoords)) {
            entity.updateCoordinates(newEntityCoords);
            return true;
        }
        return false;
    }

    public boolean placeOnMap(Entity entity) {
        if (isEmpty(entity.getCoordinates())) {
            entitiesByCoords.put(entity.getCoordinates(), entity);
            return true;
        }
        return false;
    }

    public void deleteFromMap(Entity entity) {
        entitiesByCoords.remove(entity.getCoordinates());
    }

    public void moveEntity(Entity entity, Coordinates newEntityCoords) {
        Coordinates oldEntityCoords = entity.getCoordinates();
         if (replaceCoords(entity, newEntityCoords)) {
             entitiesByCoords.remove(oldEntityCoords);
             placeOnMap(entity);
         }
    }

    public boolean isEmpty(Coordinates coords) {
        return !entitiesByCoords.containsKey(coords);
    }

    public <T extends Entity> Map<Coordinates, T> collectTargetEntity(Class<T> entityType) {
        Map<Coordinates, T> outputMap = new HashMap<>();
        for (Map.Entry<Coordinates, Entity> entry: this.entitiesByCoords.entrySet()) {
            if (entityType.isInstance(entry.getValue())) {
                outputMap.put(entry.getKey(), entityType.cast(entry.getValue()));
            }
        }
        return outputMap;
    }

    public Entity getEntityByCoords(Coordinates coords) {
        return entitiesByCoords.get(coords);
    }

    public <T extends Entity> T getEntityByCoords(Coordinates coords, Class<T> type) {
        Entity entity = entitiesByCoords.get(coords);
        if (type.isInstance(entity)) {
            return type.cast(entity);
        }
        return null;
    }



    public Map<Coordinates, Entity> getEntitiesByCoords() {
        return new HashMap<>(entitiesByCoords);
    }

}
