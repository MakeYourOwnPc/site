package Model.User;

import java.util.ArrayList;

public interface UserDao {
    ArrayList<User> doRetrieveAll();
    User doRetrieveByEmail(String email);
    boolean doSaveUser(User user);
    boolean deleteUserByEmail(String email);
}
