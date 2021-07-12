package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name="deleteBuild",urlPatterns = "/deleteBuild")
public class DeleteBuild extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String id = req.getParameter("id");
        int idBuild;
        if(user==null){
            resp.setStatus(403);
            return;
        }
        if(id==null){
            resp.setStatus(400);
            return;
        }
        idBuild=Integer.parseInt(id);
        BuildDao buildDao = new BuildDao();
        Build build = null;
        try {
            if(!user.isAdmin()) {
                build = buildDao.doRetrieveById(idBuild);
                if (!build.getMaker().equals(user.getEmail())) {
                    resp.setStatus(403);
                    return;
                }
            }
            if(buildDao.doDelete(idBuild)) {
                resp.setStatus(200);
                int idSessionBuild = (session.getAttribute("id")==null)?0:(int) session.getAttribute("id");
                if(idBuild==idSessionBuild)
                    session.removeAttribute("id");
            }
            else
                resp.setStatus(500);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
