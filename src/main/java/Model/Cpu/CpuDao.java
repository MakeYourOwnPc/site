package Model.Cpu;

import Model.ConnPool;
import Model.Gpu.Gpu;

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
                    list.add(cpu);
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
    public ArrayList<Cpu> doRetrieveBySocket(String cpuSocket,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cpus WHERE socket=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,cpuSocket);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
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
                    list.add(cpu);
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
    public Cpu doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cpus WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                Cpu cpu = new Cpu();
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
                return null;
            }
        }
        catch(SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Cpu> doRetrieveByName(String name,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cpus WHERE name CONTAINS ? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
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
                    list.add(cpu);
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
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdate(Cpu cpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Cpus SET name=?,price=?,consumption=?,socket=?,integratedgpu=? WHERE id=?;")){
                Cpu cpuOld = doRetrieveById(cpu.getId());
                if(cpu.getName()!=null)
                ps.setString(1,cpu.getName());
                else
                    ps.setString(1,cpuOld.getName());
                if(cpu.getPrice()!=0)
                ps.setFloat(2,cpu.getPrice());
                else
                    ps.setFloat(2,cpuOld.getPrice());
                if(cpu.getConsumption()!=0)
                ps.setInt(3,cpu.getConsumption());
                else
                    ps.setInt(3,cpuOld.getConsumption());
                if(cpu.getSocket()!=null)
                ps.setString(4,cpu.getSocket());
                else
                    ps.setString(4,cpuOld.getSocket());
                ps.setBoolean(6,cpu.isIntegratedgpu());
                ps.setInt(7, cpu.getId());
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

    @Override
    public boolean doUpdateStock(Cpu cpu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Cpus SET stock=? WHERE id=?;")){
                ps.setInt(1,cpu.getStock());
                ps.setInt(2,cpu.getId());
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
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Cpus WHERE id=?;")){
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
