package Controller;

import Model.Build.Build;
import Model.Build.BuildDao;
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
import Model.User.User;
import Model.User.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="modifyDB",urlPatterns = "/admin/modifyDB")
@MultipartConfig
public class ModifyDB extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String option = req.getParameter("option");
        String requestedItem = req.getParameter("requestedItem");
        if (user == null|| !user.isAdmin()){
            resp.setStatus(403);
            return;
        }
        resp.setCharacterEncoding("UTF-8");
        switch (option) {
            case "insert":
                try {
                    insert(requestedItem, req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "update":
                try {
                    update(requestedItem, req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    delete(requestedItem, req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }
    public void delete(String requestedItem,HttpServletRequest req,HttpServletResponse resp) throws SQLException,ServletException,IOException{
        String id;
        switch (requestedItem) {
            case "users":
                String email = req.getParameter("email");
                UserDao userDao = new UserDao();
                if (email != null)
                    resp.getWriter().print(userDao.doDelete(email));
                break;
            case "gpus":
                GpuDao gpuDao = new GpuDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(gpuDao.doDelete(Integer.parseInt(id)));
                break;
            case "cpus":
                CpuDao cpuDao = new CpuDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(cpuDao.doDelete(Integer.parseInt(id)));
                break;
            case "psus":
                PsuDao psuDao = new PsuDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(psuDao.doDelete(Integer.parseInt(id)));
                break;
            case "cases":
                PcCaseDao pcCaseDao = new PcCaseDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(pcCaseDao.doDelete(Integer.parseInt(id)));
                break;
            case "motherboards":
                MoboDao moboDao = new MoboDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(moboDao.doDelete(Integer.parseInt(id)));
                break;
            case "memories":
                MemoryDao memoryDao = new MemoryDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(memoryDao.doDelete(Integer.parseInt(id)));
                break;
            case "builds":
                BuildDao buildDao = new BuildDao();
                 id = req.getParameter("id");
                if (id != null)
                    resp.getWriter().print(buildDao.doDelete(Integer.parseInt(id)));
                break;
            case "countries":
                CountryDao countryDao = new CountryDao();
                 id = req.getParameter("id");
                if (id != null) {
                    ServletContext servletContext = req.getServletContext();
                    synchronized (servletContext) {
                        ArrayList<Country> countries = (ArrayList<Country>) servletContext.getAttribute("countries");
                        for (int i = 0; i < countries.size(); i++)
                            if (countries.get(i).getId().equals(id))
                                countries.remove(i);
                        servletContext.setAttribute("countries", countries);
                    }
                    resp.getWriter().print(countryDao.doDelete(id));
                }
                break;
        }
        resp.sendRedirect("/MYOPSite_war_exploded/admin");
    }
    public void insert(String requestedItem, HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        switch (requestedItem) {
            case "gpus":
                GpuDao gpuDao = new GpuDao();
                Gpu gpu = new Gpu();
                String nameGpu = req.getParameter("name");
                String priceGpu = req.getParameter("price");
                String consumptionGpu = req.getParameter("consumption");
                String stockGpu = req.getParameter("stock");
                Part filePartGpu = req.getPart("image");
                String fileNameGpu = Paths.get(filePartGpu.getSubmittedFileName()).getFileName().toString();
                System.out.println(fileNameGpu);
                if (nameGpu.isBlank() || priceGpu.isBlank() || consumptionGpu.isBlank() || stockGpu.isBlank() || Integer.parseInt(stockGpu) < 0) {
                    resp.setStatus(500);
                    return;
                }
                gpu.setName(nameGpu);
                gpu.setPrice(Float.parseFloat(priceGpu));
                gpu.setConsumption(Integer.parseInt(consumptionGpu));
                gpu.setStock(Integer.parseInt(stockGpu));
                if (!fileNameGpu.isBlank()) {
                    gpu.setImagePath(ImagePaths.imagePath + fileNameGpu);
                    try (InputStream inputStream = filePartGpu.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameGpu);
                        Files.copy(inputStream, file.toPath());
                    }
                } else
                    gpu.setImagePath(ImagePaths.defaultPath);
                System.out.println(gpu.getImagePath());
                resp.getWriter().print(gpuDao.doSave(gpu));
                break;
            case "cpus":
                CpuDao cpuDao = new CpuDao();
                Cpu cpu = new Cpu();
                String nameCpu = req.getParameter("name");
                String priceCpu = req.getParameter("price");
                String socketCpu = req.getParameter("CPUsocket");
                String integratedGpu = req.getParameter("integratedGpu");
                String consumptionCpu = req.getParameter("consumption");
                String stockCpu = req.getParameter("stock");
                Part filePartCpu = req.getPart("image");
                String fileNameCpu = Paths.get(filePartCpu.getSubmittedFileName()).getFileName().toString();
                if (nameCpu.isBlank() || priceCpu.isBlank() || socketCpu.isBlank() || integratedGpu.isBlank() || !(integratedGpu.equals("true") || integratedGpu.equals("false")) || consumptionCpu.isBlank() || stockCpu.isBlank() || Integer.parseInt(stockCpu) < 0) {
                    resp.setStatus(500);
                    return;
                }
                cpu.setName(nameCpu);
                cpu.setPrice(Float.parseFloat(priceCpu));
                cpu.setSocket(socketCpu);
                cpu.setIntegratedgpu(Boolean.parseBoolean(integratedGpu));
                cpu.setConsumption(Integer.parseInt(consumptionCpu));
                cpu.setStock(Integer.parseInt(stockCpu));
                if (!fileNameCpu.isBlank()) {
                    cpu.setImagePath(ImagePaths.imagePath + fileNameCpu);
                    try (InputStream inputStream = filePartCpu.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameCpu);
                        Files.copy(inputStream, file.toPath());
                    }
                } else
                    cpu.setImagePath(ImagePaths.defaultPath);
                resp.getWriter().print(cpuDao.doSave(cpu));
                break;
            case "psus":
                PsuDao psuDao = new PsuDao();
                Psu psu = new Psu();
                String namePsu = req.getParameter("name");
                String pricePsu = req.getParameter("price");
                String power = req.getParameter("power");
                String stockPsu = req.getParameter("stock");
                Part filePartPsu = req.getPart("image");
                String fileNamePsu = Paths.get(filePartPsu.getSubmittedFileName()).getFileName().toString();
                if (namePsu.isBlank() || pricePsu.isBlank() || power.isBlank() || stockPsu.isBlank() || Integer.parseInt(stockPsu) < 0) {
                    resp.setStatus(500);
                    return;
                }
                psu.setName(namePsu);
                psu.setPrice(Float.parseFloat(pricePsu));
                psu.setPower(Integer.parseInt(power));
                psu.setStock(Integer.parseInt(stockPsu));
                if (!fileNamePsu.isBlank()) {
                    psu.setImagePath(ImagePaths.imagePath + fileNamePsu);
                    try (InputStream inputStream = filePartPsu.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNamePsu);
                        Files.copy(inputStream, file.toPath());
                    }
                } else
                    psu.setImagePath(ImagePaths.defaultPath);
                resp.getWriter().print(psuDao.doSave(psu));
                break;
            case "cases":
                PcCaseDao pcCaseDao = new PcCaseDao();
                PcCase pcCase = new PcCase();
                String nameCase = req.getParameter("name");
                String priceCase = req.getParameter("price");
                String formFactorCase = req.getParameter("formFactor");
                String stockCase = req.getParameter("stock");
                Part filePartCase = req.getPart("image");
                String fileNameCase = Paths.get(filePartCase.getSubmittedFileName()).getFileName().toString();
                if (nameCase.isBlank() || priceCase.isBlank() || formFactorCase.isBlank() || stockCase.isBlank() || Integer.parseInt(stockCase) < 0) {
                    resp.setStatus(500);
                    return;
                }
                pcCase.setName(nameCase);
                pcCase.setPrice(Float.parseFloat(priceCase));
                pcCase.setFormFactor(formFactorCase);
                pcCase.setStock(Integer.parseInt(stockCase));
                if (!fileNameCase.isBlank()) {
                    pcCase.setImagePath(ImagePaths.imagePath + fileNameCase);
                    try (InputStream inputStream = filePartCase.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameCase);
                        Files.copy(inputStream, file.toPath());
                    }
                } else
                    pcCase.setImagePath(ImagePaths.defaultPath);
                resp.getWriter().print(pcCaseDao.doSave(pcCase));
                break;
            case "motherboards":
                MoboDao moboDao = new MoboDao();
                Mobo mobo = new Mobo();
                String nameMobo = req.getParameter("name");
                String priceMobo = req.getParameter("price");
                String consumptionMobo = req.getParameter("consumption");
                String formFactorMobo = req.getParameter("formFactor");
                String amountSlotNvme = req.getParameter("nNVMESockets");
                String amountSlotRam = req.getParameter("nRAMSockets");
                String amountSlotSata = req.getParameter("nSATASockets");
                String cpuSocket = req.getParameter("CPUsocket");
                String ramSocket = req.getParameter("RAMsocket");
                String stockMobo = req.getParameter("stock");
                Part filePartMobo = req.getPart("image");
                String fileNameMobo = Paths.get(filePartMobo.getSubmittedFileName()).getFileName().toString();
                if (nameMobo.isBlank() || priceMobo.isBlank() || consumptionMobo.isBlank() || formFactorMobo.isBlank() || amountSlotNvme.isBlank() || Integer.parseInt(amountSlotNvme) < 0 || amountSlotRam.isBlank() || Integer.parseInt(amountSlotRam) < 0 || amountSlotSata.isBlank() || Integer.parseInt(amountSlotSata) < 0 || cpuSocket.isBlank() || ramSocket.isBlank() || stockMobo.isBlank() || Integer.parseInt(stockMobo) < 0) {
                    resp.setStatus(500);
                    return;
                }
                mobo.setName(nameMobo);
                mobo.setPrice(Float.parseFloat(priceMobo));
                mobo.setConsumption(Integer.parseInt(consumptionMobo));
                mobo.setFormFactor(formFactorMobo);
                mobo.setAmountSlotNvme(Integer.parseInt(amountSlotNvme));
                mobo.setAmountSlotRam(Integer.parseInt(amountSlotRam));
                mobo.setAmountSlotSata(Integer.parseInt(stockMobo));
                mobo.setCpuSocket(cpuSocket);
                mobo.setRamSocket(ramSocket);
                mobo.setStock(Integer.parseInt(stockMobo));
                if (!fileNameMobo.isBlank()) {
                    mobo.setImagePath(ImagePaths.imagePath + fileNameMobo);
                    try (InputStream inputStream = filePartMobo.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameMobo);
                        Files.copy(inputStream, file.toPath());
                    }
                } else
                    mobo.setImagePath(ImagePaths.defaultPath);
                resp.getWriter().print(moboDao.doSave(mobo));
                break;
            case "memories":
                MemoryDao memoryDao = new MemoryDao();
                Memory memory = new Memory();
                String nameMemory = req.getParameter("name");
                String priceMemory = req.getParameter("price");
                String socketMemory = req.getParameter("MEMsocket");
                String mType = req.getParameter("mType");
                String amountMemories = req.getParameter("amountOfMemories");
                String consumptionMemory = req.getParameter("consumption");
                String stockMemory = req.getParameter("stock");
                Part filePartMemory = req.getPart("image");
                String fileNameMemory = Paths.get(filePartMemory.getSubmittedFileName()).getFileName().toString();
                if (nameMemory.isBlank() || socketMemory.isBlank() || mType.isBlank() || !(mType.equals("true") || mType.equals("false")) || priceMemory.isBlank() || consumptionMemory.isBlank() || amountMemories.isBlank() || Integer.parseInt(amountMemories) < 0 || stockMemory.isBlank() || Integer.parseInt(stockMemory) < 0) {
                    resp.setStatus(500);
                    return;
                }
                memory.setName(nameMemory);
                memory.setSocket(socketMemory);
                memory.setmType(Boolean.parseBoolean(mType));
                memory.setPrice(Float.parseFloat(priceMemory));
                memory.setConsumption(Integer.parseInt(consumptionMemory));
                memory.setAmountMemories(Integer.parseInt(amountMemories));
                memory.setStock(Integer.parseInt(stockMemory));
                if (!fileNameMemory.isBlank()) {
                    memory.setImagePath(ImagePaths.imagePath + fileNameMemory);
                    try (InputStream inputStream = filePartMemory.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameMemory);
                        Files.copy(inputStream, file.toPath());
                    }
                } else
                    memory.setImagePath(ImagePaths.defaultPath);
                resp.getWriter().print(memoryDao.doSave(memory));
                break;
            case "countries":
                ServletContext servletContext = req.getServletContext();
                CountryDao countryDao = new CountryDao();
                String countryId = req.getParameter("id");
                String countryLabel = req.getParameter("label");
                Country country = new Country();
                if (!countryLabel.isBlank() && !countryLabel.isBlank()) {
                    country.setId(countryId);
                    country.setName(countryLabel);
                    countryDao.doSave(country);
                    synchronized (servletContext) {
                        ArrayList<Country> countries = (ArrayList<Country>) servletContext.getAttribute("countries");
                        countries.add(country);
                        servletContext.setAttribute("countries", countries);
                    }
                }
                break;
        }
        resp.sendRedirect("/MYOPSite_war_exploded/admin");
    }
    public void update(String requestedItem, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchAlgorithmException, ServletException, IOException {
        switch (requestedItem) {
            case "users":
                UserDao userDao = new UserDao();
                String email = req.getParameter("email");
                boolean admin = Boolean.parseBoolean(req.getParameter("admin"));
                synchronized (userDao) {
                    resp.getWriter().print(userDao.doChangeAdmin(email, admin));
                    return;
                }
            case "gpus":
                String idGpu = req.getParameter("id");
                GpuDao gpuDao = new GpuDao();
                Gpu gpu = gpuDao.doRetrieveById(Integer.parseInt(idGpu));
                String nameGpu = req.getParameter("name");
                String priceGpu = req.getParameter("price");
                String consumptionGpu = req.getParameter("consumption");
                String stockGpu = req.getParameter("stock");
                Part filePartGpu = req.getPart("image");
                String fileNameGpu = Paths.get(filePartGpu.getSubmittedFileName()).getFileName().toString();
                if (!nameGpu.isBlank())
                    gpu.setName(nameGpu);
                if (!priceGpu.isBlank())
                    gpu.setPrice(Float.parseFloat(priceGpu));
                if (!consumptionGpu.isBlank())
                    gpu.setConsumption(Integer.parseInt(consumptionGpu));
                if (!stockGpu.isBlank() && Integer.parseInt(stockGpu) >= 0)
                    gpu.setStock(Integer.parseInt(stockGpu));
                if (!fileNameGpu.isBlank()) {
                    ImagePaths.checkFile(gpu.getImagePath());
                    gpu.setImagePath(ImagePaths.imagePath + fileNameGpu);
                    try (InputStream inputStream = filePartGpu.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameGpu);
                        Files.copy(inputStream, file.toPath());
                    }
                }
                synchronized (gpuDao) {
                    resp.getWriter().print(gpuDao.doUpdate(gpu));
                }
                break;
            case "cpus":
                String idCpu = req.getParameter("id");
                CpuDao cpuDao = new CpuDao();
                Cpu cpu = cpuDao.doRetrieveById(Integer.parseInt(idCpu));
                String nameCpu = req.getParameter("name");
                String priceCpu = req.getParameter("price");
                String socketCpu = req.getParameter("CPUsocket");
                String integratedGpu = req.getParameter("integratedGpu");
                String consumptionCpu = req.getParameter("consumption");
                String stockCpu = req.getParameter("stock");
                Part filePartCpu = req.getPart("image");
                String fileNameCpu = Paths.get(filePartCpu.getSubmittedFileName()).getFileName().toString();
                if (!nameCpu.isBlank())
                    cpu.setName(nameCpu);
                if (!priceCpu.isBlank())
                    cpu.setPrice(Float.parseFloat(priceCpu));
                if (!socketCpu.isBlank())
                    cpu.setSocket(socketCpu);
                if (!integratedGpu.isBlank())
                    cpu.setIntegratedgpu(Boolean.parseBoolean(integratedGpu));
                if (!consumptionCpu.isBlank())
                    cpu.setConsumption(Integer.parseInt(consumptionCpu));
                if (!stockCpu.isBlank() && Integer.parseInt(stockCpu) >= 0)
                    cpu.setStock(Integer.parseInt(stockCpu));
                if (!fileNameCpu.isBlank()) {
                    ImagePaths.checkFile(cpu.getImagePath());
                    cpu.setImagePath(ImagePaths.imagePath + fileNameCpu);
                    try (InputStream inputStream = filePartCpu.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameCpu);
                        Files.copy(inputStream, file.toPath());
                    }
                }
                synchronized (cpuDao) {
                    resp.getWriter().print(cpuDao.doUpdate(cpu));
                }
                break;
            case "psus":
                String idPsu = req.getParameter("id");
                PsuDao psuDao = new PsuDao();
                Psu psu = psuDao.doRetrieveById(Integer.parseInt(idPsu));
                String namePsu = req.getParameter("name");
                String pricePsu = req.getParameter("price");
                String power = req.getParameter("power");
                String stockPsu = req.getParameter("stock");
                Part filePartPsu = req.getPart("image");
                String fileNamePsu = Paths.get(filePartPsu.getSubmittedFileName()).getFileName().toString();
                if (!namePsu.isBlank())
                    psu.setName(namePsu);
                if (!pricePsu.isBlank())
                    psu.setPrice(Float.parseFloat(pricePsu));
                if (!power.isBlank())
                    psu.setPower(Integer.parseInt(power));
                if (!stockPsu.isBlank() && Integer.parseInt(stockPsu) >= 0)
                    psu.setStock(Integer.parseInt(stockPsu));
                if (!fileNamePsu.isBlank()) {
                    ImagePaths.checkFile(psu.getImagePath());
                    psu.setImagePath(ImagePaths.imagePath + fileNamePsu);
                    try (InputStream inputStream = filePartPsu.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNamePsu);
                        Files.copy(inputStream, file.toPath());
                    }
                }
                synchronized (psuDao) {
                    resp.getWriter().print(psuDao.doUpdate(psu));
                }
                break;
            case "cases":
                String idCase = req.getParameter("id");
                PcCaseDao pcCaseDao = new PcCaseDao();
                PcCase pcCase = pcCaseDao.doRetrieveById(Integer.parseInt(idCase));
                String nameCase = req.getParameter("name");
                String priceCase = req.getParameter("price");
                String formFactorCase = req.getParameter("formFactor");
                String stockCase = req.getParameter("stock");
                Part filePartCase = req.getPart("image");
                String fileNameCase = Paths.get(filePartCase.getSubmittedFileName()).getFileName().toString();
                if (!nameCase.isBlank())
                    pcCase.setName(nameCase);
                if (!priceCase.isBlank())
                    pcCase.setPrice(Float.parseFloat(priceCase));
                if (!formFactorCase.isBlank())
                    pcCase.setFormFactor(formFactorCase);
                if (!stockCase.isBlank() && Integer.parseInt(stockCase) >= 0)
                    pcCase.setStock(Integer.parseInt(stockCase));
                if (!fileNameCase.isBlank()) {
                    ImagePaths.checkFile(pcCase.getImagePath());
                    pcCase.setImagePath(ImagePaths.imagePath + fileNameCase);
                    try (InputStream inputStream = filePartCase.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameCase);
                        Files.copy(inputStream, file.toPath());
                    }
                }
                synchronized (pcCaseDao) {
                    resp.getWriter().print(pcCaseDao.doUpdate(pcCase));
                }
                break;
            case "motherboards":
                String idMobo = req.getParameter("id");
                MoboDao moboDao = new MoboDao();
                Mobo mobo = moboDao.doRetrieveById(Integer.parseInt(idMobo));
                String nameMobo = req.getParameter("name");
                String priceMobo = req.getParameter("price");
                String consumptionMobo = req.getParameter("consumption");
                String formFactorMobo = req.getParameter("formFactor");
                String amountSlotNvme = req.getParameter("nNVMESockets");
                String amountSlotRam = req.getParameter("nRAMSockets");
                String amountSlotSata = req.getParameter("nSATASockets");
                String cpuSocket = req.getParameter("CPUsocket");
                String ramSocket = req.getParameter("RAMsocket");
                String stockMobo = req.getParameter("stock");
                Part filePartMobo = req.getPart("image");
                String fileNameMobo = Paths.get(filePartMobo.getSubmittedFileName()).getFileName().toString();
                if (!nameMobo.isBlank())
                    mobo.setName(nameMobo);
                if (!priceMobo.isBlank())
                    mobo.setPrice(Float.parseFloat(priceMobo));
                if (!consumptionMobo.isBlank())
                    mobo.setConsumption(Integer.parseInt(consumptionMobo));
                if (!formFactorMobo.isBlank())
                    mobo.setFormFactor(formFactorMobo);
                if (!amountSlotNvme.isBlank() && Integer.parseInt(amountSlotNvme) >= 0)
                    mobo.setAmountSlotNvme(Integer.parseInt(amountSlotNvme));
                if (!amountSlotRam.isBlank() && Integer.parseInt(amountSlotRam) >= 0)
                    mobo.setAmountSlotRam(Integer.parseInt(amountSlotRam));
                if (!amountSlotSata.isBlank() && Integer.parseInt(amountSlotSata) >= 0)
                    mobo.setAmountSlotSata(Integer.parseInt(stockMobo));
                if (!cpuSocket.isBlank())
                    mobo.setCpuSocket(cpuSocket);
                if (!ramSocket.isBlank())
                    mobo.setRamSocket(ramSocket);
                if (!stockMobo.isBlank() && Integer.parseInt(stockMobo) >= 0)
                    mobo.setStock(Integer.parseInt(stockMobo));
                if (!fileNameMobo.isBlank()) {
                    ImagePaths.checkFile(mobo.getImagePath());
                    mobo.setImagePath(ImagePaths.imagePath + fileNameMobo);
                    try (InputStream inputStream = filePartMobo.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameMobo);
                        Files.copy(inputStream, file.toPath());
                    }
                }
                synchronized (moboDao) {
                    resp.getWriter().print(moboDao.doUpdate(mobo));
                }
                break;
            case "memories":
                String idMemory = req.getParameter("id");
                MemoryDao memoryDao = new MemoryDao();
                Memory memory = memoryDao.doRetrieveById(Integer.parseInt(idMemory));
                String nameMemory = req.getParameter("name");
                String priceMemory = req.getParameter("price");
                String socketMemory = req.getParameter("MEMsocket");
                String mType = req.getParameter("mType");
                String amountMemories = req.getParameter("amountOfMemories");
                String consumptionMemory = req.getParameter("consumption");
                String stockMemory = req.getParameter("stock");
                Part filePartMemory = req.getPart("image");
                String fileNameMemory = Paths.get(filePartMemory.getSubmittedFileName()).getFileName().toString();
                if (!nameMemory.isBlank())
                    memory.setName(nameMemory);
                if (!socketMemory.isBlank())
                    memory.setSocket(socketMemory);
                if (!mType.isBlank() && (mType.equals("true") || mType.equals("false")))
                    memory.setmType(Boolean.parseBoolean(mType));
                if (!priceMemory.isBlank())
                    memory.setPrice(Float.parseFloat(priceMemory));
                if (!consumptionMemory.isBlank())
                    memory.setConsumption(Integer.parseInt(consumptionMemory));
                if (!amountMemories.isBlank() && Integer.parseInt(amountMemories) >= 0)
                    memory.setAmountMemories(Integer.parseInt(amountMemories));
                if (!stockMemory.isBlank() && Integer.parseInt(stockMemory) >= 0)
                    memory.setStock(Integer.parseInt(stockMemory));
                if (!fileNameMemory.isBlank()) {
                    ImagePaths.checkFile(memory.getImagePath());
                    memory.setImagePath(ImagePaths.imagePath + fileNameMemory);
                    try (InputStream inputStream = filePartMemory.getInputStream()) {
                        File file = new File(ImagePaths.uploadPath + fileNameMemory);
                        Files.copy(inputStream, file.toPath());
                    }
                }
                synchronized (memoryDao) {
                    resp.getWriter().print(memoryDao.doUpdate(memory));
                }
                break;
        }
        resp.sendRedirect("/MYOPSite_war_exploded/admin");
    }
}
