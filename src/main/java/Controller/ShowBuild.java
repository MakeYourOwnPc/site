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
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet(name="showBuild",urlPatterns = "/showBuild")
public class ShowBuild extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idBuild = Integer.parseInt(req.getParameter("id"));
        String referer = req.getHeader("referer");
        URI uri = URI.create(referer);
        String path = uri.getPath();
        referer = path.substring(path.lastIndexOf(File.separator) + 1);
        req.setAttribute("referer", referer);
        BuildDao buildDao = new BuildDao();
        try {
            Gson gson = new Gson();
            Build build = buildDao.doRetrieveById(idBuild);
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
            req.setAttribute("gpu",gson.toJson(gpu));
            req.setAttribute("cpu",gson.toJson(cpu));
            req.setAttribute("psu",gson.toJson(psu));
            req.setAttribute("mobo",gson.toJson(mobo));
            req.setAttribute("pcCase",gson.toJson(pcCase));
            req.setAttribute("memories",gson.toJson(memories));
            req.setAttribute("type",gson.toJson(build.getType()));
            req.setAttribute("suggested",gson.toJson(build.isSuggested()));
            req.setAttribute("id",build.getId());
            RequestDispatcher dispatcher=req.getRequestDispatcher("/build.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
