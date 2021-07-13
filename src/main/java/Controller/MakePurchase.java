package Controller;

import Model.Build.BuildDao;
import Model.Country.Country;
import Model.Country.CountryDao;
import Model.Purchase.IPurchaseDao;
import Model.Purchase.Purchase;
import Model.Purchase.PurchaseDao;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name="makePurchase",urlPatterns = "/makePurchase")
public class MakePurchase extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user==null){
            resp.setStatus(403);
            return;
        }
        String email = user.getEmail();
        ShoppingCartDao shoppingCartDao = new ShoppingCartDao();
        BuildDao buildDao = new BuildDao();
        int idBuild;
        try {
            idBuild = shoppingCartDao.doRetrieveByEmail(email);
            String json = req.getParameter("purchase");
            Gson gson = new Gson();
            Purchase purchase = gson.fromJson(json,Purchase.class);
            ServletContext servletContext = req.getServletContext();
            synchronized (servletContext) {
                ArrayList<Country> countries = (ArrayList<Country>) servletContext.getAttribute("countries");
                boolean result = false;
                for (Country c : countries)
                    if (c.getId().equals(purchase.getCountry()))
                        result = true;
                if (!result) {
                    resp.setStatus(403);
                    return;
                }
            }
            purchase.setCreationDate(LocalDate.now());
            float price = buildDao.doRetrieveById(idBuild).price();
            try {
                PurchaseDao purchaseDao = new PurchaseDao();
                purchase.setIdBuild(idBuild);
                purchase.setEmail(email);
                purchase.setPrice(price);
                purchaseDao.doSave(purchase);
                resp.setContentType("plain/text");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().print(purchase.getId());
                req.setAttribute("idBuild",idBuild);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/updateStock");
                requestDispatcher.include(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
