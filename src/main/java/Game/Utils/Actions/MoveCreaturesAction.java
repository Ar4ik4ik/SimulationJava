package Game.Utils.Actions;


import Game.Entities.Coordinates;
import Game.Entities.Dynamic.Creature;
import Game.Entities.WorldMap;

import java.util.Map;

public class MoveCreaturesAction implements Action {
    @Override
    public void execute(WorldMap worldMap) {
        Map<Coordinates, Creature> creatureMap = worldMap.collectTargetEntity(Creature.class);

        for (Map.Entry<Coordinates, Creature> creatureEntry: creatureMap.entrySet()) {
            creatureEntry.getValue().makeMove();
        }
    }
}
