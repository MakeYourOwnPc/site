package Mock;

import Model.ConnPool;
import Model.User.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="login",value = "/login")
public class loginMock  extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user=null;

         if(email.equalsIgnoreCase("admin@admin.it")&&password.equalsIgnoreCase("admin"))
         {
             user= new User("admin@admin.it",true,"nomeAdmin","cognomeAdmin","admin");
             System.out.println(email+" logged in");
         }else if(email.equalsIgnoreCase("noadmin@noadmin.it")&&password.equalsIgnoreCase("noadmin"))
        {
            user= new User("noadmin@noadmin.it",false,"nomeUtente","cognomeUtente","noadmin");
        }
        if(user!=null)
            session.setAttribute("user",user);
        else{
            request.setAttribute("loginError",true);
        }
        RequestDispatcher dispatcher= request.getRequestDispatcher("./WEB-INF/index.jsp");
        dispatcher.forward(request,response);
    }
    void boh(){
        String hr="hello";
        java.lang.StringBuilder lol= new java.lang.StringBuilder();
        lol.isEmpty();
        hr.isEmpty();
    }
}
