package Controller;

import Model.User.User;
import Model.User.UserDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedItem = req.getParameter("requestedItem");
        switch(requestedItem){
            case "Users":
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
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Gson gson = new Gson();
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(gson.toJson(user));
                }
            case "Cpus":;
            case "Gpus":;
            case "Memories":;
            case "Motherboards":;
            case "Psus":;
            case "PcCases":;
            case "Builds":;
        }
    }
}
