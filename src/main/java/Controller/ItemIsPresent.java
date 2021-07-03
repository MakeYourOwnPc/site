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
        resp.setContentType("plain/text");
        resp.setCharacterEncoding("UTF-8");
        switch (requestedItem) {
            case "gpus" -> {
                GpuDao gpuDao = new GpuDao();
                ArrayList<Gpu> list = null;
                try {
                    list = gpuDao.doRetrieveByName(name, 1000, 0);
                    for (Gpu gpu : list) {
                        if (gpu.getName().equalsIgnoreCase(name) && gpu.getId() != Integer.parseInt(id)) {
                            resp.getWriter().print("true");
                            return;
                        }
                    }
                    resp.getWriter().print("false");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            case "cpus" -> {
                CpuDao cpuDao = new CpuDao();
                ArrayList<Cpu> list = null;
                try {
                    list = cpuDao.doRetrieveByName(name, 1000, 0);
                    for (Cpu cpu : list) {
                        if (cpu.getName().equalsIgnoreCase(name) && cpu.getId() != Integer.parseInt(id)) {
                            resp.getWriter().print("true");
                            return;
                        }
                    }
                    resp.getWriter().print("false");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            case "psus" -> {
                PsuDao psuDao = new PsuDao();
                ArrayList<Psu> list = null;
                try {
                    list = psuDao.doRetrieveByName(name, 1000, 0);
                    for (Psu psu : list) {
                        if (psu.getName().equalsIgnoreCase(name) && psu.getId() != Integer.parseInt(id)) {
                            resp.getWriter().print("true");
                            return;
                        }
                        resp.getWriter().print("false");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            case "motherboards" -> {
                MoboDao moboDao = new MoboDao();
                ArrayList<Mobo> list = null;
                try {
                    list = moboDao.doRetrieveByName(name, 1000, 0);
                    for (Mobo mobo : list) {
                        if (mobo.getName().equalsIgnoreCase(name) && mobo.getId() != Integer.parseInt(id)) {
                            resp.getWriter().print("true");
                            return;
                        }
                    }
                    resp.getWriter().print("false");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            case "memories" -> {
                MemoryDao memoryDao = new MemoryDao();
                ArrayList<Memory> list = null;
                try {
                    list = memoryDao.doRetrieveByName(name, 1000, 0);
                    for (Memory memory : list) {
                        if (memory.getName().equalsIgnoreCase(name) && memory.getId() != Integer.parseInt(id)) {
                            resp.getWriter().print("true");
                            return;
                        }
                    }
                    resp.getWriter().print("false");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

