package Model.Mobo;

import Model.ConnPool;
import Model.Memory.Memory;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class MoboDao implements IMoboDao<SQLException>{
    @Override
    public ArrayList<Mobo> doRetrieveAll(int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    mobo.setImagePath(rs.getString("imagepath"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public Mobo doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE id=?;")){
                ps.setInt(1,id);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                Mobo mobo = new Mobo();
                mobo.setConsumption(rs.getInt("consumption"));
                mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                mobo.setFormFactor(rs.getString("formfactor"));
                mobo.setId(rs.getInt("id"));
                mobo.setPrice(rs.getFloat("price"));
                mobo.setName(rs.getString("name"));
                mobo.setCpuSocket(rs.getString("cpusocket"));
                mobo.setRamSocket(rs.getString("ramsocket"));
                mobo.setStock(rs.getInt("stock"));
                return mobo;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE name CONTAINS ? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByCpuSocket(String socket,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE cpusocket=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,socket);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByRamSocket(String socket,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE ramsocket=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,socket);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByFormFactor(String formFactor,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE formfactor=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,formFactor);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByAmountOfSlotNvme(int slot,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE amountslotnvme>=? ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,slot);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByAmountOfSlotRam(int slot,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE amountslotram>=? ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,slot);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByAmountOfSlotSata(int slot,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE amountslotsata>=? ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,slot);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    list.add(mobo);
                }
                return list;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Mobo> doRetrieveByParameters(String name,String ramSocket, String cpuSocket, String formFactor, int nvmeSlot, int sataSlot,int ramSlot,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Motherboards WHERE UPPER(name) LIKE UPPER('%"+name+"%') AND UPPER(ramsocket) LIKE UPPER('%"+ramSocket+"%') AND UPPER(cpusocket) LIKE UPPER('%"+cpuSocket+"%') AND UPPER(formfactor) LIKE UPPER('"+formFactor+"%') AND amountslotnvme>=? AND amountslotsata>=? AND amountslotram>=? ORDER BY name LIMIT ?,?;")) {
                ps.setInt(1, nvmeSlot);
                ps.setInt(2, sataSlot);
                ps.setInt(3, ramSlot);
                ps.setInt(4, offset);
                ps.setInt(5, limit);
                ArrayList<Mobo> list = new ArrayList<Mobo>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Mobo mobo = new Mobo();
                    mobo.setConsumption(rs.getInt("consumption"));
                    mobo.setAmountSlotNvme(rs.getInt("amountslotnvme"));
                    mobo.setAmountSlotRam(rs.getInt("amountslotram"));
                    mobo.setAmountSlotSata(rs.getInt("amountslotsata"));
                    mobo.setFormFactor(rs.getString("formfactor"));
                    mobo.setId(rs.getInt("id"));
                    mobo.setPrice(rs.getFloat("price"));
                    mobo.setName(rs.getString("name"));
                    mobo.setCpuSocket(rs.getString("cpusocket"));
                    mobo.setRamSocket(rs.getString("ramsocket"));
                    mobo.setStock(rs.getInt("stock"));
                    mobo.setImagePath(rs.getString("imagepath"));
                    list.add(mobo);
                }
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
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Motherboards WHERE id=?;")){
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
    public boolean doUpdate(Mobo mobo) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Memories SET name=?,formfactor=?,amountslotnvme=?,amountslotsata=?,amountslotram=?,cpusocket=?,ramsocket=?,consumption=?,price=?,socket=?,imagepath=? WHERE id=?;")){
                Mobo moboOld = doRetrieveById(mobo.getId());
                if(mobo.getName()!=null)
                ps.setString(1,mobo.getName());
                else
                    ps.setString(1,moboOld.getName());
                if(mobo.getFormFactor()!=null)
                ps.setString(2,mobo.getFormFactor());
                else
                ps.setString(2,moboOld.getFormFactor());
                if(mobo.getAmountSlotNvme()!=0)
                ps.setInt(3,mobo.getAmountSlotNvme());
                else
                ps.setInt(3,moboOld.getAmountSlotNvme());
                if(mobo.getAmountSlotSata()!=0)
                ps.setInt(4,mobo.getAmountSlotSata());
                else
                    ps.setInt(4,moboOld.getAmountSlotSata());
                if(mobo.getAmountSlotRam()!=0)
                ps.setInt(5,mobo.getAmountSlotRam());
                else
                ps.setInt(5,moboOld.getAmountSlotRam());
                if(mobo.getCpuSocket()!=null)
                ps.setString(6,mobo.getCpuSocket());
                else
                ps.setString(6,moboOld.getCpuSocket());
                if(mobo.getRamSocket()!=null)
                ps.setString(7,mobo.getRamSocket());
                else
                    ps.setString(7,moboOld.getRamSocket());
                if(mobo.getConsumption()!=0)
                ps.setInt(8,mobo.getConsumption());
                else
                    ps.setInt(8,moboOld.getConsumption());
                if(mobo.getPrice()!=0)
                ps.setFloat(9,mobo.getPrice());
                else
                    ps.setFloat(9,moboOld.getPrice());
                if(mobo.getImagePath()!=null){
                    File file = new File(moboOld.getImagePath());
                    file.delete();
                    ps.setString(10,mobo.getImagePath());
                }
                else
                    ps.setString(10,moboOld.getImagePath());
                ps.setInt(11,mobo.getId());
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
    public boolean doUpdateStock(Mobo mobo) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Motherboards SET stock=? WHERE sn=?;")){
                ps.setInt(1,mobo.getStock());
                ps.setInt(2,mobo.getId());
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
    public boolean doSave(Mobo mobo) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Motherboards (name,formfactor,amountslotnvme,amountslotram,cpusocket,ramsocket,consumption,price,stock) VALUES(?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,mobo.getName());
                ps.setString(2,mobo.getFormFactor());
                ps.setInt(3,mobo.getAmountSlotNvme());
                ps.setInt(4,mobo.getAmountSlotSata());
                ps.setInt(5,mobo.getAmountSlotRam());
                ps.setString(6,mobo.getCpuSocket());
                ps.setString(7,mobo.getRamSocket());
                ps.setInt(8,mobo.getConsumption());
                ps.setFloat(9,mobo.getPrice());
                ps.setInt(10,mobo.getStock());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                mobo.setId(rs.getInt(1));
                return true;
            }
            catch (SQLException e){
                return false;
            }
        }catch (SQLException e) {
            return false;
        }
    }
}
