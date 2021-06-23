package Controller;

import Model.User.User;
import Model.User.UserDao;
import com.mysql.cj.protocol.Message;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(name="registration",urlPatterns = "/registration")
public class Registration extends HttpServlet {
    public String setPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for(byte bit:hashedPassword)
            stringBuilder.append(String.format("%02x",bit));
        return stringBuilder.toString();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String email = req.getParameter("email");
        String emailTest = req.getParameter("emailtest");
        if(!email.equalsIgnoreCase(emailTest)) try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String password = req.getParameter("password");
        try {
            password = setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(false);
        UserDao userDao = new UserDao();
        try {
            if(userDao.doRetrieveByEmail(email)==null)
                userDao.doSave(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        session.setAttribute("user",user);
        resp.sendRedirect("/MYOPSite_war_exploded/");
    }

}