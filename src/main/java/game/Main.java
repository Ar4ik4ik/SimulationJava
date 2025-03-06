package game;

import game.utils.actions.Config;
import game.utils.actions.ConfigLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Config config = ConfigLoader.loadConfig("src/main/java/Game/config.json");

        Simulation simulation = new Simulation(config);
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();

        SimulationController simulationController = new SimulationController(simulation);
        Thread controllerThread = new Thread(simulationController);
        controllerThread.start();
    }
}

