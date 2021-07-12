package Controller;

import Model.User.User;
import Model.User.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name="login",value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PasswordHasher passwordHasher = new PasswordHasher();
        try {
            synchronized (session) {
                String hashedPassword = passwordHasher.setPassword(password);
                UserDao userDao = new UserDao();
                User user = userDao.doRetrieveByEmail(email);
                boolean failedLogin = true;
                if (user != null) {
                    if (hashedPassword.equals(user.getPassword())) {
                        failedLogin = false;
                        user.setPassword("");
                        session.setAttribute("user", user);
                    }
                }
                session.setAttribute("failedLogin", failedLogin);
                String referer = req.getHeader("referer");
                if (referer.equals("/WEB-INF/UserPages/login.jsp")) {
                    resp.sendRedirect("/WEB-INF/UserPages/showCart.jsp");
                }
                resp.sendRedirect(referer);
            }
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }
    }
}
