package interfaceDAO;

import user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    void addUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    User getUser(int id) throws SQLException;
}
