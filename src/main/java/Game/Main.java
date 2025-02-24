package Game;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Thread simulationThread = new Thread(simulation);
        simulationThread.start();

        SimulationController simulationController = new SimulationController(simulation);
        Thread controllerThread = new Thread(simulationController);
        controllerThread.start();
    }
}

