package Game.Utils;

import Game.Entities.Coordinates;
import Game.Entities.Entity;
import Game.Entities.WorldMap;

import java.util.*;

public class PathFinder {

    private static double heuristicDistance(Coordinates start, Coordinates target) {
        return Math.sqrt(Math.pow(start.x() - target.x(), 2) + Math.pow(start.y() - target.y(), 2));
    }


    public static <T extends Entity> T findNearestEntity(Coordinates seekerCoordinates, Class<T> target, WorldMap mapInstance) {

        Queue<Node> openSet = new PriorityQueue<>();
        Set<Coordinates> closedSet = new HashSet<>();

        openSet.add(new Node(seekerCoordinates, null, 0, 0));

        while (!openSet.isEmpty()) {
            Node polledNode = openSet.poll();

            if (closedSet.contains(polledNode.position)) continue;
            closedSet.add(polledNode.position);

            Entity entity = mapInstance.getEntityByCoords(polledNode.position);
            if (target.isInstance(entity)) {
                return target.cast(entity);
            }

            for (Map.Entry<Coordinates, Integer> entry: getNeighbors(polledNode.position, mapInstance).entrySet()) {
                Coordinates neighbor = entry.getKey();
                int moveCost = entry.getValue();

                if (closedSet.contains(neighbor)) continue;
                Node neighborNode = new Node(neighbor, polledNode, polledNode.g + moveCost, 0);
                openSet.add(neighborNode);
            }
        }
        return null;
    }

    public static List<Coordinates> createPath(Coordinates startPosition, Coordinates targetPosition, WorldMap mapInstance) {
        Queue<Node> openSet = new PriorityQueue<>();
        Set<Coordinates> closedSet = new HashSet<>();
        openSet.add(new Node(startPosition, null, 0, heuristicDistance(startPosition, targetPosition)));

        while (!openSet.isEmpty()) {
            Node polledNode = openSet.poll();

            if (polledNode.position.equals(targetPosition)) return reconstructPath(polledNode);
            closedSet.add(polledNode.position);

            for (Map.Entry<Coordinates, Integer> entry: getNeighbors(polledNode.position, mapInstance).entrySet()) {
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

    public static Map<Coordinates, Integer> getNeighbors(Coordinates coords, WorldMap mapInstance) {
        int x = coords.x();
        int y = coords.y();
        int[][] DIRECTIONS = new int[][]{{x - 1, y}, {x, y - 1}, {x + 1, y}, {x, y + 1},
                {x - 1, y - 1}, {x + 1, y - 1}, {x - 1, y + 1}, {x + 1, y + 1}};
        Map<Coordinates, Integer> validNeighbors = new HashMap<>();

        for (int[] direction: DIRECTIONS) {
            int nx = direction[0];
            int ny = direction[1];

            if (nx >= 0 && ny >= 0 && mapInstance.isWithinBorders(new Coordinates(nx, ny))) {
                Coordinates neighborCellCoords = new Coordinates(nx, ny);

                if (mapInstance.isEmpty(neighborCellCoords)) {
                    validNeighbors.put(neighborCellCoords, 1);
                } else if (mapInstance.isWalkableObj(neighborCellCoords)) {
                    validNeighbors.put(neighborCellCoords, 2);
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
        Path.removeLast(); Path.removeFirst();
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
