package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.ShoppingCart.ShoppingCart;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name="saveBuild",urlPatterns = "/saveBuild")
public class SaveBuild extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Gson gson = new Gson();
        String buildJson = req.getParameter("build");
        BuildDao buildDao = new BuildDao();
        Build build = gson.fromJson(buildJson,Build.class);
        try {
            req.getSession().setAttribute("build",build);
            if(user!=null) {
                build.setMaker(user.getEmail());
                resp.getWriter().print(buildDao.doSave(build));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
