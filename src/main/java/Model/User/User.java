package Model.User;

public class User {
    private String email;
    private boolean admin;
    private String firstName;
    private String lastName;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){
    }
    
    public User(String email, boolean admin, String firstName, String lastName, String password) {
        this.email = email;
        this.admin = admin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String fullName(){
        return this.firstName +" "+lastName;
    }
}
