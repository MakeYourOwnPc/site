package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name="showUser",urlPatterns = "/showUser")
public class ShowUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String resultString = req.getParameter("resultUpdate");
        boolean result;
        if(session.getAttribute("user")!=null){
            if(resultString!=null)
                result=true;
            else
                result=false;
            req.setAttribute("result",result);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/UserPages/userData.jsp");
            dispatcher.forward(req,resp);
        }
        else
            resp.setStatus(403);
    }
}
