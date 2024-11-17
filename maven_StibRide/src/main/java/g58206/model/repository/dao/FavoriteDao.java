package g58206.model.repository.dao;

import g58206.exception.RepositoryException;
import g58206.dp.Dao;
import g58206.model.repository.DBManager;
import g58206.model.repository.dto.FavoriteDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao implements Dao<String, FavoriteDto> {

    private final Connection connection;

    public FavoriteDao() {
        this.connection = DBManager.getInstance().getConnection();
    }

    public static FavoriteDao getInstance() {
        return FavoriteDaoHolder.getInstance();
    }


    @Override
    public String insert(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("No item provided");
        }
        String id = "";
        String sql = "INSERT INTO FAVORITES(name,origin,destination) values(?, ?,? )";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.setString(2, item.getOrigin());
            pstmt.setString(3, item.getDestination());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                id = result.getString(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return id;

    }

    @Override
    public void delete(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No Key provided");
        }
        String sql = "DELETE FROM FAVORITES WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public void update(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("No item provided");
        }
        String sql = "UPDATE FAVORITES SET origin=? ,destination=? where name=? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getOrigin());
            pstmt.setString(2, item.getDestination());
            pstmt.setString(3, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        String sql = "Select * From FAVORITES";
        List<FavoriteDto> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                FavoriteDto dto = new FavoriteDto(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException("Error FavoriteDao SelectAll");
        }
        return list;
    }

    @Override
    public FavoriteDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key select FavoriteDao");
        }
        String sql = "SELECT * FROM FAVORITES WHERE name = ?";
        FavoriteDto dto = null;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new FavoriteDto(rs.getString(1), rs.getString(2), rs.getString(3));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Non-unique key : " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;

    }

    private static class FavoriteDaoHolder {

        private static FavoriteDao getInstance() {
            return new FavoriteDao();
        }
    }

}
