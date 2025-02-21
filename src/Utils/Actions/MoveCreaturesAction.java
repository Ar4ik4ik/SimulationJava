package Utils.Actions;


import Entities.Coordinates;
import Entities.Dynamic.Creature;
import java.util.Map;

public class MoveCreaturesAction implements Action {
    @Override
    public void execute(Simulation simulation) {
        Map<Coordinates, Creature> creatureMap = simulation.worldMap.collectTargetEntity(Creature.class);

        for (Map.Entry<Coordinates, Creature> creatureEntry: creatureMap.entrySet()) {
            creatureEntry.getValue().makeMove();
        }
    }
}
