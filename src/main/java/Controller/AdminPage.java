package Controller;

import Model.User.User;
import Model.User.UserDao;

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
                if(req.getParameter("email").isBlank()) {
                    try {
                        ArrayList<User> list = userDao.doRetrieveAll();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
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
