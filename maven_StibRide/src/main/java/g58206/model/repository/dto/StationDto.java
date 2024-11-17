package g58206.model.repository.dto;


public class StationDto extends Dto<Integer> {

    private String name;

    public StationDto(int key) {
        super(key);
    }

    public StationDto(int key, String name) {
        super(key);
        this.name = name;
    }

    @Override
    public String toString() {
        return "StationDto{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getNumber() {
        return key;
    }

    public String getName() {
        return name;
    }
}
