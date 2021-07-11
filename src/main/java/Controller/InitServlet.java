package Controller;

import Model.Country.Country;
import Model.Country.CountryDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(value="/initServlet",loadOnStartup=0)
public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        CountryDao countryDao = new CountryDao();
        try {
            ArrayList<Country> countries = countryDao.doRetrieveAll();
            System.out.println(countries);
            servletContext.setAttribute("countries",countries);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
