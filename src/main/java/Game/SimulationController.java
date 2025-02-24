package Game;

import java.util.Scanner;

public class SimulationController implements Runnable{
    private final Simulation simulation;

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
                case "pause":
                    simulation.pause();
                    System.out.println("Симуляция приостановлена.");
                    break;
                case "resume":
                    simulation.resume();
                    System.out.println("Симуляция возобновлена.");
                    break;
                case "stop":
                    simulation.stop();
                    System.out.println("Симуляция остановлена.");
                    return;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }
}
