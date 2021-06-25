package Model.ShoppingCart;

import Model.Build.Build;

import java.sql.SQLException;

public interface IShoppingCartDao <E extends Exception>{
    Build doRetrieveByEmail(String email) throws E;
    boolean doSave(ShoppingCart shoppingCart) throws E;
    boolean doDelete(String email) throws E;
}
