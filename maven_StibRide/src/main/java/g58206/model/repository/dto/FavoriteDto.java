package g58206.model.repository.dto;

import java.util.Objects;

public class FavoriteDto extends Dto<String> {

    private String origin;

    private String destination;

    public FavoriteDto(String key) {
        super(key);
    }

    public FavoriteDto(String key, String origin, String destination) {
        super(key);
        this.origin = origin;
        this.destination = destination;
    }

    public String getName() {
        return key;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteDto that = (FavoriteDto) o;
        return Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }

    @Override
    public String toString() {
        return "FavoriteDto{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
