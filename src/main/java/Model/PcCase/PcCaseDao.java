package Model.PcCase;

import Model.ConnPool;
import Model.Gpu.Gpu;
import Model.Psu.Psu;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class PcCaseDao implements IPcCaseDao<SQLException>{
    @Override
    public ArrayList<PcCase> doRetrieveAll(int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<PcCase> list = new ArrayList<PcCase>();
                while(rs.next()){
                    PcCase pcCase = new PcCase();
                    pcCase.setName(rs.getString("name"));
                    pcCase.setId(rs.getInt("id"));
                    pcCase.setFormFactor(rs.getString("formfactor"));
                    pcCase.setPrice(rs.getFloat("price"));
                    pcCase.setStock(rs.getInt("stock"));
                    pcCase.setImagePath(rs.getString("imagepath"));
                    list.add(pcCase);
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
    public ArrayList<PcCase> doRetrieveByFormFactor(String formFactor,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases WHERE formfactor=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,formFactor);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<PcCase> list = new ArrayList<PcCase>();
                while(rs.next()){
                    PcCase pcCase = new PcCase();
                    pcCase.setName(rs.getString("name"));
                    pcCase.setId(rs.getInt("id"));
                    pcCase.setFormFactor(rs.getString("formfactor"));
                    pcCase.setPrice(rs.getFloat("price"));
                    pcCase.setStock(rs.getInt("stock"));
                    pcCase.setImagePath(rs.getString("imagepath"));
                    list.add(pcCase);
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
    public PcCase doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                    PcCase pcCase = new PcCase();
                    pcCase.setName(rs.getString("name"));
                    pcCase.setId(rs.getInt("id"));
                    pcCase.setFormFactor(rs.getString("formfactor"));
                    pcCase.setPrice(rs.getFloat("price"));
                    pcCase.setStock(rs.getInt("stock"));
                pcCase.setImagePath(rs.getString("imagepath"));
                rs.close();
                return pcCase;
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
    public ArrayList<PcCase> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases WHERE name=? ORDER BY name LIMIT ?,?;")){
                ps.setString(1,name);
                ps.setInt(2,offset);
                ps.setInt(3,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<PcCase> list = new ArrayList<PcCase>();
                while(rs.next()){
                    PcCase pcCase = new PcCase();
                    pcCase.setName(rs.getString("name"));
                    pcCase.setId(rs.getInt("id"));
                    pcCase.setFormFactor(rs.getString("formfactor"));
                    pcCase.setPrice(rs.getFloat("price"));
                    pcCase.setStock(rs.getInt("stock"));
                    pcCase.setImagePath(rs.getString("imagepath"));
                    list.add(pcCase);
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
    public boolean doSave(PcCase pcCase) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Pccases (name,formfactor,price,stock,imagepath) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,pcCase.getName());
                ps.setString(2, pcCase.getFormFactor());
                ps.setFloat(3,pcCase.getPrice());
                ps.setString(4, pcCase.getImagePath());
                ps.setInt(5,pcCase.getStock());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                pcCase.setId(rs.getInt(1));
                return true;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdate(PcCase pcCase) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Pccases SET name=?,formfactor=?,price=?,imagepath=? WHERE id=?;")){
                PcCase pcCaseOld = doRetrieveById(pcCase.getId());
                if(pcCase.getName()!=null)
                    ps.setString(1, pcCase.getName());
                else
                    ps.setString(1,pcCaseOld.getName());
                if(pcCase.getFormFactor()!=null)
                    ps.setString(2,pcCase.getFormFactor());
                else
                    ps.setString(2,pcCaseOld.getFormFactor());
                if(pcCase.getPrice()!=0)
                    ps.setFloat(3,pcCase.getPrice());
                else
                    ps.setFloat(3,pcCaseOld.getPrice());
                if(pcCase.getImagePath()!=null){
                    File file = new File(pcCaseOld.getImagePath());
                    file.delete();
                    ps.setString(4,pcCase.getImagePath());
                }
                else
                    ps.setString(4,pcCaseOld.getImagePath());
                ps.setInt(5, pcCase.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doUpdateStock(PcCase pcCase) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Pccases SET stock=? WHERE id=?;")){
                ps.setInt(1,pcCase.getStock());
                ps.setInt(2, pcCase.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Pccases WHERE id=?;")){
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
}
