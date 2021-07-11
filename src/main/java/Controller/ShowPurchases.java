package Controller;

import Model.Purchase.Purchase;
import Model.Purchase.PurchaseDao;
import Model.User.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet(name="oldOrders",value = "/oldOrders")

public class ShowPurchases extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user == null){
            resp.setStatus(403);
            return;
        }
        try {
            PurchaseDao purchaseDao = new PurchaseDao();
            ArrayList<Purchase> list = purchaseDao.doRetrieveByEmail(user.getEmail(), 50,0);
            req.setAttribute("purchases",list);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/UserPages/userOrders.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
