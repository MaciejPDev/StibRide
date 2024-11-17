package g58206.model.repository;

import g58206.exception.RepositoryException;
import g58206.dp.Repository;
import g58206.model.repository.dao.StationDao;
import g58206.model.repository.dto.StationDto;

import java.util.List;

public class StationRepository implements Repository<Integer, StationDto> {

    private final StationDao dao;

    public StationRepository() {
        this.dao = StationDao.getInstance();
    }


    @Override
    public Integer add(StationDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public List<StationDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StationDto get(Integer key) throws RepositoryException {
        return dao.select(key);
    }

    public StationDto get(String name) throws RepositoryException {
        return dao.select(name);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        return dao.select(key) != null;
    }
}
