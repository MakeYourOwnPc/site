package Model.Memory;

public class Memory {
    private int id;
    private String name;
    private String socket ;
    private boolean mType;
    private int amountMemories;
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

    public Memory() {
    }

    public Memory(int id, String name, String socket, boolean mType, int amountMemories, int consumption, float price, int stock) {
        this.id = id;
        this.name = name;
        this.socket = socket;
        this.mType = mType; //false= ram true=massStorage
        this.amountMemories = amountMemories;
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

    public void setName(String name) { this.name = name; }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public boolean ismType() {
        return mType;
    }

    public void setmType(boolean mType) {
        this.mType = mType;
    }

    public int getAmountMemories() {
        return amountMemories;
    }

    public void setAmountMemories(int amountMemories) {
        this.amountMemories = amountMemories;
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
