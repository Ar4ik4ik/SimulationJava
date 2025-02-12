package Entities;

import Entities.Dynamic.Creature;
import Utils.Coordinates;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    protected final int WIDTH; // x coordinate
    protected final int HEIGHT; // y coordinate

    private final Map<Coordinates, Creature> entityWithCoords = new HashMap<>();


    public WorldMap(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    private boolean isWithinBorders(Coordinates coords) {
        // Negative check in Coordinates.java (x & y can't be like -1)
        return coords.x() <= WIDTH && coords.y() <= HEIGHT;
    }

    private void replaceCoords(Creature creature, Coordinates newCreatureCoords) {
        entityWithCoords.remove(creature.getEntityCoords());
        entityWithCoords.put(newCreatureCoords, creature);
    }

    private boolean placeOnMap(Creature creature) {
        if (isEmpty(creature.getEntityCoords())) {
            entityWithCoords.put(creature.getEntityCoords(), creature);
            return true;
        }
        return false;
    }

    public boolean moveCreature(Creature creature, Coordinates newCreatureCoords) {
        if (isWithinBorders(newCreatureCoords) && isEmpty(newCreatureCoords)) {
            creature.setEntityCoords(newCreatureCoords);
            replaceCoords(creature, newCreatureCoords);
            return true;
        }
        return false;
    }

    public boolean isEmpty(Coordinates coords) {
        return !entityWithCoords.containsKey(coords);
    }
}
