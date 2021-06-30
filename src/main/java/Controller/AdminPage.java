package Controller;

import Model.Gpu.Gpu;
import Model.Gpu.GpuDao;
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
@WebServlet(name="adminpage",urlPatterns = "/adminpage")
public class AdminPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedItem = req.getParameter("requestedItem");
        switch(requestedItem){
            case "users":
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
                break;
            case "cpus":;
            case "gpus":
                GpuDao gpuDao = new GpuDao();
                String name = req.getParameter("name");
                if(name.isBlank()) {
                    try {
                        ArrayList<Gpu> list = gpuDao.doRetrieveAll(50,0);
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
                    ArrayList<Gpu> list = null;
                    try {
                        list = gpuDao.doRetrieveByName(name,0,50);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Gson gson = new Gson();
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(gson.toJson(list));
                }
                break;
            case "memories":;
            case "motherboards":;
            case "psus":;
            case "pccases":;
            case "builds":;
        }
    }
}
