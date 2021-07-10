package Controller;

import Model.User.User;
import Model.User.UserDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="usersLister",urlPatterns="/admin/usersLister")
public class UsersLister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User caller = (User) req.getSession().getAttribute("user");
        if(caller==null||!caller.isAdmin()) {
            resp.setStatus(403);
            return;
        }
        UserDao userDao = new UserDao();
        String email = req.getParameter("email");
        if(email.isBlank()) {
            try {
                ArrayList<User> list = userDao.doRetrieveAll();
                Gson gson = new Gson();
                String json = gson.toJson(list);
                resp.setContentType("plain/text");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().print(json);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            User user = null;
            try {
                user = userDao.doRetrieveByEmail(email);
                user.setPassword("");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Gson gson = new Gson();
            resp.setContentType("plain/text");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(gson.toJson(user));
        }
    }
}
