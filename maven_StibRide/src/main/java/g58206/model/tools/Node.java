package g58206.model.tools;

import g58206.exception.RepositoryException;
import g58206.model.repository.StationRepository;
import g58206.model.repository.dto.StationDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {

    private final int station;

    private List<Node> shortestPath = new LinkedList<>();

    private int distance = Integer.MAX_VALUE;

    private final List<Edge> edges;

    public Node(int station) {
        this.station = station;
        this.edges = new ArrayList<>();
    }

    public Node(StationDto stationDto) {
        this.station = stationDto.getKey();
        this.edges = new ArrayList<>();
    }

    public int getStation() {
        return station;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(int i) {
        return edges.get(i);
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    public boolean cotainsEdge(int station) {
        for (Edge e : edges) {
            if (e.getDestination().getStation() == station) {
                return true;
            }
        }
        return false;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public List<Node> getShortestPath() {
        return this.shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public String toString() {
        StationRepository rep = new StationRepository();
        try {
            return rep.get(station).getName();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}
