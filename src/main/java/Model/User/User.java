package Model.User;

public class User {
    private String email;
    private boolean admin;
    private String FirstName;
    private String LastName;
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
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String email, boolean admin, String firstName, String lastName, String password) {
        this.email = email;
        this.admin = admin;
        FirstName = firstName;
        LastName = lastName;
        this.password = password;
    }
}
