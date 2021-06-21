package Model.Mobo;

public class Mobo {
    private int id;
    private String name;
    private int amountSlotNvme;
    private int amountSlotSata ;
    private int amountSlotRam ;
    private String cpuSocket ;
    private String ramSocket ;
    private String formFactor;
    private int consumption;
    private float price;
    private int stock;


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String imagePath;

    public Mobo() {
    }

    public Mobo(int id, String name, int amountSlotNvme, int amountSlotSata, int amountSlotRam, String cpuSocket, String ramSocket, String formFactor, int consumption, float price, int stock) {
        this.id = id;
        this.name = name;
        this.amountSlotNvme = amountSlotNvme;
        this.amountSlotSata = amountSlotSata;
        this.amountSlotRam = amountSlotRam;
        this.cpuSocket = cpuSocket;
        this.ramSocket = ramSocket;
        this.formFactor = formFactor;
        this.consumption = consumption;
        this.price = price;
        this.stock = stock;
    }

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

    public int getAmountSlotNvme() {
        return amountSlotNvme;
    }

    public void setAmountSlotNvme(int amountSlotNvme) {
        this.amountSlotNvme = amountSlotNvme;
    }

    public int getAmountSlotSata() {
        return amountSlotSata;
    }

    public void setAmountSlotSata(int amountSlotSata) {
        this.amountSlotSata = amountSlotSata;
    }

    public int getAmountSlotRam() {
        return amountSlotRam;
    }

    public void setAmountSlotRam(int amountSlotRam) {
        this.amountSlotRam = amountSlotRam;
    }

    public String getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(String cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    public String getRamSocket() {
        return ramSocket;
    }

    public void setRamSocket(String ramSocket) {
        this.ramSocket = ramSocket;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
