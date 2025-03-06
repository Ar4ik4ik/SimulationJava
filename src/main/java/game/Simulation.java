package game;

import game.entities.WorldMap;
import game.utils.actions.*;
import game.utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    public WorldMap worldMap;
    public Renderer renderer;
    public int turnsCount = 0;
    List<Action> initActionsList = new ArrayList<>(List.of(new MovementStrategyChooseAction()));
    List<Action> turnActionsList = new ArrayList<>(List.of(new MoveCreaturesAction(), new CheckAliveEntityAction(), new MovementStrategyChooseAction()));
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public Simulation(Config config) {
        initActionsList.addFirst(new SpawnAction(config));
        int WIDTH = config.mapSize.mapWidth;
        int HEIGHT = config.mapSize.mapHeight;
        worldMap = new WorldMap(WIDTH, HEIGHT);
        renderer = new Renderer(worldMap);
    }

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

        while (isRunningAndCreaturesOnMap()) {
            if (!paused) {
                nextTurn();
            }
            try {
                Thread.sleep(100);
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

    private boolean isRunningAndCreaturesOnMap() {
        return running && worldMap.isCreaturesOnMap();
    }

    private void initGame() {
        for (Action action: initActionsList) {
            action.execute(worldMap);
        }
    }

}
