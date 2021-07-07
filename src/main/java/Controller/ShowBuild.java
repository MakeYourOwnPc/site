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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet(name="showBuild",urlPatterns = "/showBuild")
public class ShowBuild extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idBuild = Integer.parseInt(req.getParameter("idBuild"));
        BuildDao buildDao = new BuildDao();
        try {
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
            for(Integer idMem:build.getMemories())
                memories.add(memoryDao.doRetrieveById(idMem));
            req.setAttribute("gpu",gpu);
            req.setAttribute("cpu",cpu);
            req.setAttribute("psu",psu);
            req.setAttribute("mobo",mobo);
            req.setAttribute("pcCase",pcCase);
            req.setAttribute("memories",memories);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/build.jsp");
            dispatcher.forward(req,resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
