package Mock;

import Model.Build.BuildNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="home",value="/index.html")
public class indexmock extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BuildNames build= new BuildNames();
        build.setCpu("Intel Grosso");
        build.setGpu("Scheda chi l'ha mai vista");
        build.setMobo("Gigabyte B550 GAMING X V2 ATX AM4 Motherboard");
        build.setId(1);
        build.setMaker("Gianfranco");
        build.setPsu("EVGA B5 550 W 80+ Bronze Certified Fully Modular ATX Power Supply");
        build.setPcCase("Phanteks Eclipse P300A Mesh ATX Mid Tower Case");
        build.addMemory("Memoria grande");
        build.addMemory("ram veloce");
        build.addMemory("ram veloce");
        build.setType("Gaming");
        ArrayList<BuildNames> builds=new ArrayList<>();
        builds.add(build);
        request.setAttribute("builds",builds);


        RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request,response);
    }
}
