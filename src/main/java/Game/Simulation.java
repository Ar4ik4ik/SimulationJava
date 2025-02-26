package Game;

import Game.Entities.Dynamic.Creature;
import Game.Entities.WorldMap;
import Game.Utils.Actions.*;
import Game.Utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    public WorldMap worldMap = new WorldMap();
    public Renderer renderer = new Renderer(worldMap);
    public int turnsCount = 0;
    List<? extends Action> initActionsList = new ArrayList<>(List.of(new PlaceEntitiesAction(), new MoveStategyChooseAction()));
    List<? extends Action> turnActionsList = new ArrayList<>(List.of(new MoveCreaturesAction(), new CheckAliveEntityAction(), new MoveStategyChooseAction()));
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
                Thread.sleep(2000);
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
        for (Action action: initActionsList) {
            action.execute(worldMap);
        }
    }

}
