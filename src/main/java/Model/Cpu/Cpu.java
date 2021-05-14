package Model.Cpu;

public class Cpu {

    private String sn;
    private String name;
    private String socket;
    private double price;
    private int consumption;
    private boolean integratedgpu;
    private int stock;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public boolean isIntegratedgpu() {
        return integratedgpu;
    }

    public void setIntegratedgpu(boolean integratedgpu) {
        this.integratedgpu = integratedgpu;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int available) {
        this.stock = available;
    }
    public Cpu(){

    }
    public Cpu(String sn, String name, String socket, double price, int consumption, boolean integratedgpu, int stock) {
        this.sn = sn;
        this.name = name;
        this.socket = socket;
        this.price = price;
        this.consumption = consumption;
        this.integratedgpu = integratedgpu;
        this.stock = stock;
    }


}
