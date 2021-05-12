package Model.Memory;

public class Memory {
    private String sn;
    private String name;
    private String Socket ;
    private boolean mType;
    private int amountMemories;
    private int consumption;
    private float price;
    private int stock;

    public Memory() {
    }

    public Memory(String sn, String name, String socket, boolean mType, int amountMemories, int consumption, float price, int stock) {
        this.sn = sn;
        this.name = name;
        Socket = socket;
        this.mType = mType;
        this.amountMemories = amountMemories;
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

    public String getSocket() {
        return Socket;
    }

    public void setSocket(String socket) {
        Socket = socket;
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
