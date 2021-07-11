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
                throw new SQLException();
            }
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public Memory doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Memories WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                Memory memory = new Memory();
                rs.next();
                memory.setConsumption(rs.getInt("consumption"));
                memory.setAmountMemories(rs.getInt("amountmemories"));
                memory.setName(rs.getString("name"));
                memory.setPrice(rs.getFloat("price"));
                memory.setId(rs.getInt("id"));
                memory.setStock(rs.getInt("stock"));
                memory.setmType(rs.getBoolean("mtype"));
                memory.setSocket(rs.getString("socket"));
                memory.setImagePath(rs.getString("imagepath"));
                return memory;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public ArrayList<Memory> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM memories WHERE UPPER(name) LIKE UPPER('%"+name+"%') ORDER BY name LIMIT ?,?;")){
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
                throw new SQLException();
            }
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public ArrayList<Memory> doRetrieveByParameters(String name,String socket, Boolean mType, int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            String query= "SELECT * FROM Memories WHERE ";
                StringBuilder stringBuilder = new StringBuilder();
                if(!name.isBlank()){
                    stringBuilder.append(" UPPER(name) LIKE UPPER('%"+name+"%')");
                }
                if(!socket.isBlank()){
                    if(!stringBuilder.isEmpty())
                        stringBuilder.append(" AND ");
                    stringBuilder.append("UPPER(socket) LIKE UPPER('%"+socket+"%')");
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
            throw new SQLException();
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
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Memories SET name=?,price=?,mType=?,socket=?,amountmemories=?,consumption=?,imagepath=?,stock=? WHERE id=?;")){
                ps.setString(1,memory.getName());
                ps.setFloat(2,memory.getPrice());
                ps.setBoolean(3,memory.ismType());
                ps.setString(4, memory.getSocket());
                ps.setInt(5,memory.getAmountMemories());
                ps.setInt(6,memory.getConsumption());
                ps.setString(7,memory.getImagePath());
                ps.setInt(8,memory.getStock());
                ps.setInt(9,memory.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
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
                throw new SQLException();
            }
        }catch (SQLException e) {
            throw new SQLException();
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
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }
}
