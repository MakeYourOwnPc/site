package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.Build.BuildNames;
import Model.Country.Country;
import Model.Country.CountryDao;
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
import Model.Purchase.Purchase;
import Model.Purchase.PurchaseDao;
import Model.User.User;
import Model.User.UserDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(name="itemsLister",urlPatterns = "/itemsLister")
public class ItemsLister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedItem = req.getParameter("requestedItem");
        switch (requestedItem) {
            case "cpus" -> {
                CpuDao cpuDao = new CpuDao();
                String nameCpu = req.getParameter("name");
                String socketCpu = req.getParameter("CPUsocket");
                String test = req.getParameter("integratedGpu");
                Boolean integratedGpu;
                if (!test.isBlank())
                    integratedGpu = Boolean.valueOf(test);
                else
                    integratedGpu = null;
                if (nameCpu.isBlank() && socketCpu.isBlank() && test.isBlank()) {
                    try {
                        ArrayList<Cpu> list = cpuDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<Cpu> list = null;
                    try {
                        list = cpuDao.doRetrieveByParameters(nameCpu, socketCpu, integratedGpu, 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            case "gpus" -> {
                GpuDao gpuDao = new GpuDao();
                String nameGpu = req.getParameter("name");
                if (nameGpu.isBlank()) {
                    try {
                        ArrayList<Gpu> list = gpuDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<Gpu> list = null;
                    try {
                        list = gpuDao.doRetrieveByName(nameGpu, 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            case "memories" -> {
                MemoryDao memoryDao = new MemoryDao();
                String nameMemory = req.getParameter("name");
                String socketMemory = req.getParameter("MEMsocket");
                String testType = req.getParameter("mType");
                if (nameMemory.isBlank() && socketMemory.isBlank() && testType.isBlank()) {
                    try {
                        ArrayList<Memory> list = memoryDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<Memory> list = null;
                    try {
                        list = memoryDao.doRetrieveByParameters(nameMemory, socketMemory, testType.isBlank() ? null : Boolean.valueOf(testType), 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            case "motherboards" -> {
                MoboDao moboDao = new MoboDao();
                String nameMobo = req.getParameter("name");
                String socketRamMobo = req.getParameter("RAMsocket");
                String socketCpuMobo = req.getParameter("CPUsocket");
                String formFactor = req.getParameter("formFactor");
                String numSlotRam = req.getParameter("nRAMSockets");
                String numSlotSata = req.getParameter("nSATASockets");
                String numSlotNvme = req.getParameter("nNVMESockets");
                if (nameMobo.isBlank() && socketRamMobo.isBlank() && socketCpuMobo.isBlank() && formFactor.isBlank() && numSlotNvme.isBlank() && numSlotSata.isBlank() && numSlotRam.isBlank()) {
                    try {
                        ArrayList<Mobo> list = moboDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<Mobo> list = null;
                    try {
                        list = moboDao.doRetrieveByParameters(nameMobo, socketRamMobo, socketCpuMobo, formFactor, numSlotNvme.isBlank() ? 0 : Integer.parseInt(numSlotNvme), numSlotSata.isBlank() ? 0 : Integer.parseInt(numSlotSata), numSlotRam.isBlank() ? 0 : Integer.parseInt(numSlotRam), 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            case "psus" -> {
                PsuDao psuDao = new PsuDao();
                String namePsu = req.getParameter("name");
                String power = req.getParameter("power");
                if (namePsu.isBlank() && power.isBlank()) {
                    try {
                        ArrayList<Psu> list = psuDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<Psu> list = null;
                    try {
                        list = psuDao.doRetrieveByParameters(namePsu, power.isBlank() ? 0 : Integer.parseInt(power), 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            case "cases" -> {
                PcCaseDao pcCaseDao = new PcCaseDao();
                String namePcCase = req.getParameter("name");
                String formFactorCase = req.getParameter("formFactor");
                if (namePcCase.isBlank() && formFactorCase.isBlank()) {
                    try {
                        ArrayList<PcCase> list = pcCaseDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<PcCase> list = null;
                    try {
                        list = pcCaseDao.doRetrieveByParameters(namePcCase, formFactorCase, 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            case "builds" -> {
                BuildDao buildDao = new BuildDao();
                String mobo = req.getParameter("moboName");
                String cpu = req.getParameter("cpuName");
                String gpu = req.getParameter("gpuName");
                String psu = req.getParameter("psuName");
                String pcCase = req.getParameter("caseName");
                String maker = req.getParameter("maker");
                String suggested = req.getParameter("suggested");
                String type = req.getParameter("type");
                if (mobo.isBlank() && cpu.isBlank() && gpu.isBlank() && psu.isBlank() && pcCase.isBlank() && maker.isBlank() && suggested.isBlank() && type.isBlank()) {
                    try {
                        ArrayList<BuildNames> list = buildDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<BuildNames> list = null;
                    try {
                        list = buildDao.doRetrieveByParameters(mobo, cpu, gpu, psu, type, suggested.isBlank() ? null : Boolean.valueOf(suggested), 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            case "purchases" -> {
                PurchaseDao purchaseDao = new PurchaseDao();
                String email = req.getParameter("email");
                String startingDate = req.getParameter("startingDate");
                String endingDate = req.getParameter("endingDate");
                if (email.isBlank() && startingDate.isBlank() && endingDate.isBlank()) {
                    try {
                        ArrayList<Purchase> list = purchaseDao.doRetrieveAll(50, 0);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    ArrayList<Purchase> list = null;
                    try {
                        list = purchaseDao.doRetrieveByParameters(email,startingDate.isBlank()?new Date(0):Date.valueOf(startingDate),endingDate.isBlank()? Date.valueOf(LocalDate.now()):Date.valueOf(endingDate), 50, 0);
                        Gson gson = new Gson();
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(gson.toJson(list));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            case "countries" ->{
                CountryDao countryDao = new CountryDao();
                    try {
                        ArrayList<Country> list = countryDao.doRetrieveAll();
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        resp.setContentType("plain/text");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().print(json);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
            }
        }
    }
}
