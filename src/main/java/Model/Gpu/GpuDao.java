package Model.Gpu;

import Model.ConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GpuDao implements IGpuDao<SQLException> {
    @Override
    public ArrayList<Gpu> doRetrieveAll() throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus;")){
                ResultSet rs = ps.executeQuery();
                ArrayList<Gpu> list = new ArrayList<Gpu>();
                while(rs.next()){
                    Gpu gpu = new Gpu();
                    gpu.setName(rs.getString("name"));
                    gpu.setSn(rs.getString("sn"));
                    gpu.setConsumption(rs.getInt("consumption"));
                    gpu.setPrice(rs.getFloat("price"));
                    list.add(gpu);
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
    public Gpu doRetrieveBySn(String sn) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus WHERE sn CONTAINS ?;")){
                ps.setString(1,sn);
                ResultSet rs = ps.executeQuery();
                Gpu gpu = new Gpu();
                gpu.setName(rs.getString("name"));
                gpu.setSn(rs.getString("sn"));
                gpu.setConsumption(rs.getInt("consumption"));
                gpu.setPrice(rs.getFloat("price"));
                return gpu;
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
    public Gpu doRetrieveByName(String name) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus WHERE name CONTAINS ?;")){
                ps.setString(1,name);
                ResultSet rs = ps.executeQuery();
                Gpu gpu = new Gpu();
                gpu.setName(rs.getString("name"));
                gpu.setSn(rs.getString("sn"));
                gpu.setConsumption(rs.getInt("consumption"));
                gpu.setPrice(rs.getFloat("price"));
                return gpu;
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
    public boolean doSave(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Gpus (name,sn,price,consumption,stock) VALUES (?,?,?,?,?);")){
                ps.setString(1,gpu.getName());
                ps.setString(2,gpu.getSn());
                ps.setFloat(3,gpu.getPrice());
                ps.setInt(4,gpu.getConsumption());
                ps.setInt(5,gpu.getStock());
                ps.executeQuery();
                return true;
            }
            catch(SQLException e){
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdate(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Gpus SET name=?, price=?, consumption=?, stock=? WHERE sn=?;")){
                ps.setString(1,gpu.getName());
                ps.setFloat(2,gpu.getPrice());
                ps.setInt(3,gpu.getConsumption());
                ps.setInt(4,gpu.getStock());
                ps.setString(5,gpu.getSn());
                ps.executeUpdate();
                return true;
            }
            catch (SQLException e){
                return false;
            }
        }catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean doUpdateStock(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Gpus SET stock=? WHERE sn=?;")){
                ps.setInt(1,gpu.getStock()+1);
                ps.setString(2,gpu.getSn());
                ps.executeUpdate();
                return true;
            }
            catch (SQLException e){
                return false;
            }
        }catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean doDelete(String sn) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Gpus WHERE sn=?;")){
                ps.setString(1,sn);
                ps.executeUpdate();
                return true;
            }
            catch(SQLException e){
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
    }
}
