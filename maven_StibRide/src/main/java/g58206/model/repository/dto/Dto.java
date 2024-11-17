package g58206.model.repository.dto;

public class Dto<K> {

    protected K key;

    protected Dto(K key) {
        if (key == null) {
            throw new IllegalArgumentException("No key");
        }
        this.key = key;
    }

    public K getKey() {
        return this.key;
    }
}
