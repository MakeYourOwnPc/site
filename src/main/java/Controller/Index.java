package Controller;

import Model.Build.BuildDao;
import Model.Build.BuildNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Index extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BuildDao buildDao = new BuildDao();
        try {
            ArrayList<BuildNames> builds = buildDao.doRetrieveSuggested();
            req.setAttribute("builds",builds);
            RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/index.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
