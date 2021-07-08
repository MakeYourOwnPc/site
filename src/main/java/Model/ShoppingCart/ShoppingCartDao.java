package Model.ShoppingCart;

import Model.Build.Build;
import Model.Build.BuildDao;
import Model.ConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartDao implements IShoppingCartDao<SQLException>{
    @Override
    public Build doRetrieveByEmail(String email) throws SQLException {
        try(Connection connPool = ConnPool.getConnection()){
            try(PreparedStatement ps = connPool.prepareStatement("SELECT * FROM ShoppingCarts WHERE user=?;")){
                ps.setString(1,email);
                ResultSet rs = ps.executeQuery();
                BuildDao buildDao = new BuildDao();
                return buildDao.doRetrieveById(rs.getInt("idbuild"));
            }catch (SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public boolean doSave(ShoppingCart shoppingCart) throws SQLException{
        try(Connection connPool = ConnPool.getConnection()){
            try(PreparedStatement ps = connPool.prepareStatement("INSERT INTO ShoppingCarts(user,idbuild) VALUES (?,?);")){
                ps.setString(1,shoppingCart.getUser());
                ps.setInt(2,shoppingCart.getIdbuild());
                return ps.executeUpdate()>0;
            }catch (SQLException e){
                return true;
            }
        }catch (SQLException e){
            return true;
        }
    }

    @Override
    public boolean doDelete(String email) throws SQLException{
        try(Connection connPool = ConnPool.getConnection()){
            try(PreparedStatement ps = connPool.prepareStatement("DELETE FROM ShoppingCarts WHERE user=?;")){
                ps.setString(1,email);
                return ps.executeUpdate()>0;
            }catch (SQLException e){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
}
