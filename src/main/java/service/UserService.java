package service;

import DAO.UserDao;
import user.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDao userDao = getUserDao();

    public UserService() {
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public User getUser(int id) throws SQLException {
        return userDao.getUser(id);
    }

    public boolean addUser(User user) throws SQLException {
       return userDao.addUser(user);
    }

    public boolean deleteUser(User user) throws SQLException {
        return userDao.deleteUser(user);
    }

    public boolean updateUser(User user) throws SQLException {
       return userDao.updateUser(user);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("crud_project?").          //db name
                    append("user=root").             //login
                    append("&password=1111").        //password();
                    append("&serverTimezone=UTC");  //setup server time


            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDao getUserDao() {
        return new UserDao(getMysqlConnection());
    }
}
