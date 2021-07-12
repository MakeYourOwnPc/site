package Model.Cpu;

import Controller.ImagePaths;
import Model.ConnPool;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class CpuDao implements ICpuDao<SQLException> {
    @Override
    public ArrayList<Cpu> doRetrieveAll(int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cpus ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Cpu> list = new ArrayList<Cpu>();
                while(rs.next()){
                    Cpu cpu = new Cpu();
                    cpu.setName(rs.getString("name"));
                    cpu.setId(rs.getInt("id"));
                    cpu.setIntegratedgpu(rs.getBoolean("integratedgpu"));
                    cpu.setSocket(rs.getString("socket"));
                    cpu.setConsumption(rs.getInt("consumption"));
                    cpu.setPrice(rs.getFloat("price"));
                    cpu.setStock(rs.getInt("stock"));
                    cpu.setImagePath(rs.getString("imagepath"));
                    list.add(cpu);
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
    public Cpu doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cpus WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                Cpu cpu = new Cpu();
                rs.next();
                cpu.setName(rs.getString("name"));
                cpu.setId(rs.getInt("id"));
                cpu.setIntegratedgpu(rs.getBoolean("integratedgpu"));
                cpu.setSocket(rs.getString("socket"));
                cpu.setConsumption(rs.getInt("consumption"));
                cpu.setPrice(rs.getFloat("price"));cpu.setStock(rs.getInt("stock"));
                rs.close();
                return cpu;
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
    public ArrayList<Cpu> doRetrieveByName(String name,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cpus WHERE UPPER(name) LIKE UPPER('%"+name+"%') ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Cpu> list = new ArrayList<Cpu>();
                while(rs.next()){
                    Cpu cpu = new Cpu();
                    cpu.setName(rs.getString("name"));
                    cpu.setId(rs.getInt("id"));
                    cpu.setIntegratedgpu(rs.getBoolean("integratedgpu"));
                    cpu.setSocket(rs.getString("socket"));
                    cpu.setConsumption(rs.getInt("consumption"));
                    cpu.setPrice(rs.getFloat("price"));
                    cpu.setStock(rs.getInt("stock"));
                    cpu.setImagePath(rs.getString("imagepath"));
                    list.add(cpu);
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
    public ArrayList<Cpu> doRetrieveByParameters(String name,String socket,Boolean integratedGpu,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            String query= "SELECT * FROM Cpus WHERE ";
            StringBuilder stringBuilder = new StringBuilder();
            if(!name.isBlank()){
                stringBuilder.append(" UPPER(name) LIKE UPPER('%"+name+"%')");
            }
            if(!socket.isBlank()){
                if(!stringBuilder.isEmpty())
                    stringBuilder.append(" AND ");
                stringBuilder.append("UPPER(socket) LIKE UPPER('%"+socket+"%')");
            }
            if(integratedGpu!=null){
                if(!stringBuilder.isEmpty())
                    stringBuilder.append(" AND ");
                stringBuilder.append("integratedgpu="+integratedGpu);
            }
            stringBuilder.append(" ORDER BY name LIMIT "+offset+","+limit+";");
            ArrayList<Cpu> list = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery(query+ stringBuilder);
                while(rs.next()){
                    Cpu cpu = new Cpu();
                    cpu.setName(rs.getString("name"));
                    cpu.setId(rs.getInt("id"));
                    cpu.setIntegratedgpu(rs.getBoolean("integratedgpu"));
                    cpu.setSocket(rs.getString("socket"));
                    cpu.setConsumption(rs.getInt("consumption"));
                    cpu.setPrice(rs.getFloat("price"));
                    cpu.setStock(rs.getInt("stock"));
                    cpu.setImagePath(rs.getString("imagepath"));
                    list.add(cpu);
                }
                rs.close();
                return list;
            }
            catch(SQLException e){
                throw new SQLException();
            }
    }
    @Override
    public boolean doSave(Cpu cpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Cpus (name,price,consumption,stock,socket,integratedgpu) VALUES (?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,cpu.getName());
                ps.setFloat(2,cpu.getPrice());
                ps.setInt(3,cpu.getConsumption());
                ps.setInt(4,cpu.getStock());
                ps.setString(5,cpu.getSocket());
                ps.setBoolean(6,cpu.isIntegratedgpu());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                cpu.setId(rs.getInt(1));

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
    public boolean doUpdate(Cpu cpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Cpus SET name=?,price=?,consumption=?,socket=?,integratedgpu=?,imagepath=?,stock=? WHERE id=?;")){
                ps.setString(1,cpu.getName());
                ps.setFloat(2,cpu.getPrice());
                ps.setInt(3,cpu.getConsumption());
                ps.setString(4,cpu.getSocket());
                ps.setBoolean(6,cpu.isIntegratedgpu());
                ps.setString(7, cpu.getImagePath());
                ps.setInt(8,cpu.getStock());
                ps.setInt(9, cpu.getId());
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
    public boolean doUpdateStock(Cpu cpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Cpus SET stock=? WHERE id=?;")){
                ps.setInt(1,cpu.getStock());
                ps.setInt(2,cpu.getId());
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
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Cpus WHERE id=?;")){
                ps.setInt(1,id);
                ImagePaths.checkFile(doRetrieveById(id).getImagePath());
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
