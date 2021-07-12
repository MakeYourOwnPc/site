package Model.PcCase;

import Controller.ImagePaths;
import Model.ConnPool;

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
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public PcCase doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                    PcCase pcCase = new PcCase();
                rs.next();
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
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public ArrayList<PcCase> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases WHERE UPPER(name) LIKE UPPER('%"+name+"%') ORDER BY name LIMIT ?,?;")){
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
                throw new SQLException();
            }
        }
        catch(SQLException e){
            throw new SQLException();
        }
    }
    @Override
    public ArrayList<PcCase> doRetrieveByParameters(String name,String formFactor,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Pccases WHERE UPPER(name) LIKE UPPER('%"+name+"%') AND UPPER(formfactor) LIKE UPPER('"+formFactor+"%') ORDER BY name LIMIT ?,?;")) {
                ps.setInt(1, offset);
                ps.setInt(2, limit);
                ArrayList<PcCase> list = new ArrayList<>();
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
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
            } catch (SQLException e) {
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }

    }
    @Override
    public boolean doSave(PcCase pcCase) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Pccases (name,formfactor,price,imagepath,stock) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
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
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public boolean doUpdate(PcCase pcCase) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Pccases SET name=?,formfactor=?,price=?,imagepath=?,stock=? WHERE id=?;")){
                ps.setString(1, pcCase.getName());
                ps.setString(2,pcCase.getFormFactor());
                ps.setFloat(3,pcCase.getPrice());
                ps.setString(4,pcCase.getImagePath());
                ps.setInt(5,pcCase.getStock());
                ps.setInt(6, pcCase.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
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
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Pccases WHERE id=?;")){
                ps.setInt(1,id);
                ImagePaths.checkFile(doRetrieveById(id).getImagePath());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }
}
