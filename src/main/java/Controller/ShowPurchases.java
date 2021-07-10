package Controller;

import Model.Purchase.Purchase;
import Model.Purchase.PurchaseDao;
import Model.User.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
            Gson gson = new Gson();
            req.setAttribute("purchases",gson.toJson(list));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/UserPages/showPurchases");
            requestDispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
