package g58206.model.repository;

import g58206.exception.RepositoryException;
import g58206.dp.Repository;
import g58206.model.repository.dao.StopDao;
import g58206.model.repository.dto.StopDto;
import g58206.model.tools.Pair;

import java.util.List;

public class StopRepository implements Repository<Pair<Integer, Integer>, StopDto> {

    private final StopDao dao;

    public StopRepository() {
        this.dao = StopDao.getInstance();
    }

    @Override
    public Pair<Integer, Integer> add(StopDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void remove(Pair<Integer, Integer> key) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public List<StopDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StopDto get(Pair<Integer, Integer> key) throws RepositoryException {
        return dao.select(key);
    }

    @Override
    public boolean contains(Pair<Integer, Integer> key) throws RepositoryException {
        return dao.select(key) != null;
    }

    public List<Pair<Integer, Integer>> getLinesAndOrders(int station) throws RepositoryException {
        return dao.getLinesAndOrder(station);
    }

    public List<Integer> getLines(int station) throws RepositoryException {
        return dao.getLines(station);
    }

    public StopDto getByLineAndOrder(int line, int order) throws RepositoryException {
        return dao.getByLineAndOrder(line, order);
    }
}
