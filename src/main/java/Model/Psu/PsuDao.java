package Model.Psu;

import Controller.ImagePaths;
import Model.ConnPool;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class PsuDao implements IPsuDao <SQLException> {
    @Override
    public ArrayList<Psu> doRetrieveAll(int limit, int offset) throws SQLException {
            try(Connection conn = ConnPool.getConnection()){
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus ORDER BY name LIMIT ?,?;")){
                    ps.setInt(1,offset);
                    ps.setInt(2,limit);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Psu> list = new ArrayList<Psu>();
                    while(rs.next()){
                        Psu psu = new Psu();
                        psu.setName(rs.getString("name"));
                        psu.setId(rs.getInt("id"));
                        psu.setPower(rs.getInt("power"));
                        psu.setPrice(rs.getFloat("price"));
                        psu.setStock(rs.getInt("stock"));
                        psu.setImagePath(rs.getString("imagepath"));
                        list.add(psu);
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
    public Psu doRetrieveById(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                    Psu psu = new Psu();
                rs.next();
                    psu.setName(rs.getString("name"));
                    psu.setId(rs.getInt("id"));
                    psu.setPower(rs.getInt("power"));
                    psu.setPrice(rs.getFloat("price"));
                    psu.setStock(rs.getInt("stock"));
                psu.setImagePath(rs.getString("imagepath"));
                    return psu;
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
    public ArrayList<Psu> doRetrieveByName(String name,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE UPPER(name) LIKE UPPER('%"+name+"%') ORDER BY name LIMIT ?,?;")){
                ps.setInt(1,offset);
                ps.setInt(2,limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Psu> list = new ArrayList<Psu>();
                while(rs.next()){
                    Psu psu = new Psu();
                    psu.setName(rs.getString("name"));
                    psu.setId(rs.getInt("id"));
                    psu.setPower(rs.getInt("power"));
                    psu.setPrice(rs.getFloat("price"));
                    psu.setStock(rs.getInt("stock"));
                    psu.setImagePath(rs.getString("imagepath"));
                    list.add(psu);
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
    public ArrayList<Psu> doRetrieveByParameters(String name,int power,int limit, int offset) throws SQLException {
        try(Connection conn = ConnPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Psus WHERE UPPER(name) LIKE UPPER('%"+name+"%') AND power>=? ORDER BY name LIMIT ?,?;")) {
                ps.setInt(1, power);
                ps.setInt(2, offset);
                ps.setInt(3, limit);
                ResultSet rs = ps.executeQuery();
                ArrayList<Psu> list = new ArrayList<>();
                while (rs.next()) {
                    Psu psu = new Psu();
                    psu.setName(rs.getString("name"));
                    psu.setId(rs.getInt("id"));
                    psu.setPower(rs.getInt("power"));
                    psu.setPrice(rs.getFloat("price"));
                    psu.setStock(rs.getInt("stock"));
                    psu.setImagePath(rs.getString("imagepath"));
                    list.add(psu);
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
    public boolean doDelete(int id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Psus WHERE id=?;")){
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

    @Override
    public boolean doUpdate(Psu psu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Psus set name=?,power=?,price=?,imagepath=?,stock=? WHERE id=?;")){
                ps.setString(1, psu.getName());
                ps.setInt(2,psu.getPower());
                ps.setFloat(3,psu.getPrice());
                ps.setString(4,psu.getImagePath());
                ps.setInt(5,psu.getStock());
                ps.setInt(6, psu.getId());
                return ps.executeUpdate()>0;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }

    @Override
    public boolean doUpdateStock(Psu psu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Psus SET stock=? WHERE id=?;")){
                ps.setInt(1,psu.getStock());
                ps.setInt(2, psu.getId());
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
    public boolean doSave(Psu psu) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Psus (name,power,price,imagepath,stock) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,psu.getName());
                ps.setInt(2,psu.getPower());
                ps.setFloat(3,psu.getPrice());
                ps.setString(4,psu.getImagePath());
                ps.setInt(5, psu.getStock());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                psu.setId(rs.getInt(1));
                return true;
            }catch(SQLException e){
                throw new SQLException();
            }
        }catch(SQLException e){
            throw new SQLException();
        }
    }


}
