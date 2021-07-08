package Controller;

import Model.ShoppingCart.ShoppingCart;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AddtoCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null)
            resp.sendRedirect("/registrationPage.jsp");
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user.getEmail());
        shoppingCart.setIdbuild((Integer) session.getAttribute("id"));
        try {
            resp.getWriter().print(shoppingCartDao.doSave(shoppingCart));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
