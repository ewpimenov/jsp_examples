package DAO;


import interfaceDAO.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import user.User;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public List<User> getAllUsers() throws SQLException {
        Transaction transaction = session.beginTransaction();
        List<User> users = (List<User>)session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    public void addUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public void deleteUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public void updateUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public User getUser(int id) throws SQLException {
        User user = null;
        Transaction transaction = session.beginTransaction();
        user = (User) session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }
}

