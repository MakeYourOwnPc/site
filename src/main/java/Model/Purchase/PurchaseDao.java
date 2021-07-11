package Model.Purchase;


import Model.ConnPool;
import Model.Cpu.Cpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Psu.Psu;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseDao implements IPurchaseDao<SQLException>{
    @Override
    public ArrayList<Purchase> doRetrieveAll(int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Purchases LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Purchase> list = new ArrayList<>();
                while(rs.next()){
                    Purchase purchase = new Purchase();
                    purchase.setId(rs.getInt("id"));
                    purchase.setIdBuild(rs.getInt("idbuild"));
                    purchase.setCreationDate(rs.getDate("creationdate").toLocalDate());
                    purchase.setCellphonenumber(rs.getString("cellphone"));
                    purchase.setCountry(rs.getString("countryid"));
                    purchase.setPrice(rs.getFloat("price"));
                    purchase.setAddress(rs.getString("address"));
                    purchase.setCap(rs.getString("cap"));
                    purchase.setCity(rs.getString("city"));
                    purchase.setEmail(rs.getString("email"));
                    list.add(purchase);
                }
                rs.close();
                return list;
            }
            catch(SQLException e){
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public Purchase doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Purchases WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                    Purchase purchase = new Purchase();
                    purchase.setId(rs.getInt("id"));
                    purchase.setIdBuild(rs.getInt("idbuild"));
                    purchase.setCreationDate(rs.getDate("creationdate").toLocalDate());
                    purchase.setCellphonenumber(rs.getString("cellphone"));
                    purchase.setCountry(rs.getString("countryid"));
                    purchase.setPrice(rs.getFloat("price"));
                    purchase.setAddress(rs.getString("address"));
                    purchase.setCap(rs.getString("cap"));
                    purchase.setCity(rs.getString("city"));
                    purchase.setEmail(rs.getString("email"));
                rs.close();
                return purchase;
            }
            catch(SQLException e){
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }
    @Override
    public ArrayList<Purchase> doRetrieveByParameters(String email, Date startingDate, Date endingDate,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Purchases WHERE email LIKE LOWER('%"+email+"%')? AND creationdate BETWEEN ? AND ? LIMIT ?,?;")){
               ps.setDate(1,startingDate);
               ps.setDate(2,endingDate);
                ps.setInt(3,offset);
                ps.setInt(4,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Purchase> list = new ArrayList<>();
                while(rs.next()){
                    Purchase purchase = new Purchase();
                    purchase.setId(rs.getInt("id"));
                    purchase.setIdBuild(rs.getInt("idbuild"));
                    purchase.setCreationDate(rs.getDate("creationdate").toLocalDate());
                    purchase.setCellphonenumber(rs.getString("cellphone"));
                    purchase.setCountry(rs.getString("countryid"));
                    purchase.setPrice(rs.getFloat("price"));
                    purchase.setAddress(rs.getString("address"));
                    purchase.setCap(rs.getString("cap"));
                    purchase.setCity(rs.getString("city"));
                    purchase.setEmail(rs.getString("email"));
                    list.add(purchase);
                }
                rs.close();
                return list;
            }
            catch(SQLException e){
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }
    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Purchases WHERE id=?;")){
                ps.setInt(1,id);
                return ps.executeUpdate()>0;
            }
            catch(SQLException e){
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public boolean doSave(Purchase purchase) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Purchases (idbuild,creationdate,cellphone,countryid,price,address,cap,city,email) VALUES (?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){
                ps.setInt(1,purchase.getIdBuild());
                ps.setDate(2, Date.valueOf(purchase.getCreationDate()));
                ps.setString(3, purchase.getCellphonenumber());
                ps.setString(4, purchase.getCountry());
                ps.setFloat(5,purchase.getPrice());
                ps.setString(6, purchase.getAddress());
                ps.setString(7, purchase.getCap());
                ps.setString(8, purchase.getCity());
                ps.setString(9, purchase.getEmail());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                purchase.setId(rs.getInt(1));
                return true;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }


    @Override
    public ArrayList<Purchase> doRetrieveByEmail(String email,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Purchases WHERE email=? LIMIT ?,?;")){
                ps.setString(1,email);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Purchase> list = new ArrayList<>();
                while(rs.next()){
                    Purchase purchase = new Purchase();
                    purchase.setId(rs.getInt("id"));
                    purchase.setIdBuild(rs.getInt("idbuild"));
                    purchase.setCreationDate(rs.getDate("creationdate").toLocalDate());
                    purchase.setCellphonenumber(rs.getString("cellphone"));
                    purchase.setCountry(rs.getString("countryid"));
                    purchase.setPrice(rs.getFloat("price"));
                    purchase.setAddress(rs.getString("address"));
                    purchase.setCap(rs.getString("cap"));
                    purchase.setCity(rs.getString("city"));
                    purchase.setEmail(rs.getString("email"));
                    list.add(purchase);
                }
                rs.close();
                return list;
            }
            catch(SQLException e){
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }
}
