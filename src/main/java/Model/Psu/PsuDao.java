package Model.Psu;

import Model.ConnPool;
import Model.Cpu.Cpu;
import Model.Gpu.Gpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;
import Model.Purchase.IPurchaseDao;
import Model.Purchase.Purchase;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class PsuDao implements IPsuDao <SQLException> {
    @Override
    public ArrayList<Psu> doRetrieveAll(int limit, int offset) throws SQLException {
            try(Connection conn = ConnPool.getConnection()){
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus ORDER BY name LIMIT ?,?;")){
                    ps.setInt(1,offset);
                    ps.setInt(2,limit);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Psu> list = new ArrayList<Psu>();
                    while(rs.next()){
                        Psu psu = new Psu();
                        psu.setName(rs.getString("name"));
                        psu.setId(rs.getInt("id"));
                        psu.setPower(rs.getInt("power"));
                        psu.setPrice(rs.getFloat("price"));
                        psu.setStock(rs.getInt("stock"));
                        psu.setImagePath(rs.getString("imagepath"));
                        list.add(psu);
                    }
                    rs.close();
                    return list;
                }
                catch(SQLException e){
                    return null;
                }
            }
            catch(SQLException e){
                return null;
            }
    }

    @Override
    public Psu doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                    Psu psu = new Psu();
                    psu.setName(rs.getString("name"));
                    psu.setId(rs.getInt("id"));
                    psu.setPower(rs.getInt("power"));
                    psu.setPrice(rs.getFloat("price"));
                    psu.setStock(rs.getInt("stock"));
                psu.setImagePath(rs.getString("imagepath"));
                    return psu;
            }
            catch(SQLException e){
                return null;
            }
        }
        catch(SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Psu> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE name CONTAINS ? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Psu> list = new ArrayList<Psu>();
                while(rs.next()){
                    Psu psu = new Psu();
                    psu.setName(rs.getString("name"));
                    psu.setId(rs.getInt("id"));
                    psu.setPower(rs.getInt("power"));
                    psu.setPrice(rs.getFloat("price"));
                    psu.setStock(rs.getInt("stock"));
                    psu.setImagePath(rs.getString("imagepath"));
                    list.add(psu);
                }
                rs.close();
                return list;
            }
            catch(SQLException e){
                return null;
            }
        }
        catch(SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Psu> doRetrieveByPower(int power,int limit, int offset) throws SQLException {
            try(Connection conn = ConnPool.getConnection()){
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE power=? ORDER BY name LIMIT ?,?;")){
                    ps.setInt(1,power);
                    ps.setInt(2,offset);
                    ps.setInt(3,limit);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Psu> list = new ArrayList<Psu>();
                    while(rs.next()){
                        Psu psu = new Psu();
                        psu.setName(rs.getString("name"));
                        psu.setId(rs.getInt("id"));
                        psu.setPower(rs.getInt("power"));
                        psu.setPrice(rs.getFloat("price"));
                        psu.setStock(rs.getInt("stock"));
                        psu.setImagePath(rs.getString("imagepath"));
                        list.add(psu);
                    }
                    rs.close();
                    return list;
                }
                catch(SQLException e){
                    return null;
                }
            }
            catch(SQLException e){
                return null;
            }
    }
    @Override
    public ArrayList<Psu> doRetrieveByParameters(String name,int power,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE name LIKE %?% AND power>=? ORDER BY name LIMIT ?,?;")) {
                ps.setString(1, name);
                ps.setInt(2, power);
                ps.setInt(3, offset);
                ps.setInt(4, limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Psu> list = new ArrayList<Psu>();
                while (rs.next()) {
                    Psu psu = new Psu();
                    psu.setName(rs.getString("name"));
                    psu.setId(rs.getInt("id"));
                    psu.setPower(rs.getInt("power"));
                    psu.setPrice(rs.getFloat("price"));
                    psu.setStock(rs.getInt("stock"));
                    psu.setImagePath(rs.getString("imagepath"));
                    list.add(psu);
                }
                rs.close();
                return list;
            } catch (SQLException e) {
                return null;
            }
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Psus WHERE id=?;")){
                ps.setInt(1,id);
                File file = new File(doRetrieveById(id).getImagePath());
                file.delete();
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdate(Psu psu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Psus set name=?,power=?,price=?,imagepath=? WHERE id=?;")){
                Psu psuOld = doRetrieveById(psu.getId());
                if(psu.getName()!=null)
                    ps.setString(1, psu.getName());
                else
                    ps.setString(1,psuOld.getName());
                if(psu.getPower()!=0)
                ps.setInt(2,psu.getPower());
                else
                    ps.setInt(2,psuOld.getPower());
                if(psu.getPrice()!=0)
                    ps.setFloat(3,psu.getPrice());
                else
                    ps.setFloat(3,psuOld.getPrice());
                if(psu.getImagePath()!=null){
                    File file = new File(psuOld.getImagePath());
                    file.delete();
                    ps.setString(4,psu.getImagePath());
                }
                else
                    ps.setString(4,psuOld.getImagePath());
                ps.setInt(5, psu.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdateStock(Psu psu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Psus SET stock=? WHERE id=?;")){
                ps.setInt(1,psu.getStock());
                ps.setInt(2, psu.getId());
                return ps.executeUpdate()>0;
            }
            catch (SQLException e){
                return false;
            }
        }catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean doSave(Psu psu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Psus (name,power,price,stock,imagepath) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,psu.getName());
                ps.setInt(2,psu.getPower());
                ps.setFloat(3,psu.getPrice());
                ps.setInt(4, psu.getStock());
                ps.setString(5,psu.getImagePath());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                psu.setId(rs.getInt(1));
                return true;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }


}
