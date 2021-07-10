package Controller;

import Model.Build.BuildDao;
import Model.Build.BuildNames;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="home",value="/index.html")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        BuildDao buildDao = new BuildDao();
        try {
            ArrayList<BuildNames> builds = buildDao.doRetrieveSuggested();
            Gson gson = new Gson();
            System.out.println(gson.toJson(builds));
            req.setAttribute("builds",builds);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/index.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
