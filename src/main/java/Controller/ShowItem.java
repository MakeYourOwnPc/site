package Controller;

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
import Model.User.User;
import Model.User.UserDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="showItem",urlPatterns = "/showItem")
public class ShowItem extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedItem = req.getParameter("requestedItem");
        switch(requestedItem){
            case "users":
                String email = req.getParameter("email");
                UserDao userDao = new UserDao();
                try {
                    User user = userDao.doRetrieveByEmail(email);
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "gpus":
                String idGpu = req.getParameter("id");
                GpuDao gpuDao = new GpuDao();
                try {
                    Gpu gpu = gpuDao.doRetrieveById(Integer.parseInt(idGpu));
                    Gson gson = new Gson();
                    String json = gson.toJson(gpu);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "cpus":
                String idCpu = req.getParameter("id");
                CpuDao cpuDao = new CpuDao();
                try {
                    Cpu cpu = cpuDao.doRetrieveById(Integer.parseInt(idCpu));
                    Gson gson = new Gson();
                    String json = gson.toJson(cpu);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "psus":
                String idPsu = req.getParameter("id");
                PsuDao psuDao = new PsuDao();
                try {
                    Psu psu = psuDao.doRetrieveById(Integer.parseInt(idPsu));
                    Gson gson = new Gson();
                    String json = gson.toJson(psu);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "memories":
                String idMemory = req.getParameter("id");
                MemoryDao memoryDao = new MemoryDao();
                try {
                    Memory memory = memoryDao.doRetrieveById(Integer.parseInt(idMemory));
                    Gson gson = new Gson();
                    String json = gson.toJson(memory);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "mobos":
                String idMobo = req.getParameter("id");
                MoboDao moboDao = new MoboDao();
                try {
                    Mobo mobo = moboDao.doRetrieveById(Integer.parseInt(idMobo));
                    Gson gson = new Gson();
                    String json = gson.toJson(mobo);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "cases":
                String idCase = req.getParameter("id");
                PcCaseDao pcCaseDao = new PcCaseDao();
                try {
                    PcCase pcCase = pcCaseDao.doRetrieveById(Integer.parseInt(idCase));
                    Gson gson = new Gson();
                    String json = gson.toJson(pcCase);
                    resp.setContentType("plain/text");
                    resp.setCharacterEncoding("UTF-8");
                    resp.getWriter().print(json);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "builds":;
        }
    }
}
