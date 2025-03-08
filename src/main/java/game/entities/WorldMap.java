package game.entities;

import game.entities.dynamic.Creature;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    public final int width;
    public final int height;
    private final Map<Coordinates, Entity> entitiesByCoords = new HashMap<>();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isWithinBorders(Coordinates coords) {
        // Negative check in Coordinates.java (x & y can't be like -1)
        return coords.x() >= 0 && coords.x() < width && coords.y() < height && coords.y() >= 0;
    }

    public boolean isCoordsValid(Coordinates coordinates) {
        return isWithinBorders(coordinates) && isEmpty(coordinates);
    }

    public void place(Entity entity, Coordinates coordinates) {
        if (isEmpty(coordinates)) {
            entitiesByCoords.put(coordinates, entity);
            return;
        }
        throw new IllegalArgumentException("Coordinates %s is already occupied".formatted(coordinates.toString()));
    }

    public void delete(Coordinates coordinates) {
        entitiesByCoords.remove(coordinates);
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

    public boolean isCreaturesOnMap() {
        return !collectTargetEntity(Creature.class).isEmpty();
    }

    public Coordinates getCoordsByEntity(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry: entitiesByCoords.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return new Coordinates(-1, -1);
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

    public Map<Coordinates, Entity> toMap() {
        return new HashMap<>(entitiesByCoords);
    }
}
