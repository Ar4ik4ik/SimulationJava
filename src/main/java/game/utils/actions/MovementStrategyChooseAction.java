package game.utils.actions;

import game.entities.Coordinates;
import game.entities.dynamic.Creature;
import game.entities.WorldMap;
import game.utils.RandomMoveStrategy;
import game.utils.TargetMoveStrategy;

import java.util.Map;

public class MovementStrategyChooseAction implements Action{
    @Override
    public void execute(WorldMap worldMap) {
        for (Map.Entry<Coordinates, Creature> entry: worldMap.collectTargetEntity(Creature.class).entrySet()) {
            Creature<?> creature = entry.getValue();
            if (creature.getHungry().isHungry()) {
                creature.setMovementStrategy(new TargetMoveStrategy<>(creature.getFood(), creature.getMoveSpeed()));
            } else {
                creature.setMovementStrategy(new RandomMoveStrategy());
            }
        }
    }
}
