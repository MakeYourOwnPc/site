package Controller;

import Model.User.User;
import Model.User.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name="matchPassword",urlPatterns = "/matchPassword")
public class MatchPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String oldPassword = req.getParameter("oldPassword");
        UserDao userDao = new UserDao();
        try {
            User user = userDao.doRetrieveByEmail(email);
            PasswordHasher passwordHasher = new PasswordHasher();
            if(!passwordHasher.setPassword(oldPassword).equals(user.getPassword()))
                resp.getWriter().print("true");
            else
                resp.getWriter().print("false");

        } catch (SQLException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }
    }
}
