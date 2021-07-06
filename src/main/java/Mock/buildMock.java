package Mock;

import Model.Build.BuildNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@WebServlet(name="build",value="/build.html")
public class buildMock {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/build.jsp");
        dispatcher.forward(request,response);
    }
}
