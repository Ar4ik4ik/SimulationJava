package game.utils.actions;

import game.entities.Coordinates;
import game.entities.Entity;
import game.entities.LiveNature;
import game.entities.WorldMap;

import java.util.Map;

public class CheckAliveEntityAction implements Action{
    @Override
    public void execute(WorldMap worldMap) {
        for (Map.Entry<Coordinates, Entity> entry: worldMap.toMap().entrySet()) {
            if (entry.getValue() instanceof LiveNature && ((LiveNature) entry.getValue()).getHealth().isDead()) {
                worldMap.delete(entry.getKey());
            }
        }
    }
}
