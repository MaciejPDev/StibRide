package g58206.model.repository.dto;

import java.util.Objects;

public class LineDto extends Dto<Integer> {

    private int number;

    public LineDto(int key) {
        super(key);
    }

    public LineDto(int key, int number) {
        super(key);
        this.number = number;
    }

    @Override
    public String toString() {
        return "LineDto{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineDto lineDto = (LineDto) o;
        return number == lineDto.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public int getNumber() {
        return this.number;
    }

}
