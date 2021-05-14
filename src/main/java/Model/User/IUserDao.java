package Model.User;

import java.util.ArrayList;

public interface IUserDao<E extends Exception>{
    ArrayList<User> doRetrieveAll()throws E;
    User doRetrieveByEmail(String email)throws E;
    boolean doSave(User user)throws E;
    boolean doDelete(User user)throws E;
    boolean doUpdate(User user) throws E;
    boolean doSetAdminByEmail(String email)throws E;
    boolean doRemoveAdminByEmail(String email)throws E;
    boolean doUpdatePassword(User user)throws E;

}
