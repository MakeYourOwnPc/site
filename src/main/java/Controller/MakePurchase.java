package Controller;

import Model.Country.CountryDao;
import Model.Purchase.IPurchaseDao;
import Model.Purchase.Purchase;
import Model.Purchase.PurchaseDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
@WebServlet(name="makePurchase",urlPatterns = "/makePurchase")
public class MakePurchase extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String idBuild = req.getParameter("idBuild");
        String address = req.getParameter("address");
        String cellphone = req.getParameter("cellphone");
        String creationDate = req.getParameter("creationDate");
        String city = req.getParameter("city");
        String cap = req.getParameter("cap");
        String price = req.getParameter("price");
        String country = req.getParameter("country");
        if(email==null||idBuild==null||address==null||cellphone==null||creationDate==null||city==null||cap==null||price==null||country==null){
            resp.setStatus(400);
            return;
        }
        try {
            PurchaseDao purchaseDao = new PurchaseDao();
            CountryDao countryDao = new CountryDao();
            Purchase purchase = new Purchase();
            purchase.setCountry(countryDao.doRetrieveByName(country));
            purchase.setEmail(email);
            purchase.setCity(city);
            purchase.setCap(cap);
            purchase.setPrice(Float.parseFloat(price));
            purchase.setAddress(address);
            purchase.setCellphonenumber(cellphone);
            purchase.setCreationDate(LocalDate.parse(creationDate));
            purchaseDao.doSave(purchase);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/UserPages/showCart.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
