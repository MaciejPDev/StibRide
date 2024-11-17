package g58206.model.repository.dao;

import g58206.exception.RepositoryException;
import g58206.dp.Dao;
import g58206.model.repository.DBManager;
import g58206.model.repository.dto.StopDto;
import g58206.model.tools.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StopDao implements Dao<Pair<Integer, Integer>, StopDto> {

    private final Connection connection;

    private StopDao() {
        this.connection = DBManager.getInstance().getConnection();
    }

    public static StopDao getInstance() {
        return StopDao.StopDaoHolder.getInstance();
    }

    /**
     * public List<StopDto> selectAll() throws RepositoryException {
     * String sql = "Select id_line, id_station, id_order From STOPS";
     * List<StopDto> list = new ArrayList<>();
     * try {
     * Statement stmt = connection.createStatement();
     * ResultSet rs = stmt.executeQuery(sql);
     * while (rs.next()) {
     * StopDto dto = new StopDto(
     * rs.getInt(1),
     * rs.getInt(2),
     * rs.getInt(3)
     * );
     * list.add(dto);
     * }
     * } catch (SQLException e) {
     * throw new RepositoryException("Error StopDao SelectAll");
     * }
     * return list;
     * }
     * <p>
     * public StopDto select(int line, int station) throws RepositoryException {
     * String sql = "Select id_line, id_station, id_order From STOPS Where id_line = ? And id_station = ?";
     * StopDto dto;
     * try {
     * PreparedStatement stmt = connection.prepareStatement(sql);
     * stmt.setInt(1, line);
     * stmt.setInt(2, station);
     * ResultSet rs = stmt.executeQuery();
     * rs.next();
     * dto = new StopDto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
     * } catch (SQLException e) {
     * throw new RepositoryException("Error StopDao Select");
     * }
     * return dto;
     * }
     */

    @Override
    public Pair<Integer, Integer> insert(StopDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void delete(Pair<Integer, Integer> key) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void update(StopDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public List<StopDto> selectAll() throws RepositoryException {
        String sql = "Select id_line, id_station, id_order From STOPS";
        List<StopDto> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                StopDto dto = new StopDto(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error StopDao SelectAll");
        }
        return list;
    }

    @Override
    public StopDto select(Pair<Integer, Integer> key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key");
        }
        String sql = "Select id_line, id_station, id_order From STOPS Where id_line = ? And id_station = ?";
        StopDto dto = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key.getFirst());
            stmt.setInt(2, key.getSecond());
            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StopDto(key.getFirst(), key.getSecond(), rs.getInt(2));
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

    public List<Pair<Integer, Integer>> getLinesAndOrder(int station) throws RepositoryException {
        String sql = "Select id_line, id_order From STOPS Where id_station = ?";
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, station);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Pair<>(rs.getInt(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error StopDao getLines");
        }
        return list;
    }

    public List<Integer> getLines(int station) throws RepositoryException {
        String sql = "Select id_line From STOPS Where id_station = ?";
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, station);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error StopDao getLines");
        }
        return list;
    }

    public StopDto getByLineAndOrder(int line, int order) throws RepositoryException {
        String sql = "Select * From STOPS Where id_line = ? And id_order = ?";
        StopDto dto = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, line);
            stmt.setInt(2, order);
            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StopDto(line, rs.getInt(2), order);
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Non-Unique Record for line : " + line + "and order " + order);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StopDaoHolder {
        private static StopDao getInstance() {
            return new StopDao();
        }
    }
}
