package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.Build.BuildNames;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name="showCart",urlPatterns = "/showCart")
public class ShowCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user==null){

            return;
        }
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        try {
            int idBuild = shoppingCartDao.doRetrieveByEmail(user.getEmail());
            System.out.println(idBuild);
            BuildDao buildDao = new BuildDao();
            BuildNames build = new BuildNames();
            if(idBuild==0)
                build.setId(0);
            else
                build = buildDao.doRetrieveNamesById(idBuild);
            req.setAttribute("build",build);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/UserPages/userCart.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
