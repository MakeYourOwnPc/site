package Model.PcCase;

public class PcCase {
    private String sn;
    private String name;
    private double price;
    private String formFactor;
    private int stock;


    public PcCase(String sn, String name, double price, String formFactor, int stock) {
        this.sn = sn;
        this.name = name;
        this.price = price;
        this.formFactor = formFactor;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
