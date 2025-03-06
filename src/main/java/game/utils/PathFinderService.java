package game.utils;

import game.entities.Coordinates;
import game.entities.Entity;
import game.entities.WorldMap;

import java.util.*;

public class PathFinderService {

    private final static int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1},
            {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

    private static double heuristicDistance(Coordinates start, Coordinates target) {
        return Math.sqrt(Math.pow(start.x() - target.x(), 2) + Math.pow(start.y() - target.y(), 2));
    }


    public static <T extends Entity> T findNearestEntity(Coordinates seekerCoordinates, Class<T> target, WorldMap worldMap) {

        Queue<Node> openSet = new PriorityQueue<>();
        Set<Coordinates> closedSet = new HashSet<>();

        openSet.add(new Node(seekerCoordinates, null, 0, 0));

        while (!openSet.isEmpty()) {
            Node polledNode = openSet.poll();

            if (closedSet.contains(polledNode.position)) continue;
            closedSet.add(polledNode.position);

            Entity entity = worldMap.getEntityByCoords(polledNode.position);
            if (target.isInstance(entity)) {
                return target.cast(entity);
            }

            for (Map.Entry<Coordinates, Integer> entry: getNeighbors(polledNode.position, worldMap, true, null).entrySet()) {
                Coordinates neighbor = entry.getKey();
                int moveCost = entry.getValue();

                if (closedSet.contains(neighbor)) continue;
                Node neighborNode = new Node(neighbor, polledNode, polledNode.g + moveCost, 0);
                openSet.add(neighborNode);
            }
        }
        return null;
    }

    public static List<Coordinates> createPath(Coordinates startPosition, Coordinates targetPosition, WorldMap worldMap) {
        Queue<Node> openSet = new PriorityQueue<>();
        Set<Coordinates> closedSet = new HashSet<>();
        openSet.add(new Node(startPosition, null, 0, heuristicDistance(startPosition, targetPosition)));

        while (!openSet.isEmpty()) {
            Node polledNode = openSet.poll();

            if (polledNode.position.equals(targetPosition)) return reconstructPath(polledNode);
            closedSet.add(polledNode.position);

            for (Map.Entry<Coordinates, Integer> entry: getNeighbors(polledNode.position, worldMap,
                    false, targetPosition).entrySet()) {
                Coordinates neighborPosition = entry.getKey();
                int moveCost = entry.getValue();

                if (closedSet.contains(neighborPosition)) {
                    continue;
                }

                double newG = polledNode.g + moveCost;
                Node existingNode = null;

                for (Node node: openSet) {
                    if (node.position.equals(neighborPosition)) {
                        existingNode = node;
                        break;
                    }
                }

                if (existingNode == null || newG < existingNode.g) {
                    Node newNode = new Node(neighborPosition, polledNode, newG, heuristicDistance(neighborPosition, targetPosition));
                    openSet.add(newNode);
                }
            }

        }

        return new ArrayList<>();
    }

    public static Map<Coordinates, Integer> getNeighbors(Coordinates cellCoords, WorldMap worldMap, boolean includeOccupied, Coordinates targetCoords) {
        int x = cellCoords.x();
        int y = cellCoords.y();
        Map<Coordinates, Integer> validNeighbors = new HashMap<>();

        for (int[] direction: DIRECTIONS) {
            int nx = x + direction[0];
            int ny = y + direction[1];

            if (nx >= 0 && ny >= 0 && worldMap.isWithinBorders(new Coordinates(nx, ny))) {
                Coordinates neighborCellCoords = new Coordinates(nx, ny);

                if (worldMap.isEmpty(neighborCellCoords)) {
                    validNeighbors.put(neighborCellCoords, 1);
                } else if (neighborCellCoords.equals(targetCoords)) {
                    validNeighbors.put(neighborCellCoords, 1);
                } else if (includeOccupied) {
                    validNeighbors.put(neighborCellCoords, 1);
                }
            }
        }

        return validNeighbors;
    }

    private static List<Coordinates> reconstructPath(Node node) {
        List<Coordinates> Path = new ArrayList<>();

        while (node != null) {
            Path.add(node.position);
            node = node.parent;
        }
        Collections.reverse(Path);
        Path.removeFirst();
        return Path;
    }
}


class Node implements Comparable<Node>{

    Coordinates position;
    double g;
    double h;
    Node parent;

    Node(Coordinates position, Node parent, double g, double h) {
        this.g = g;
        this.h = h;
        this.position = position;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.g + this.h, other.g + other.h);
    }
}
