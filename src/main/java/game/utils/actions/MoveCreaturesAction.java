package game.utils.actions;


import game.entities.Coordinates;
import game.entities.dynamic.Creature;
import game.entities.WorldMap;

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
