package g58206.model.tools;

import g58206.exception.GraphException;

import java.util.*;

public class Dijkstra {

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) throws GraphException {
        source.getShortestPath().clear();
        graph.clean();

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Edge e : currentNode.getEdges()) {
                Node neighbour = graph.getNode(e.getDestination());
                if (!settledNodes.contains(neighbour)) {
                    calculateMinimumDistance(neighbour, e.getWeight(), currentNode);
                    unsettledNodes.add(neighbour);
                }
            }
            currentNode.getShortestPath().add(currentNode);
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(Node evaluationNode, int edgeWeigh, Node sourceNode) {
        int sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            List<Node> shortestPath = new ArrayList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
