package Controller;

import Model.User.User;
import Model.User.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name="matchPassword",urlPatterns = "/matchPassword")
public class MatchPassword extends HttpServlet {
    private final int defaultCounter = 3;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int counter;
        HttpSession session = req.getSession();
        synchronized (session) {
            LocalDateTime nextAttempt = (LocalDateTime) session.getAttribute("nextAttempt");
            if (nextAttempt == null || LocalDateTime.now().isAfter(nextAttempt))
                counter = defaultCounter;
            else
                counter = (int) session.getAttribute("attempts");
            if (counter > 0) {
                User user = (User) session.getAttribute("user");
                String oldPassword = req.getParameter("oldPassword");
                UserDao userDao = new UserDao();
                try {
                    User userDB = userDao.doRetrieveByEmail(user.getEmail());
                    PasswordHasher passwordHasher = new PasswordHasher();
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    if (passwordHasher.setPassword(oldPassword).equals(userDB.getPassword()))
                        resp.getWriter().print("{\"result\":true,\"attempts\":" + counter + "}");
                    else {
                        resp.getWriter().print("{\"result\":false,\"attempts\":" + counter + "}");
                        counter--;
                        session.setAttribute("attempts", counter);
                    }

                } catch (SQLException | NoSuchAlgorithmException throwables) {
                    throwables.printStackTrace();
                }
            } else
                resp.getWriter().print("false");
        }
    }
}
