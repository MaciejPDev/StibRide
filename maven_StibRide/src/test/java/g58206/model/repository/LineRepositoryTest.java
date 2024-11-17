package g58206.model.repository;

import g58206.exception.RepositoryException;
import g58206.model.repository.dao.LineDao;
import g58206.model.repository.dto.LineDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class LineRepositoryTest {

    @Mock
    private LineDao mock;
    private int trueKey;
    private int falseKey;
    private LineDto trueLine;
    private LineDto falseLine;
    private List<LineDto> list;

    public LineRepositoryTest() {
        this.trueKey = 1;
        this.falseKey = 7;
        this.trueLine = new LineDto(trueKey, 1);
        this.falseLine = new LineDto(falseKey, 7);
        this.list = List.of(
                new LineDto(1, 1),
                new LineDto(2, 2),
                new LineDto(5, 5),
                new LineDto(6, 6)
        );
    }

    @BeforeEach
    public void init() throws RepositoryException {
        System.out.println("BeforeEach");
        Mockito.lenient().when(mock.select(trueKey)).thenReturn(trueLine);
        Mockito.lenient().when(mock.select(falseKey)).thenReturn(null);
        Mockito.lenient().when(mock.selectAll()).thenReturn(list);
        Mockito.lenient().when(mock.insert(falseLine)).thenThrow(RepositoryException.class);
        Mockito.lenient().doThrow(RepositoryException.class).when(mock).update(falseLine);
        Mockito.lenient().doThrow(RepositoryException.class).when(mock).delete(falseKey);
    }

    @Test
    public void selectAllTest() throws RepositoryException {
        System.out.println("Select All Test");
        LineRepository l = new LineRepository(mock);

        assertEquals(l.getAll(), list);
    }

    @Test
    public void selectExistTest() throws RepositoryException {
        System.out.println("Select Exist Test");
        LineRepository l = new LineRepository(mock);

        assertEquals(l.get(trueKey), trueLine);
    }

    @Test
    public void selectNonExistTest() throws RepositoryException {
        System.out.println("Select Non Exist Test");
        LineRepository l = new LineRepository(mock);

        assertNull(l.get(falseKey));
    }

    @Test
    public void addUpdateTest() {
        System.out.println("Add Update Test");
        LineRepository l = new LineRepository(mock);

        assertThrows(RepositoryException.class, () -> {
            l.add(new LineDto(trueKey, 10));
        });
    }

    @Test
    public void addInsertTest() {
        System.out.println("Add Insert Test");
        LineRepository l = new LineRepository(mock);

        assertThrows(RepositoryException.class, () -> {
            l.add(new LineDto(falseKey, 10));
        });
    }

    @Test
    public void removeTest() {
        System.out.println("Remove Test");
        LineRepository l = new LineRepository(mock);

        assertThrows(RepositoryException.class, () -> {
            l.remove(trueKey);
        });
    }

    @Test
    public void containsTrueTest() throws RepositoryException {
        System.out.println("Contains True Test");
        LineRepository l = new LineRepository(mock);

        assertTrue(l.contains(trueKey));
    }

    @Test
    public void containsFalseTest() throws RepositoryException {
        System.out.println("Contains False Test");
        LineRepository l = new LineRepository(mock);

        assertFalse(l.contains(falseKey));
    }

}