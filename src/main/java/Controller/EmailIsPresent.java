package Controller;

import Model.User.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="EmailIsPresent",value = "/emailispresent")
public class EmailIsPresent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao= new UserDao();
        String email= request.getParameter("email");


        response.setContentType("plain/text");
        response.setCharacterEncoding("UTF-8");
        try {
            if(dao.isPresent(email))
                response.getWriter().print("true");

            else
                response.getWriter().print("false");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(500);
        }
    }
}
