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
import java.util.regex.Pattern;

@WebServlet(name="modifyUser",urlPatterns = "/modifyUser")
public class ModifyUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserDao userDao = new UserDao();
        try {
            User userDB = userDao.doRetrieveByEmail(user.getEmail());
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String newPassword = req.getParameter("newPassword");
            if(!newPassword.isBlank()) {
                PasswordHasher passwordHasher = new PasswordHasher();
                String hashedPassword = passwordHasher.setPassword(newPassword);
                Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[@#$._%-])(?=.*[A-Z]).{8,16}$");
                if (patternPassword.matcher(newPassword).matches() && !hashedPassword.equals(userDB.getPassword())) {
                    userDB.setPassword(hashedPassword);
                }
            }
            userDB.setFirstName(firstName);
            userDB.setLastName(lastName);
            boolean result = userDao.doUpdate(userDB);
            if(result) {
                user = userDao.doRetrieveByEmail(user.getEmail());
                user.setPassword("");
                req.getSession().setAttribute("user", user);
            }
            resp.getWriter().print(result);
        } catch (SQLException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }


    }
}
