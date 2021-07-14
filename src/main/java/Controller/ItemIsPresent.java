package Controller;

import Model.Cpu.Cpu;
import Model.Cpu.CpuDao;
import Model.Gpu.Gpu;
import Model.Gpu.GpuDao;
import Model.Memory.Memory;
import Model.Memory.MemoryDao;
import Model.Mobo.Mobo;
import Model.Mobo.MoboDao;
import Model.Psu.Psu;
import Model.Psu.PsuDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet(name="itemIsPresent",urlPatterns = "/admin/itemIsPresent")
public class ItemIsPresent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedItem = req.getParameter("requestedItem");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String path = req.getParameter("path");
        if(!path.isBlank()) {
            char separator = '\\';
            if (path.lastIndexOf('\\') < path.lastIndexOf('/'))
                separator = '/';
            path = path.substring(path.lastIndexOf(separator) + 1);
            System.out.println(path);
            File file = new File(ImagePaths.uploadPath + path);
            if (file.exists())
                resp.getWriter().print("fileIsPresent");
        }
        resp.setCharacterEncoding("UTF-8");
        switch (requestedItem) {
            case "gpus":
                GpuDao gpuDao = new GpuDao();
                ArrayList<Gpu> listg = null;
                try {
                    listg = gpuDao.doRetrieveByName(name, 1000, 0);
                    for (Gpu gpu : listg)
                        if (gpu.getName().equalsIgnoreCase(name) && gpu.getId() != Integer.parseInt(id))
                            resp.getWriter().print("itemIsPresent");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "cpus":
                CpuDao cpuDao = new CpuDao();
                ArrayList<Cpu> listc = null;
                try {
                    listc = cpuDao.doRetrieveByName(name, 1000, 0);
                    for (Cpu cpu : listc)
                        if (cpu.getName().equalsIgnoreCase(name) && cpu.getId() != Integer.parseInt(id))
                            resp.getWriter().print("itemIsPresent");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "psus":
                PsuDao psuDao = new PsuDao();
                ArrayList<Psu> listp = null;
                try {
                    listp = psuDao.doRetrieveByName(name, 1000, 0);
                    for (Psu psu : listp)
                        if (psu.getName().equalsIgnoreCase(name) && psu.getId() != Integer.parseInt(id))
                            resp.getWriter().print("itemIsPresent");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "motherboards":
                MoboDao moboDao = new MoboDao();
                ArrayList<Mobo> listm = null;
                try {
                    listm = moboDao.doRetrieveByName(name, 1000, 0);
                    for (Mobo mobo : listm)
                        if (mobo.getName().equalsIgnoreCase(name) && mobo.getId() != Integer.parseInt(id))
                            resp.getWriter().print("itemIsPresent");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "memories":
                MemoryDao memoryDao = new MemoryDao();
                ArrayList<Memory> listmem = null;
                try {
                    listmem = memoryDao.doRetrieveByName(name, 1000, 0);
                    for (Memory memory : listmem)
                        if (memory.getName().equalsIgnoreCase(name) && memory.getId() != Integer.parseInt(id))
                            resp.getWriter().print("itemIsPresent");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }
}

