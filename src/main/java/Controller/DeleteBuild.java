package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name="deleteBuild",urlPatterns = "/deleteBuild")
public class DeleteBuild extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String id = req.getParameter("id");
        if(user==null){
            resp.setStatus(403);
            return;
        }
        if(id==null){
            resp.setStatus(400);
            return;
        }
        BuildDao buildDao = new BuildDao();
        Build build = null;
        try {
            if(!user.isAdmin()) {
                build = buildDao.doRetrieveById(Integer.parseInt(id));
                if (!build.getMaker().equals(user.getEmail())) {
                    resp.setStatus(403);
                    return;
                }
            }
            if(buildDao.doDelete(Integer.parseInt(id)))
                resp.setStatus(200);
            else
                resp.setStatus(500);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
