package Controller;

import Model.Country.Country;
import Model.Country.CountryDao;
import Model.User.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

@WebServlet(value="/initServlet",loadOnStartup=0)
public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        CountryDao countryDao = new CountryDao();
        UserDao userDao = new UserDao();
        try {
            Hashtable<String, LocalDateTime> usersNextAttempt = new Hashtable<>();
            ArrayList<Country> countries = countryDao.doRetrieveAll();
            servletContext.setAttribute("usersNextAttempt",usersNextAttempt);
            servletContext.setAttribute("countries",countries);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
