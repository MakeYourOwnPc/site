package Controller;

import Model.User.User;
import Model.User.UserDao;

import javax.servlet.ServletContext;
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
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

@WebServlet(name="matchPassword",urlPatterns = "/matchPassword")
public class MatchPassword extends HttpServlet {
    private final int defaultCounter = 3;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();
        synchronized (session) {
            synchronized (servletContext) {
                Hashtable<String, LocalDateTime> usersNextAttempt = (Hashtable<String, LocalDateTime>) servletContext.getAttribute("usersNextAttempt");
                User user = (User) session.getAttribute("user");
                Object obj = session.getAttribute("attempts");
                int counter = -1;
                if (!usersNextAttempt.containsKey(user.getEmail())) {
                    usersNextAttempt.put(user.getEmail(), LocalDateTime.now());
                    servletContext.setAttribute("usersNextAttempt", usersNextAttempt);
                }

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                    if (LocalDateTime.now().isAfter(usersNextAttempt.get(user.getEmail()))) {
                        if (obj == null) {
                        counter = defaultCounter;
                        session.setAttribute("attempts", counter);
                    }
                        else
                            counter = (int) obj;
                }
                if (counter > 0) {
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
                } else {
                    usersNextAttempt.replace(user.getEmail(), LocalDateTime.now().plusMinutes(5));
                    servletContext.setAttribute("usersNextAttempt", usersNextAttempt);
                    resp.getWriter().print("{\"result\":false,\"attempts\":" + counter + "}");
                }
            }
        }
    }
}
