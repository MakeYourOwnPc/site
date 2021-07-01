package Controller;

import Model.User.User;
import Model.User.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class ModifyDB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(!user.isAdmin())
            resp.setStatus(403);
        String option = req.getParameter("option");
        String requestedItem = req.getParameter("requestedItem");
        if(option.equals("insert"))
            insert(requestedItem);
        else if (option.equals("update")) {
            try {
                update(requestedItem,req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

    }
    public void insert(String requestedItem){
        switch(requestedItem){

        }
    }
    public boolean update(String requestedItem, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchAlgorithmException, ServletException, IOException {
        switch (requestedItem){
            case "users":
                PasswordHasher passwordHasher = new PasswordHasher();
                Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[@#$._%-])(?=.*[A-Z]).{8,16}$");
                String email = req.getParameter("email");
                String oldPassword = req.getParameter("oldPassword");
                String newPassword = req.getParameter("newPassword");
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String admin = req.getParameter("admin");
                UserDao userDao = new UserDao();
                User user = userDao.doRetrieveByEmail(email);
                if(!passwordHasher.setPassword(oldPassword).equals(user.getPassword())||!patternPassword.matcher(newPassword).matches())
                    resp.setStatus(400);
                if(!newPassword.isBlank()){
                    newPassword = passwordHasher.setPassword(newPassword);
                    user.setPassword(newPassword);
                }
                if(!firstName.isBlank())
                    user.setFirstName(firstName);
                if(!lastName.isBlank())
                    user.setLastName(lastName);
                if(!admin.isBlank())
                    user.setAdmin(Boolean.valueOf(admin));
                return userDao.doUpdate(user);
            case "gpus":
                String nameGpu = req.getParameter("name");
                String price = req.getParameter("price");
                String consumption = req.getParameter("consumption");
                Part filePart = req.getPart("image");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        }
        return false;
    }
}
