package Model.Gpu;

import Model.ConnPool;
import Model.User.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class GpuDao implements IGpuDao<SQLException> {
    @Override
    public ArrayList<Gpu> doRetrieveAll(int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Gpu> list = new ArrayList<Gpu>();
                while(rs.next()){
                    Gpu gpu = new Gpu();
                    gpu.setName(rs.getString("name"));
                    gpu.setId(rs.getInt("id"));
                    gpu.setConsumption(rs.getInt("consumption"));
                    gpu.setPrice(rs.getFloat("price"));
                    gpu.setStock(rs.getInt("stock"));
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
    public Gpu doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus WHERE id=?;")) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                ArrayList<Gpu> list = new ArrayList<Gpu>();
                    Gpu gpu = new Gpu();
                    gpu.setName(rs.getString("name"));
                    gpu.setId(rs.getInt("id"));
                    gpu.setConsumption(rs.getInt("consumption"));
                    gpu.setPrice(rs.getFloat("price"));
                    gpu.setStock(rs.getInt("stock"));
                    list.add(gpu);
                rs.close();
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
    public ArrayList<Gpu> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus WHERE name CONTAINS ? ORDER BY name LIMIT ?,?;")) {
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Gpu> list = new ArrayList<Gpu>();
                while (rs.next()) {
                    Gpu gpu = new Gpu();
                    gpu.setName(rs.getString("name"));
                    gpu.setId(rs.getInt("id"));
                    gpu.setConsumption(rs.getInt("consumption"));
                    gpu.setPrice(rs.getFloat("price"));
                    gpu.setStock(rs.getInt("stock"));
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
    public boolean doSave(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Gpus (name,price,consumption,stock) VALUES (?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,gpu.getName());
                ps.setFloat(2,gpu.getPrice());
                ps.setInt(3,gpu.getConsumption());
                ps.setInt(4,gpu.getStock());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                gpu.setId(rs.getInt(1));
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
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Gpus SET name=?, price=?, consumption=?, imagepath=? WHERE id=?;")){
                Gpu gpuOld = doRetrieveById(gpu.getId());
                if(gpu.getName()!=null)
                ps.setString(1,gpu.getName());
                else
                    ps.setString(1,gpuOld.getName());
                if(gpu.getPrice()!=0)
                ps.setFloat(2,gpu.getPrice());
                else
                    ps.setFloat(2,gpuOld.getPrice());
                if(gpu.getConsumption()!=0)
                ps.setInt(3,gpu.getConsumption());
                else
                    ps.setInt(3,gpuOld.getConsumption());
                if(gpu.getImagePath()!=null){
                    File file = new File(gpuOld.getImagePath());
                    file.delete();
                    ps.setString(4, gpu.getImagePath());
                }
                else
                    ps.setString(4,gpuOld.getImagePath());
                ps.setInt(5,gpu.getId());
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
    public boolean doUpdateStock(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Gpus SET stock=? WHERE id=?;")){
                ps.setInt(1,gpu.getStock());
                ps.setInt(2,gpu.getId());
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
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Gpus WHERE id=?;")){
                ps.setInt(1,id);
                File file = new File(doRetrieveById(id).getImagePath());
                file.delete();
                return ps.executeUpdate()>0;
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
