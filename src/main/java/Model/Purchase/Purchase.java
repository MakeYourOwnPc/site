package Model.Purchase;

import Model.Build.Build;
import Model.Country.Country;

import java.time.LocalDate;

public class Purchase {
    private int id;
    private Build build;
    private LocalDate creationDate;
    private String cellphonenumber;
    private float price ;
    private String address;
    private String city;
    private String cap;
    private String email;
    private Country country;

    public Purchase() {
    }

    public Purchase(int id, Build build, LocalDate creationDate, String cellphonenumber, float price, String address, String city, String cap, String email, Country country) {
        this.id = id;
        this.build = build;
        this.creationDate = creationDate;
        this.cellphonenumber = cellphonenumber;
        this.price = price;
        this.address = address;
        this.city = city;
        this.cap = cap;
        this.email = email;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCellphonenumber() {
        return cellphonenumber;
    }

    public void setCellphonenumber(String cellphonenumber) {
        this.cellphonenumber = cellphonenumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
