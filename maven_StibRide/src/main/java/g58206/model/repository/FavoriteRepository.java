package g58206.model.repository;

import g58206.exception.RepositoryException;
import g58206.dp.Repository;
import g58206.model.repository.dao.FavoriteDao;
import g58206.model.repository.dto.FavoriteDto;

import java.util.List;

public class FavoriteRepository implements Repository<String, FavoriteDto> {

    private final FavoriteDao dao;

    public FavoriteRepository() {
        this.dao = FavoriteDao.getInstance();
    }

    @Override
    public String add(FavoriteDto item) throws RepositoryException {
        String key = item.getKey();
        if (contains(key)) {
            dao.update(item);
        } else {
            dao.insert(item);
        }
        return key;
    }

    @Override
    public void remove(String key) throws RepositoryException {
        dao.delete(key);
    }

    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(String key) throws RepositoryException {
        return dao.select(key);
    }

    @Override
    public boolean contains(String key) throws RepositoryException {
        return dao.select(key) != null;
    }
}
