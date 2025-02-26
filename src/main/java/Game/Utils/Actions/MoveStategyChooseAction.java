package Game.Utils.Actions;

import Game.Entities.Coordinates;
import Game.Entities.Dynamic.Creature;
import Game.Entities.WorldMap;
import Game.Utils.RandomMoveStrategy;
import Game.Utils.TargetMoveStrategy;

import java.util.Map;

public class MoveStategyChooseAction implements Action{
    @Override
    public void execute(WorldMap worldMap) {
        for (Map.Entry<Coordinates, Creature> entry: worldMap.collectTargetEntity(Creature.class).entrySet()) {
            Creature<?> creature = entry.getValue();
            if (creature.getHungry().isHungry()) {
                creature.setMovementStrategy(new TargetMoveStrategy<>(creature.food, creature.moveSpeed));
            } else {
                creature.setMovementStrategy(new RandomMoveStrategy());
            }
        }
    }
}
