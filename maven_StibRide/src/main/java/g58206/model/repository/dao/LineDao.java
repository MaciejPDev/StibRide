package g58206.model.repository.dao;

import g58206.dp.Dao;
import g58206.exception.RepositoryException;
import g58206.model.repository.DBManager;
import g58206.model.repository.dto.LineDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineDao implements Dao<Integer, LineDto> {

    private final Connection connection;

    private LineDao() {
        this.connection = DBManager.getInstance().getConnection();
    }

    @Override
    public Integer insert(LineDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public void update(LineDto item) throws RepositoryException {
        throw new RepositoryException(new UnsupportedOperationException("This action is not supported."));
    }

    @Override
    public List<LineDto> selectAll() throws RepositoryException {
        String sql = "Select id From LINES";
        List<LineDto> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                LineDto dto = new LineDto(rs.getInt(1), rs.getInt(1));
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error LineDao SelectAll");
        }
        return list;
    }

    @Override
    public LineDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key");
        }
        String sql = "Select id From LINES Where id = ?";
        LineDto dto = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new LineDto(key, rs.getInt(1));
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


    public static LineDao getInstance() {
        return LineDaoHolder.getInstance();
    }

    private static class LineDaoHolder {
        private static LineDao getInstance() {
            return new LineDao();
        }
    }

}
