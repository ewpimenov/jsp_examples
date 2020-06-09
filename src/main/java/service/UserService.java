package service;

import DAO.UserDao;
import DAO.UserHibernateDAO;
import org.hibernate.SessionFactory;
import user.DBHelper;
import user.User;
 
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService service;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static UserHibernateDAO getUserHibernateDAO() {
        return new UserHibernateDAO(service.sessionFactory.openSession());
    }

    public static UserService getInstance() {
        if (service == null) {
            service = new UserService(DBHelper.getSessionFactory());
        }
        return service;
    }


    //ниже то, что было

    //private UserDao userDao = getUserDao();

    public UserService() {
    }

    public List<User> getAllUsers() throws SQLException {
        return getUserHibernateDAO().getAllUsers();
    }

    public User getUser(int id) throws SQLException {
        return getUserHibernateDAO().getUser(id);
    }

    public void addUser(User user) throws SQLException {
        getUserHibernateDAO().addUser(user);
    }

    public void deleteUser(User user) throws SQLException {
        getUserHibernateDAO().deleteUser(user);
    }

    public void updateUser(User user) throws SQLException {
        getUserHibernateDAO().updateUser(user);
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
