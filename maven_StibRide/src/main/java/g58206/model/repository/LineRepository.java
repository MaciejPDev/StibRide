package g58206.model.repository;

import g58206.exception.RepositoryException;
import g58206.dp.Repository;
import g58206.model.repository.dao.LineDao;
import g58206.model.repository.dto.LineDto;

import java.util.List;

public class LineRepository implements Repository<Integer, LineDto> {

    private final LineDao dao;

    public LineRepository() {
        this.dao = LineDao.getInstance();
    }

    public LineRepository(LineDao dao) {
        this.dao = dao;
    }

    @Override
    public Integer add(LineDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public List<LineDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public LineDto get(Integer key) throws RepositoryException {
        return dao.select(key);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return dao.select(key) != null;
    }

}
