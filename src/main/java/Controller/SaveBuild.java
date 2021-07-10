package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.Cpu.Cpu;
import Model.Cpu.CpuDao;
import Model.Gpu.Gpu;
import Model.Gpu.GpuDao;
import Model.Memory.Memory;
import Model.Memory.MemoryDao;
import Model.Mobo.Mobo;
import Model.Mobo.MoboDao;
import Model.PcCase.PcCase;
import Model.PcCase.PcCaseDao;
import Model.Psu.Psu;
import Model.Psu.PsuDao;
import Model.ShoppingCart.ShoppingCart;
import Model.ShoppingCart.ShoppingCartDao;
import Model.User.User;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="saveBuild",urlPatterns = "/saveBuild")
public class SaveBuild extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        HttpSession session = req.getSession();
        Gson gson = new Gson();
        String buildJson = req.getParameter("build");
        String referer = req.getParameter("referer");
        BuildDao buildDao = new BuildDao();
        Build build = gson.fromJson(buildJson,Build.class);
        int idBuild = build.getId();
        resp.setContentType("plain/text");
        resp.setCharacterEncoding("UTF-8");
        try {
            GpuDao gpuDao = new GpuDao();
            CpuDao cpuDao = new CpuDao();
            PsuDao psuDao = new PsuDao();
            MoboDao moboDao = new MoboDao();
            MemoryDao memoryDao = new MemoryDao();
            PcCaseDao pcCaseDao = new PcCaseDao();
            Gpu gpu = gpuDao.doRetrieveById(build.getGpu());
            Cpu cpu = cpuDao.doRetrieveById(build.getCpu());
            Mobo mobo = moboDao.doRetrieveById(build.getMobo());
            Psu psu = psuDao.doRetrieveById(build.getPsu());
            PcCase pcCase = pcCaseDao.doRetrieveById(build.getPcCase());
            ArrayList<Memory> memories = new ArrayList<>();
            ArrayList<Integer> memIds = build.getMemories();
            for(Integer idMem:memIds)
                memories.add(memoryDao.doRetrieveById(idMem));
            if(!referer.equals("admin")) {
                session.setAttribute("gpu", gson.toJson(gpu));
                session.setAttribute("cpu", gson.toJson(cpu));
                session.setAttribute("psu", gson.toJson(psu));
                session.setAttribute("mobo", gson.toJson(mobo));
                session.setAttribute("pcCase", gson.toJson(pcCase));
                session.setAttribute("memories", gson.toJson(memories));
                session.setAttribute("type", gson.toJson(build.getType()));
                session.setAttribute("suggested", gson.toJson(build.isSuggested()));
            }
            if(user!=null) {
                if(idBuild!=0) {
                    if(!user.isAdmin())
                     if (!user.getEmail().equals(build.getMaker())) {
                         resp.setStatus(403);
                         return;
                     }
                    buildDao.doUpdate(build);
                }
                else {
                        build.setMaker(user.getEmail());
                        buildDao.doSave(build);
                    }
                if(referer.equals("admin"))
                    resp.sendRedirect("/MYOPSite_war_exploded/admin");
                session.setAttribute("id",build.getId());
            }
            resp.getWriter().print(build.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
