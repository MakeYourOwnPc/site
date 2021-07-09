package Controller;

import Model.Build.BuildDao;
import Model.Build.BuildNames;
import Model.User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet(name="showBuilds",urlPatterns="/showBuilds")
public class ShowBuilds extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        BuildDao buildDao = new BuildDao();
        if(user==null) {
            resp.setStatus(403);
            return;
        }
        try {
            ArrayList<BuildNames> builds = buildDao.doRetrieveByMaker(user.getEmail());
            req.setAttribute("builds",builds);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/UserPages/userBuilds.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
