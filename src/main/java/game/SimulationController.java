package game;

import java.util.Scanner;

public class SimulationController implements Runnable{
    private final Simulation simulation;

    private final static String PAUSE = "pause";
    private final static String RESUME = "resume";
    private final static String STOP = "stop";

    SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду (pause/resume/stop): ");
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case PAUSE:
                    simulation.pause();
                    System.out.println("Симуляция приостановлена.");
                    break;
                case RESUME:
                    simulation.resume();
                    System.out.println("Симуляция возобновлена.");
                    break;
                case STOP:
                    simulation.stop();
                    System.out.println("Симуляция остановлена.");
                    return;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }
}
