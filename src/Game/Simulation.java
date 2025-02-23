package Game;

import Game.Entities.Dynamic.Creature;
import Game.Entities.WorldMap;
import Game.Utils.Actions.Action;
import Game.Utils.Actions.MoveCreaturesAction;
import Game.Utils.Actions.PlaceEntitiesAction;
import Game.Utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public WorldMap worldMap = new WorldMap(10, 10);
    public Renderer renderer = new Renderer(worldMap);
    public int turnsCount = 0;
    List<? extends Action> initActionsList = new ArrayList<>(List.of(new PlaceEntitiesAction()));
    List<? extends Action> turnActionsList = new ArrayList<>(List.of(new MoveCreaturesAction()));

    public void nextTurn() {
        for (Action action: turnActionsList) {
            action.execute(worldMap);
        }
        renderer.renderMap();
        System.out.println(turnsCount++);
    }

    public void startSimulation() {
        initGame();

        while (!worldMap.collectTargetEntity(Creature.class).isEmpty()) {
            try {
                Thread.sleep(2000);
                nextTurn();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initGame() {
        int a = 0;
        for (Action action: initActionsList) {
            action.execute(worldMap);
        }
    }

}
