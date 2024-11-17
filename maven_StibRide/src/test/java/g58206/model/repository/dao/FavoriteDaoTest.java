package g58206.model.repository.dao;

import g58206.config.ConfigManager;
import g58206.exception.RepositoryException;
import g58206.model.repository.dto.FavoriteDto;
import g58206.model.repository.dto.LineDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FavoriteDaoTest {

    private final FavoriteDto fav1;
    private final FavoriteDto fav2;
    private final String trueKey;
    private final String falseKey;
    private final FavoriteDao instance;

    public FavoriteDaoTest() {
        try {
            ConfigManager.getInstance().load();
            instance = FavoriteDao.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.trueKey = "shopping";
        this.falseKey = "error";
        this.fav1 = new FavoriteDto(trueKey, "GARE DU MIDI", "ROGIER");
        this.fav2 = new FavoriteDto(falseKey, "m", "m");
    }

    @Test
    void selectAll() throws RepositoryException {
        System.out.println("Test Select All");

        List<FavoriteDto> list = List.of(
                new FavoriteDto("shopping", "GARE DU MIDI", "ROGIER"),
                new FavoriteDto("école", "GARE CENTRALE", "PARC")
        );

        assertEquals(instance.selectAll(), list);
    }

    @Test
    void selectExist() throws RepositoryException {
        System.out.println("Test Select Exist");

        assertEquals(instance.select(trueKey), fav1);
    }

    @Test
    void selectNonExist() throws RepositoryException {
        System.out.println("Test Select Non Exist");

        assertNull(instance.select(falseKey));
    }

    @Test
    void insertNonExist() throws RepositoryException {
        System.out.println("Test Insert Non Exist");

        instance.insert(fav2);
        assertEquals(instance.select(falseKey), fav2);
        instance.delete(falseKey);
    }

    @Test
    void insertExist() throws RepositoryException {
        System.out.println("Test Insert Exist");

        instance.insert(fav2);
        assertThrows(RepositoryException.class, () -> {
            instance.insert(fav2);
        });
        instance.delete(falseKey);
    }

    @Test
    void insertNull() {
        System.out.println("Test Insert Null");

        assertThrows(RepositoryException.class, () -> {
            instance.insert(null);
        });
    }

    @Test
    void updateExist() throws RepositoryException {
        System.out.println("Test Update Exist");

        instance.insert(fav2);
        FavoriteDto fav3 = new FavoriteDto(falseKey, "a", "a");

        instance.update(fav3);
        assertEquals(instance.select(falseKey), fav3);
        instance.delete(falseKey);
    }

    @Test
    void updateNonExist() throws RepositoryException {
        System.out.println("Test Update Non Exist");

        FavoriteDto fav3 = new FavoriteDto(falseKey, "a", "a");
        instance.update(fav3);
        assertNull(instance.select(falseKey));
    }

    @Test
    void updateNull() {
        System.out.println("Test Update Null");

        assertThrows(RepositoryException.class, () -> {
            instance.update(null);
        });
    }

    @Test
    void delete() throws RepositoryException {
        System.out.println("Test Delete");

        instance.insert(fav2);

        instance.delete(falseKey);
        assertNull(instance.select(falseKey));
    }

    @Test
    void deleteNull() {
        System.out.println("Test Delete Null");

        assertThrows(RepositoryException.class, () -> {
            instance.delete(null);
        });
    }

}