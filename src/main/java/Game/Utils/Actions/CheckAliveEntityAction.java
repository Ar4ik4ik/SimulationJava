package Game.Utils.Actions;

import Game.Entities.Coordinates;
import Game.Entities.Entity;
import Game.Entities.LiveNature;
import Game.Entities.WorldMap;

import java.util.Map;

public class CheckAliveEntityAction implements Action{
    @Override
    public void execute(WorldMap worldMap) {
        for (Map.Entry<Coordinates, Entity> entry: worldMap.getEntitiesByCoords().entrySet()) {
            if (entry.getValue() instanceof LiveNature && ((LiveNature) entry.getValue()).getHealth().isDead()) {
                worldMap.deleteFromMap(entry.getValue());
            }
        }
    }
}
