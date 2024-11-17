package g58206.model.tools;

import java.util.Objects;

public class Edge {

    private final Node destination;

    private final int weight;

    public Edge(Node node, int weight) {
        this.destination = node;
        this.weight = weight;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight && Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, weight);
    }
}
