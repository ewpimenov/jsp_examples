package DAO;

import java.util.List;

import user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from user_table";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            String password = result.getString("password");
            users.add(new User(id, name, password));
        }
        result.close();
        stmt.close();
        return users;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user_table (name, password) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getPassword());
        stmt.execute();
        stmt.close();
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "delete from user_table where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, user.getId());

        boolean rowDeleted = stmt.executeUpdate() > 0;
        stmt.close();
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "update user_table set name = ?, password= ? where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getPassword());
        stmt.setInt(3, user.getId());

        boolean rowUpdated = stmt.executeUpdate() > 0;
        stmt.close();
        return rowUpdated;
    }

    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "select * from user_table where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            user = new User(id, name, password);
        }
        resultSet.close();
        stmt.close();
        return user;
    }
}
