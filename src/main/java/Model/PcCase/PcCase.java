package Model.PcCase;

public class PcCase {
    private int id;
    private String name;
    private float price;
    private String formFactor;
    private int stock;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String imagePath;

    public PcCase(){
    }
    public PcCase(int id, String name, float price, String formFactor, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.formFactor = formFactor;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
