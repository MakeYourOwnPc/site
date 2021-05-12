package Model.Build;

import Model.Cpu.Cpu;
import Model.Gpu.Gpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.PcCase.PcCase;
import Model.Psu.Psu;
import Model.User.User;

import java.util.ArrayList;

public class Build {
    private Mobo mobo;
    private Gpu gpu;
    private Cpu cpu;
    private ArrayList<Memory> memories;
    private int id;
    private PcCase pcCase;
    private Psu psu;
    private User user;
    private boolean suggested;
    private String type;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
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

    public Build(Mobo mobo, Gpu gpu, ArrayList<Memory> memories, PcCase pcCase, Psu psu, User user) throws BuildException {
        this.mobo = mobo;
        this.gpu = gpu;
        this.memories = memories;
        this.pcCase = pcCase;
        this.psu = psu;
        this.user = user;
        verifier();
    }

    public Mobo getMobo() {
        return mobo;
    }

    public void setMobo(Mobo mobo) {
        this.mobo = mobo;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
    }

    public ArrayList<Memory> getMemories() {
        return memories;
    }

    public void setMemories(ArrayList<Memory> memories) {
        this.memories = memories;
    }

    public PcCase getPcCase() {
        return pcCase;
    }

    public void setPcCase(PcCase pcCase) {
        this.pcCase = pcCase;
    }

    public Psu getPsu() {
        return psu;
    }

    public void setPsu(Psu psu) {
        this.psu = psu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addMemory(Memory memory) {
        memories.add(memory);
    }

    public float price(){
        float price=0;
        for(Memory memory:memories){
            price+=memory.getPrice();
        }
        return (float) (mobo.getPrice()+gpu.getPrice()+ cpu.getPrice()+pcCase.getPrice()+ psu.getPrice()+price);

    }

    public boolean verifier() throws BuildException {
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
            case"mini-atx":if(moboFormFactor.equals(("micro"))) throw new BuildException("caseSmallerThanMobo");
            case"micro-atx":if(moboFormFactor.equals(("atx"))) throw new BuildException("caseSmallerThanMobo");
            case"atx":if(moboFormFactor.equals("eatx")) throw new BuildException("caseSmallerThanMobo");
        }
        consumption+=mobo.getConsumption();

        if(psu.getPower()<consumption)
            throw new BuildException("tooMuchPowerConsumption");

        return true;

    }
}



