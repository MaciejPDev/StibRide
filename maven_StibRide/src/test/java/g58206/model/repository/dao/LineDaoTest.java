package g58206.model.repository.dao;

import g58206.config.ConfigManager;
import g58206.exception.RepositoryException;
import g58206.model.repository.dto.LineDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineDaoTest {

    private LineDto line1;
    private LineDto line2;
    private int trueKey;
    private int falseKey;
    private LineDao instance;

    public LineDaoTest() {
        try {
            ConfigManager.getInstance().load();
            instance = LineDao.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.trueKey = 1;
        this.falseKey = 7;
        this.line1 = new LineDto(trueKey, 1);
        this.line2 = new LineDto(falseKey, 7);
    }

    @Test
    void insert() {
        System.out.println("Test Insert");
        assertThrows(RepositoryException.class, () -> {
            instance.insert(line1);
        });
    }

    @Test
    void delete() {
        System.out.println("Test Delete");
        assertThrows(RepositoryException.class, () -> {
            instance.delete(trueKey);
        });
    }

    @Test
    void update() {
        System.out.println("Test Update");
        assertThrows(RepositoryException.class, () -> {
            instance.update(line2);
        });
    }

    @Test
    void selectAll() throws RepositoryException {
        System.out.println("Test SelectAll");

        List<LineDto> expected = List.of(
                new LineDto(1, 1),
                new LineDto(2, 2),
                new LineDto(5, 5),
                new LineDto(6, 6)
        );

        assertEquals(instance.selectAll(), expected);
    }

    @Test
    void selectExisting() throws RepositoryException {
        System.out.println("Test SelectExisting");

        assertEquals(instance.select(trueKey), line1);
    }

    @Test
    void selectNonExisting() throws RepositoryException {
        System.out.println("Test SelectNonExisting");

        assertNull(instance.select(falseKey));
    }
}