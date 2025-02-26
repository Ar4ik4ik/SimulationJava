package Game;

import Game.Entities.Dynamic.Creature;
import Game.Entities.WorldMap;
import Game.Utils.Actions.*;
import Game.Utils.Renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    public WorldMap worldMap;
    public Renderer renderer;
    public int turnsCount = 0;
    List<? extends Action> initActionsList = new ArrayList<>(List.of(new PlaceEntitiesAction(), new MovementStrategyChooseAction()));
    List<? extends Action> turnActionsList = new ArrayList<>(List.of(new MoveCreaturesAction(), new CheckAliveEntityAction(), new MovementStrategyChooseAction()));
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public Simulation() {
        try {
            Config config = ConfigLoader.loadConfig("src/main/java/Game/config.json");
            int WIDTH = config.mapSize.mapWidth;
            int HEIGHT = config.mapSize.mapHeight;
            worldMap = new WorldMap(WIDTH, HEIGHT);
            renderer = new Renderer(worldMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
