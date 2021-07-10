package Model.ShoppingCart;

import Model.Build.Build;
import Model.Build.BuildNames;

import java.sql.SQLException;

public interface IShoppingCartDao <E extends Exception>{
    int doRetrieveByEmail(String email) throws E;
    boolean doSave(ShoppingCart shoppingCart) throws E;
    boolean doDelete(String email) throws E;
}
