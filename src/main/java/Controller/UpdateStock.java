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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet(name="updateStock",urlPatterns = "/updateStock")
public class UpdateStock extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idBuild = (int)req.getAttribute("idBuild");
        CpuDao cpuDao = new CpuDao();
        GpuDao gpuDao = new GpuDao();
        MoboDao moboDao = new MoboDao();
        MemoryDao memoryDao = new MemoryDao();
        PsuDao psuDao = new PsuDao();
        PcCaseDao pcCaseDao = new PcCaseDao();
        BuildDao buildDao = new BuildDao();
        try {
            Build build = buildDao.doRetrieveById(idBuild);
            Gpu gpu = null;
            if(build.getGpu()!=0) gpuDao.doRetrieveById(build.getGpu());
            Cpu cpu = cpuDao.doRetrieveById(build.getCpu());
            Mobo mobo = moboDao.doRetrieveById(build.getMobo());
            Psu psu = psuDao.doRetrieveById(build.getPsu());
            PcCase pcCase = pcCaseDao.doRetrieveById(build.getPcCase());
            ArrayList<Integer> listIds = build.getMemories();
            ArrayList<Memory> listMem = new ArrayList<>();
            for(int id:listIds)
                listMem.add(memoryDao.doRetrieveById(id));
            if(gpu!=null) {
                gpu.setStock(gpu.getStock() - 1);
                gpuDao.doUpdateStock(gpu);
            }
            cpu.setStock(cpu.getStock()-1);
            cpuDao.doUpdateStock(cpu);
            mobo.setStock(mobo.getStock()-1);
            moboDao.doUpdateStock(mobo);
            psu.setStock(psu.getStock()-1);
            psuDao.doUpdateStock(psu);
            pcCase.setStock(pcCase.getStock()-1);
            pcCaseDao.doUpdateStock(pcCase);
            for(Memory mem:listMem) {
                mem.setStock(mem.getStock() - 1);
                memoryDao.doUpdateStock(mem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
