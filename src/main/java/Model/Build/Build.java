    package Model.Build;

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

import java.sql.SQLException;
import java.util.ArrayList;

public class Build {
    private int mobo;
    private int gpu;
    private int cpu;
    private ArrayList<Integer> memories;
    private int id;
    private int pcCase;
    private int psu;

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    private String maker;
    private boolean suggested;
    private String type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public boolean isSuggested() {
        return suggested;
    }

    public void setSuggested(boolean suggested) {
        this.suggested = suggested;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Build() {
        this.memories = new ArrayList<>();
    }

    public Build(int mobo, int gpu, ArrayList<Integer> memories, int pcCase, int psu, String maker) throws BuildException, SQLException {
        this.mobo = mobo;
        this.gpu = gpu;
        this.memories = memories;
        this.pcCase = pcCase;
        this.psu = psu;
        this.maker = maker;
        verifier();
    }
    public int getMobo() {
        return mobo;
    }

    public void setMobo(int mobo) {
        this.mobo = mobo;
    }

    public int getGpu() {
        return gpu;
    }

    public void setGpu(int gpu) {
        this.gpu = gpu;
    }

    public ArrayList<Integer> getMemories() {
        return memories;
    }

    public void setMemories(ArrayList<Integer> memories) {
        this.memories = memories;
    }

    public int getPcCase() {
        return pcCase;
    }

    public void setPcCase(int pcCase) {
        this.pcCase = pcCase;
    }

    public int getPsu() {
        return psu;
    }

    public void setPsu(int psu) {
        this.psu = psu;
    }

    public void addMemory(Integer memory) {
        memories.add(memory);
    }

    public float price() throws SQLException{
        float price=0;
        MoboDao moboDao = new MoboDao();
        CpuDao cpuDao = new CpuDao();
        GpuDao gpuDao = new GpuDao();
        PsuDao psuDao = new PsuDao();
        PcCaseDao pcCaseDao = new PcCaseDao();
        MemoryDao memoryDao = new MemoryDao();
        Mobo mobo = moboDao.doRetrieveById(this.mobo);
        Cpu cpu = cpuDao.doRetrieveById(this.cpu);
        Gpu gpu = gpuDao.doRetrieveById(this.gpu);
        PcCase pcCase = pcCaseDao.doRetrieveById(this.pcCase);
        Psu psu = psuDao.doRetrieveById(this.psu);
        ArrayList<Memory> memories = new ArrayList<>();
        for(int id:this.memories){
            memories.add(memoryDao.doRetrieveById(id));
        }
        for(Memory memory:memories){
            price+=memory.getPrice();
        }
        return (mobo.getPrice()+gpu.getPrice()+ cpu.getPrice()+pcCase.getPrice()+ psu.getPrice()+price);

    }

    public boolean verifier() throws SQLException,BuildException {
        MoboDao moboDao = new MoboDao();
        CpuDao cpuDao = new CpuDao();
        GpuDao gpuDao = new GpuDao();
        PsuDao psuDao = new PsuDao();
        PcCaseDao pcCaseDao = new PcCaseDao();
        MemoryDao memoryDao = new MemoryDao();
        Mobo mobo = moboDao.doRetrieveById(this.mobo);
        Cpu cpu = cpuDao.doRetrieveById(this.cpu);
        Gpu gpu = gpuDao.doRetrieveById(this.gpu);
        PcCase pcCase = pcCaseDao.doRetrieveById(this.pcCase);
        Psu psu = psuDao.doRetrieveById(this.psu);
        ArrayList<Memory> memories = new ArrayList<>();
        for(int id:this.memories){
            memories.add(memoryDao.doRetrieveById(id));
        }
        int sataDrives = 0;
        int nvmeDrives = 0;
        int ramMemories = 0;
        int consumption=0;

        for (Memory memory : memories) {
            if (memory.ismType()) {
                if (memory.getSocket().equalsIgnoreCase("nvme"))
                    nvmeDrives++;
                if (memory.getSocket().equalsIgnoreCase("sata"))
                    sataDrives++;
            } else {
                if (memory.getSocket().equalsIgnoreCase(mobo.getRamSocket()))
                    ramMemories += memory.getAmountMemories();
                else throw new BuildException("ramSocketError");
            }
            consumption+=memory.getConsumption();
        }
        if (nvmeDrives > mobo.getAmountSlotNvme())
            throw new BuildException("nvmeError");
        if (sataDrives > mobo.getAmountSlotSata())
            throw new BuildException("sataError");
        if (ramMemories > mobo.getAmountSlotRam())
            throw new BuildException("ramSticksError");

        consumption+= gpu.getConsumption();

        if(!cpu.getSocket().equalsIgnoreCase(mobo.getCpuSocket()))
            throw new BuildException("cpuError");
        consumption+= cpu.getConsumption();
        String moboFormFactor=mobo.getFormFactor();
        switch (pcCase.getFormFactor().toLowerCase()){
            case"mini-itx":if(moboFormFactor.equals(("micro"))) throw new BuildException("caseSmallerThanMobo");
            case"micro-atx":if(moboFormFactor.equals(("atx"))) throw new BuildException("caseSmallerThanMobo");
            case"atx":if(moboFormFactor.equals("eatx")) throw new BuildException("caseSmallerThanMobo");
        }
        consumption+=mobo.getConsumption();

        if(psu.getPower()<consumption)
            throw new BuildException("tooMuchPowerConsumption");

        return true;

    }

}



