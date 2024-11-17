package g58206.model.repository.dao;

import g58206.exception.RepositoryException;
import g58206.dp.Dao;
import g58206.model.repository.DBManager;
import g58206.model.repository.dto.StationDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDao implements Dao<Integer, StationDto> {

    private final Connection connection;

    private StationDao() {
        this.connection = DBManager.getInstance().getConnection();
    }

    public static StationDao getInstance() {
        return StationDaoHolder.getInstance();
    }

    @Override
    public Integer insert(StationDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void update(StationDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        String sql = "Select * From STATIONS";
        List<StationDto> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StationDto dto = new StationDto(rs.getInt(1), rs.getString(2));
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Erro StationDao SelectAll");
        }
        return list;
    }


    @Override
    public StationDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key");
        }
        String sql = "Select id, name From STATIONS Where id = ?";
        StationDto dto = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(key, rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Non-Unique Record " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    public StationDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key");
        }
        String sql = "Select id, name From STATIONS Where name = ?";
        StationDto dto = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), key);
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Non-Unique Record " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StationDaoHolder {
        private static StationDao getInstance() {
            return new StationDao();
        }
    }


}
