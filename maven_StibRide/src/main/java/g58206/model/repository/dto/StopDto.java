package g58206.model.repository.dto;

import g58206.model.tools.Pair;

public class StopDto extends Dto<Pair<Integer, Integer>> {

    private final int order;

    public StopDto(int line, int station, int order) {
        super(new Pair<>(line, station));
        this.order = order;
    }

    public int getLine() {
        return getKey().getFirst();
    }

    public int getStation() {
        return getKey().getSecond();
    }

    public int getOrder() {
        return this.order;
    }

    @Override
    public String toString() {
        return "StopDto{" +
                "order=" + order +
                '}';
    }
}
