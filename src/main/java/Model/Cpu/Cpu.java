package Model.Cpu;

public class Cpu {

    private int id;
    private String name;
    private String socket;
    private float price;
    private int consumption;
    private boolean integratedgpu;
    private int stock;


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String imagePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
    public Cpu(int id, String name, String socket, float price, int consumption, boolean integratedgpu, int stock) {
        this.id = id;
        this.name = name;
        this.socket = socket;
        this.price = price;
        this.consumption = consumption;
        this.integratedgpu = integratedgpu;
        this.stock = stock;
    }


}
