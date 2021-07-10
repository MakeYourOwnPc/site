package Controller;

import Model.User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name="adminPage",urlPatterns = "/admin")
public class adminPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        if (session.isNew() ||user==null|| !user.isAdmin()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./index.html");
            dispatcher.forward(request,response);
            return;
        }
        System.out.println(request.getRequestURL());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/AdminPages/adminPage.jsp");
        dispatcher.forward(request,response);
    }
}
