package Game;

import Game.Entities.Dynamic.Creature;
import Game.Entities.WorldMap;
import Game.Utils.Actions.Action;
import Game.Utils.Actions.MoveCreaturesAction;
import Game.Utils.Actions.PlaceEntitiesAction;
import Game.Utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    public WorldMap worldMap = new WorldMap();
    public Renderer renderer = new Renderer(worldMap);
    public int turnsCount = 0;
    List<? extends Action> initActionsList = new ArrayList<>(List.of(new PlaceEntitiesAction()));
    List<? extends Action> turnActionsList = new ArrayList<>(List.of(new MoveCreaturesAction()));
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public void nextTurn() {
        for (Action action: turnActionsList) {
            action.execute(worldMap);
        }
        renderer.clearScreen();
        renderer.renderMap();
        System.out.printf("Ход %s%n", turnsCount++);
    }

    public void run() {
        initGame();

        while (running && !worldMap.collectTargetEntity(Creature.class).isEmpty()) {
            if (!paused) {
                nextTurn();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }


    private void initGame() {
        int a = 0;
        for (Action action: initActionsList) {
            action.execute(worldMap);
        }
    }

}
