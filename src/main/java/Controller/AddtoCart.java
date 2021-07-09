package Controller;

import Model.Build.Build;
import Model.ShoppingCart.ShoppingCart;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;
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
@WebServlet(name="addToCart",urlPatterns = "/addToCart")
public class AddtoCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null)
            resp.sendRedirect("/registrationPage.jsp");
        String json = req.getParameter("build");
        Gson gson = new Gson();
        Build build = gson.fromJson(json,Build.class);
        System.out.println(json+"\n"+gson.toJson(build));
        int idBuild = build.getId();
        if(idBuild==0){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/saveBuild");
            requestDispatcher.include(req,resp);
        }
        idBuild = (int) session.getAttribute("id");
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user.getEmail());
        shoppingCart.setIdbuild(idBuild);
        try {
            if(shoppingCartDao.isShopCartPresent(user.getEmail()))
                shoppingCartDao.doDelete(user.getEmail());
            resp.getWriter().print(shoppingCartDao.doSave(shoppingCart));
            doGet(req,resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.html");
        requestDispatcher.forward(req,resp);
    }
}
