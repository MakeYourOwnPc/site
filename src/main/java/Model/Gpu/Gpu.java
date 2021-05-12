package Model.Gpu;

public class Gpu {
    private String sn;
    private String name;
    private int consumption;
    private double price;
    private int stock;

    public Gpu(String sn, String name, int consumption, double price, int stock) {
        this.sn = sn;
        this.name = name;
        this.consumption = consumption;
        this.price = price;
        this.stock = stock;
    }

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

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
