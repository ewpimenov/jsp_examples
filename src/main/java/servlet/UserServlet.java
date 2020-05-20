package servlet;

import java.sql.SQLException;
import java.util.List;

import service.UserService;
import user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/add":
                    addUser(req, resp);
                    break;
                case "/delete":
                    deleteUser(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateUser(req, resp);
                    break;
                default:
                    listUsers(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<User> users = service.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/usersForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = service.getUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/usersForm.jsp");
        req.setAttribute("users", existingUser);
        dispatcher.forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User(name, password);
        service.addUser(user);
        resp.sendRedirect("users");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User(id, name, password);
        service.updateUser(user);
        resp.sendRedirect("users");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));

        User user = new User(id);
        service.deleteUser(user);
        resp.sendRedirect("users");
    }
}
