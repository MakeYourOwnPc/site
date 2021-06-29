package Model.Memory;

import Model.ConnPool;
import Model.Mobo.Mobo;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class MemoryDao implements IMemoryDao<SQLException>{
    @Override
    public ArrayList<Memory> doRetrieveAll(int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM memories ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ArrayList<Memory> list = new ArrayList<Memory>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Memory memory = new Memory();
                    memory.setConsumption(rs.getInt("consumption"));
                    memory.setAmountMemories(rs.getInt("amountmemories"));
                    memory.setName(rs.getString("name"));
                    memory.setPrice(rs.getFloat("price"));
                    memory.setId(rs.getInt("id"));
                    memory.setStock(rs.getInt("stock"));
                    memory.setmType(rs.getBoolean("mType"));
                    memory.setSocket(rs.getString("socket"));
                    memory.setImagePath(rs.getString("imagepath"));
                    list.add(memory);
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
    public Memory doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM memories WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                Memory memory = new Memory();
                memory.setConsumption(rs.getInt("consumption"));
                memory.setAmountMemories(rs.getInt("amountmemories"));
                memory.setName(rs.getString("name"));
                memory.setPrice(rs.getFloat("price"));
                memory.setId(rs.getInt("id"));
                memory.setStock(rs.getInt("stock"));
                memory.setmType(rs.getBoolean("mType"));
                memory.setSocket(rs.getString("socket"));
                return memory;
            }catch(SQLException e){
                return null;
            }
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ArrayList<Memory> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM memories WHERE name CONTAINS ? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Memory> list = new ArrayList<Memory>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Memory memory = new Memory();
                    memory.setConsumption(rs.getInt("consumption"));
                    memory.setAmountMemories(rs.getInt("amountmemories"));
                    memory.setName(rs.getString("name"));
                    memory.setPrice(rs.getFloat("price"));
                    memory.setId(rs.getInt("id"));
                    memory.setStock(rs.getInt("stock"));
                    memory.setmType(rs.getBoolean("mType"));
                    memory.setSocket(rs.getString("socket"));
                    list.add(memory);
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
    public ArrayList<Memory> doRetrieveBySocket(String socket,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM memories WHERE socket=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,socket);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Memory> list = new ArrayList<Memory>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Memory memory = new Memory();
                    memory.setConsumption(rs.getInt("consumption"));
                    memory.setAmountMemories(rs.getInt("amountmemories"));
                    memory.setName(rs.getString("name"));
                    memory.setPrice(rs.getFloat("price"));
                    memory.setId(rs.getInt("id"));
                    memory.setStock(rs.getInt("stock"));
                    memory.setmType(rs.getBoolean("mType"));
                    memory.setSocket(rs.getString("socket"));
                    list.add(memory);
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
    public ArrayList<Memory> doRetrieveByMType(Boolean mType,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM memories WHERE mType=? ORDER BY name LIMIT ?,?;")){
                ps.setBoolean(1,mType);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ArrayList<Memory> list = new ArrayList<Memory>();
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Memory memory = new Memory();
                    memory.setConsumption(rs.getInt("consumption"));
                    memory.setAmountMemories(rs.getInt("amountmemories"));
                    memory.setName(rs.getString("name"));
                    memory.setPrice(rs.getFloat("price"));
                    memory.setId(rs.getInt("id"));
                    memory.setStock(rs.getInt("stock"));
                    memory.setmType(rs.getBoolean("mType"));
                    memory.setSocket(rs.getString("socket"));
                    list.add(memory);
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
    public ArrayList<Memory> doRetrieveByParameters(String name,String socket, Boolean mType, int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            String query= "SELECT * FROM Memories WHERE ";
                StringBuilder stringBuilder = new StringBuilder();
                if(!name.isBlank()){
                    stringBuilder.append(" name LIKE %"+name+"%");
                }
                if(!socket.isBlank()){
                    if(!stringBuilder.isEmpty())
                        stringBuilder.append(" AND ");
                    stringBuilder.append("socket="+socket);
                }
                if(mType!=null){
                    if(!stringBuilder.isEmpty())
                        stringBuilder.append(" AND ");
                    stringBuilder.append("mtype="+mType);
                }
                stringBuilder.append(" ORDER BY name LIMIT "+offset+","+limit+";");
                ArrayList<Memory> list = new ArrayList<>();
                ResultSet rs = conn.createStatement().executeQuery(query+ stringBuilder);
            while(rs.next()){
                Memory memory = new Memory();
                memory.setConsumption(rs.getInt("consumption"));
                memory.setAmountMemories(rs.getInt("amountmemories"));
                memory.setName(rs.getString("name"));
                memory.setPrice(rs.getFloat("price"));
                memory.setId(rs.getInt("id"));
                memory.setStock(rs.getInt("stock"));
                memory.setmType(rs.getBoolean("mType"));
                memory.setSocket(rs.getString("socket"));
                memory.setImagePath(rs.getString("imagepath"));
                list.add(memory);
            }
            return list;
            }catch(SQLException e){
                return null;
            }
    }
    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Memories WHERE id=?;")){
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
    public boolean doUpdate(Memory memory) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Memories SET name=?,price=?,mType=?,socket=?,amountmemories=?,consumption=?,imagepath=? WHERE id=?;")){
                Memory memoryOld = doRetrieveById(memory.getId());
                if(memory.getName()!=null)
                ps.setString(1,memory.getName());
                else
                ps.setString(1,memoryOld.getName());
                if(memory.getPrice()!=0)
                ps.setFloat(2,memory.getPrice());
                else
                ps.setFloat(2,memoryOld.getPrice());
                ps.setBoolean(4,memory.ismType());
                if(memory.getSocket()!=null)
                ps.setString(5, memory.getSocket());
                else
                ps.setString(5, memoryOld.getSocket());
                if(memory.getAmountMemories()!=0)
                ps.setInt(6,memory.getAmountMemories());
                else
                ps.setInt(6,memoryOld.getAmountMemories());
                if(memory.getConsumption()!=0)
                ps.setInt(7,memory.getConsumption());
                else
                ps.setInt(7,memoryOld.getConsumption());
                if(memory.getImagePath()!=null){
                    File file = new File(memoryOld.getImagePath());
                    file.delete();
                    ps.setString(8,memory.getImagePath());
                }
                else
                    ps.setString(8, memoryOld.getImagePath());
                ps.setInt(9,memory.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdateStock(Memory memory) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Memories SET stock=? WHERE id=?;")){
                ps.setInt(1,memory.getStock());
                ps.setInt(2,memory.getId());
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
    public boolean doSave(Memory memory) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Memories (name,mType,socket,amountmemories,consumption,price,stock) VALUES (?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,memory.getName());
                ps.setBoolean(2,memory.ismType());
                ps.setString(3, memory.getSocket());
                ps.setInt(4,memory.getAmountMemories());
                ps.setInt(5,memory.getConsumption());
                ps.setFloat(6,memory.getPrice());
                ps.setInt(7,memory.getStock());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                memory.setId(rs.getInt(1));
                return true;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }
}
