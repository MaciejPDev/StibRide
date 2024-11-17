package g58206.model.tools;

public class Pair<K, I> {

    private final K first;
    private final I second;

    public Pair(K first, I second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public K getFirst() {
        return first;
    }

    public I getSecond() {
        return second;
    }
}
