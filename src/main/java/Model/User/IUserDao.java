package Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUserDao<E extends Exception>{
    ArrayList<User> doRetrieveAll()throws E;
    User doRetrieveByEmail(String email)throws E;
    boolean doSave(User user)throws E;
    boolean doDelete(String string)throws E;
    boolean doUpdate(User user) throws E;
    boolean doChangeAdmin(String email,Boolean admin) throws SQLException;
}
