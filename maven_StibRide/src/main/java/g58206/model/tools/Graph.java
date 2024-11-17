package g58206.model.tools;

import g58206.exception.GraphException;
import g58206.exception.RepositoryException;
import g58206.model.repository.StationRepository;
import g58206.model.repository.StopRepository;
import g58206.model.repository.dto.StationDto;
import g58206.model.repository.dto.StopDto;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<Node> nodes;

    public Graph(StationRepository stationRep, StopRepository stopRep) throws GraphException {
        this.nodes = new ArrayList<>();
        populateGraph(stationRep, stopRep);
    }

    private void populateGraph(StationRepository stationRep, StopRepository stopRep) throws GraphException {
        try {
            List<StationDto> stations = stationRep.getAll();
            for (StationDto station : stations) {
                Node n = new Node(station);
                nodes.add(n);
            }
            for (Node n : nodes) {
                List<Pair<Integer, Integer>> linesAndOrders = stopRep.getLinesAndOrders(n.getStation());
                for (Pair<Integer, Integer> lineAndOrder : linesAndOrders) {
                    StopDto next = stopRep.getByLineAndOrder(lineAndOrder.getFirst(), lineAndOrder.getSecond() + 1);
                    if (next != null && !n.cotainsEdge(next.getStation())) {
                        n.addEdge(new Edge(new Node(next.getStation()), 1));
                    }
                    StopDto previous = stopRep.getByLineAndOrder(lineAndOrder.getFirst(), lineAndOrder.getSecond() - 1);
                    if (previous != null && !n.cotainsEdge(previous.getStation())) {
                        n.addEdge(new Edge(new Node(previous.getStation()), 1));
                    }
                }
            }
        } catch (RepositoryException e) {
            System.err.println(e.getMessage());
            throw new GraphException("Error while populating graph");
        }
    }

    public Node getNode(int key) throws GraphException {
        for (Node n : nodes) {
            if (n.getStation() == key) {
                return n;
            }
        }
        throw new GraphException("Unknown Node");
    }

    public Node getNode(Node key) throws GraphException {
        for (Node n : nodes) {
            if (n.getStation() == key.getStation()) {
                return n;
            }
        }
        throw new GraphException("Unknown Node");
    }

    public void clean() {
        for (Node n : nodes) {
            n.getShortestPath().clear();
            n.setDistance(Integer.MAX_VALUE);
        }
    }

    public void displayGraph() {
        for (Node n : nodes) {
            System.out.print("station-" + n.getStation() + " connected to [");
            for (Edge n2 : n.getEdges()) {
                System.out.print(n2.getDestination().getStation() + ",");
            }
            System.out.println("]");
        }
    }
}
