package Controller;

import Model.Build.BuildDao;
import Model.Country.CountryDao;
import Model.Purchase.IPurchaseDao;
import Model.Purchase.Purchase;
import Model.Purchase.PurchaseDao;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
            String address = req.getParameter("address");
            String cellphone = req.getParameter("cellphone");
            LocalDate creationDate = LocalDate.now();
            String city = req.getParameter("city");
            String cap = req.getParameter("cap");
            String country = req.getParameter("country");
            if(email==null||idBuild==0||address==null||cellphone==null||city==null||cap==null||country==null){
                resp.setStatus(400);
                return;
            }
            Float price = buildDao.doRetrieveById(idBuild).price();
            try {
                PurchaseDao purchaseDao = new PurchaseDao();
                Purchase purchase = new Purchase();
                purchase.setIdBuild(idBuild);
                purchase.setCountry(country);
                purchase.setEmail(email);
                purchase.setCity(city);
                purchase.setCap(cap);
                purchase.setPrice(price);
                purchase.setAddress(address);
                purchase.setCellphonenumber(cellphone);
                purchase.setCreationDate(creationDate);
                purchaseDao.doSave(purchase);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
