package Model.Build;

import Model.ConnPool;
import Model.Cpu.Cpu;
import Model.Gpu.Gpu;
import Model.Memory.Memory;
import Model.Mobo.Mobo;

import java.sql.*;
import java.util.ArrayList;

public class BuildDao implements IBuildDao<SQLException>{
    @Override
    public ArrayList<Build> doRetrieveAll(int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public Build doRetrieveById(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Build> doRetrieveByType(String type,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds WHERE type=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,type);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public ArrayList<Build> doRetrieveByPower(int power,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds WHERE power=? ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,power);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public ArrayList<Build> doRetrieveSuggested(boolean suggested,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds WHERE suggested=? ORDER BY name LIMIT ?,?;")){
                ps.setBoolean(1,suggested);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public ArrayList<Build> doRetrieveByMobo(String name,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds WHERE mobo=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public ArrayList<Build> doRetrieveByCpu(String name,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds WHERE cpu LIKE %?% ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public ArrayList<Build> doRetrieveByGpu(String name,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Builds WHERE gpu=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Build> list = new ArrayList<Build>();
                while(rs.next()){
                    Build build = new Build();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getInt("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select * FROM Memoriesbuiltin WHERE idbuild=? ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getInt("id"));
                    }
                    build.setGpu(rs.getInt("gpu"));
                    build.setPsu(rs.getInt("psu"));
                    build.setMobo(rs.getInt("mobo"));
                    build.setPcCase(rs.getInt("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
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
    public ArrayList<BuildNames> doRetrieveByParameters(String mobo,String cpu,String gpu,String psu,String type,Boolean isSuggested,int limit,int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            String query = "SELECT Builds.id id,Motherboards.name mobo,Cpus.name cpu,Gpus.name gpu,Psus.name psu,Builds.type type,Builds.suggested suggested,Builds.maker maker FROM Builds,Gpus,Cpus,Motherboards WHERE Builds.type=? Motherboards.name LIKE %"+mobo+"% AND Gpus.name LIKE %"+gpu+"% AND Cpus.name LIKE %"+cpu+"% AND Psus.Name LIKE %"+psu+"% AND Builds.cpu=Cpus.id AND Builds.gpu=Cpus.id AND Builds.mobo=Motherboards.id AND Builds.psu=Psus.id";
                String s="";
                if(isSuggested!=null){
                    s=" AND suggested="+isSuggested;
                }
                ResultSet rs = conn.createStatement().executeQuery(query+s+"ORDER BY id LIMIT "+offset+","+limit+";");
                ArrayList<BuildNames> list = new ArrayList<>();
                while(rs.next()){
                    BuildNames build = new BuildNames();
                    build.setId(rs.getInt("id"));
                    build.setCpu(rs.getString("cpu"));
                    PreparedStatement ps2 = conn.prepareStatement("Select Memories.name,amountofmemories name FROM Memoriesbuiltin,Memories WHERE idbuild=? AND Memoriesbuiltin.id=Memories.id ORDER BY name;");
                    ps2.setInt(1,build.getId());
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        for(int i = 0; i < rs2.getInt("amountofmemories");i++)
                            build.addMemory(rs2.getString("name"));
                    }
                    build.setGpu(rs.getString("gpu"));
                    build.setPsu(rs.getString("psu"));
                    build.setMobo(rs.getString("mobo"));
                    build.setPcCase(rs.getString("pccase"));
                    build.setType(rs.getString("type"));
                    build.setSuggested(rs.getBoolean("suggested"));
                    build.setMaker(rs.getString("maker"));
                    list.add(build);
                }
                rs.close();
                return list;
            }
            catch(SQLException e){
                return null;
            }
    }
    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Builds WHERE id=?;")){
                ps.setInt(1,id);
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
    public boolean doUpdate(Build build) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Builds SET cpu=?,gpu=?,mobo=?,psu=?,pccase=?,type=?,suggested=? WHERE id=?;")){
                ps.setInt(1,build.getCpu());
                ps.setInt(2,build.getGpu());
                ps.setInt(3,build.getMobo());
                ps.setInt(4,build.getPsu());
                ps.setInt(5,build.getPcCase());
                ps.setString(6,build.getType());
                ps.setBoolean(7, build.isSuggested());
                ps.setInt(8,build.getId());
                PreparedStatement ps2 = conn.prepareStatement("DELETE FROM memoriesbuiltin WHERE buildid=?;");
                ps2.setInt(1,build.getId());
                ps2.executeUpdate();
                ArrayList<Integer> v = build.getMemories();
                for(int i = 0;i < v.size();i++){
                    int amountMemories = 1;
                    for(int j=i;j< v.size();j++)
                        if(v.get(i).equals(v.get(j))) {
                            amountMemories++;
                            v.remove(j);
                        }
                    PreparedStatement ps3 = conn.prepareStatement("INSERT INTO memoriesbuiltin(idbuild,id,amountmemories) VALUES (?,?,?);");
                    ps3.setInt(1,build.getId());
                    ps3.setInt(2,v.get(i));
                    ps3.setInt(3,amountMemories);
                    ps3.executeUpdate();
                }
                return ps.executeUpdate()>0;
            }
            catch(SQLException e) {
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doSave(Build build) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Builds(cpu,gpu,mobo,psu,pccase,type,suggested,maker) VALUES (?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setInt(1,build.getCpu());
                ps.setInt(2,build.getGpu());
                ps.setInt(3,build.getMobo());
                ps.setInt(4,build.getPsu());
                ps.setInt(5,build.getPcCase());
                ps.setString(6,build.getType());
                ps.setBoolean(7, build.isSuggested());
                ps.setString(8,build.getMaker());
                PreparedStatement ps2 = conn.prepareStatement("DELETE FROM memoriesbuiltin WHERE buildid=?;");
                ps2.setInt(1,build.getId());
                ps2.executeUpdate();
                ArrayList<Integer> v = build.getMemories();
                for(int i = 0;i < v.size();i++){
                    int amountMemories = 1;
                    for(int j=i;j< v.size();j++)
                        if(v.get(i).equals(v.get(j))) {
                            amountMemories++;
                            v.remove(j);
                        }
                    PreparedStatement ps3 = conn.prepareStatement("INSERT INTO memoriesbuiltin(idbuild,id,amountmemories) VALUES (?,?,?);");
                    ps3.setInt(1,build.getId());
                    ps3.setInt(2,v.get(i));
                    ps3.setInt(3,amountMemories);
                    ps3.executeUpdate();
                }
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                build.setId(rs.getInt(1));
                return true;
            }
            catch(SQLException e) {
                return false;
            }
        }
        catch(SQLException e){
            return false;
        }
    }
}
