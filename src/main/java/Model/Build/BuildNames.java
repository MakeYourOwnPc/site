package Model.Build;

import java.util.ArrayList;

public class BuildNames {
    private String mobo;
    private String gpu;

    public String getMobo() {
        return mobo;
    }

    public void setMobo(String mobo) {
        this.mobo = mobo;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public ArrayList<String> getMemories() {
        return memories;
    }

    public void setMemories(ArrayList<String> memories) {
        this.memories = memories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPcCase() {
        return pcCase;
    }

    public void setPcCase(String pcCase) {
        this.pcCase = pcCase;
    }

    public String getPsu() {
        return psu;
    }

    public void setPsu(String psu) {
        this.psu = psu;
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

    private String cpu;
    private ArrayList<String> memories= new ArrayList<>();
    private int id;
    private String pcCase;
    private String psu;

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }
    public void addMemory(String memory){
        memories.add(memory);
    }
    private String maker;
    private boolean suggested;
    private String type;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String imagePath;

}
