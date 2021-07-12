package Model.Gpu;

import Controller.ImagePaths;
import Model.ConnPool;

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
                    gpu.setImagePath(rs.getString("imagepath"));
                    list.add(gpu);
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
    public Gpu doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus WHERE id=?;")) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                rs.next();
                    Gpu gpu = new Gpu();
                    gpu.setName(rs.getString("name"));
                    gpu.setId(rs.getInt("id"));
                    gpu.setConsumption(rs.getInt("consumption"));
                    gpu.setPrice(rs.getFloat("price"));
                    gpu.setStock(rs.getInt("stock"));
                    gpu.setImagePath(rs.getString("imagepath"));
                rs.close();
                return gpu;
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
    public ArrayList<Gpu> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gpus WHERE UPPER(name) LIKE UPPER('%"+name+"%') ORDER BY name LIMIT ?,?;")) {
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Gpu> list = new ArrayList<Gpu>();
                while (rs.next()) {
                    Gpu gpu = new Gpu();
                    gpu.setName(rs.getString("name"));
                    gpu.setId(rs.getInt("id"));
                    gpu.setConsumption(rs.getInt("consumption"));
                    gpu.setPrice(rs.getFloat("price"));
                    gpu.setStock(rs.getInt("stock"));
                    gpu.setImagePath(rs.getString("imagepath"));
                    list.add(gpu);
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
    public boolean doSave(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Gpus (name,price,consumption,imagepath,stock) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,gpu.getName());
                ps.setFloat(2,gpu.getPrice());
                ps.setInt(3,gpu.getConsumption());
                ps.setString(4,gpu.getImagePath());
                ps.setInt(5,gpu.getStock());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                gpu.setId(rs.getInt(1));
                return true;
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
    public boolean doUpdate(Gpu gpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Gpus SET name=?, price=?, consumption=?, imagepath=?,stock=? WHERE id=?;")){
                ps.setString(1,gpu.getName());
                ps.setFloat(2,gpu.getPrice());
                ps.setInt(3,gpu.getConsumption());
                ps.setString(4, gpu.getImagePath());
                ps.setInt(5,gpu.getStock());
                ps.setInt(6,gpu.getId());
                return ps.executeUpdate()>0;
            }
            catch (SQLException e){
                throw new SQLException();
            }
        }catch (SQLException e) {
            throw new SQLException();
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
                throw new SQLException();
            }
        }catch (SQLException e) {
            throw new SQLException();
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Gpus WHERE id=?;")){
                ps.setInt(1,id);
                String filePath = doRetrieveById(id).getImagePath();
                String path = filePath.substring(filePath.lastIndexOf(File.separator)+1);
                File file = new File(ImagePaths.uploadPath+path);
                System.out.println(ImagePaths.uploadPath+path);
                file.delete();
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
}
