package Controller;

import Model.User.User;
import Model.User.UserDao;
import com.mysql.cj.protocol.Message;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.regex.Pattern;

@WebServlet(name="registration",urlPatterns = "/registration")
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws RuntimeException {

        HttpSession session = req.getSession(true);

        Pattern patternEmail = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[@#$._%-])(?=.*[A-Z]).{8,16}$");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String emailTest = req.getParameter("emailtest");
        String password = req.getParameter("password");
        if (!email.equalsIgnoreCase(emailTest) || !patternEmail.matcher(email).matches() || !patternPassword.matcher(password).matches()) try {
            throw new Exception();
        } catch (Exception e) {
            resp.setStatus(500);
            throw new RuntimeException();
        }
        try {
            PasswordHasher passwordHasher = new PasswordHasher();
            password = passwordHasher.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(false);
        UserDao userDao = new UserDao();
        try {
            if (!userDao.isPresent(email)) {
                userDao.doSave(user);
                user.setPassword("");
                session.setAttribute("user", user);
                resp.sendRedirect("/MYOPSite_war_exploded/");
            } else {
                req.setAttribute("errorDescription", "Email already used.");
                throw new Exception();
            }
        } catch (Exception throwables) {
            resp.setStatus(500);
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
}
