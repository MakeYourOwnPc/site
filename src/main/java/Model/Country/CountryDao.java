package Model.Country;

import Model.ConnPool;
import Model.Cpu.Cpu;

import java.sql.*;
import java.util.ArrayList;

public class CountryDao implements ICountryDao<SQLException>{
    @Override
    public ArrayList<Country> doRetrieveAll() throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Countries;")){
                ResultSet rs = ps.executeQuery();
                ArrayList<Country> list = new ArrayList<Country>();
                while(rs.next()){
                    Country country = new Country();
                    country.setName(rs.getString("name"));
                    country.setId(rs.getString("id"));
                    list.add(country);
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
    public boolean doSave(Country country) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Countries (name,id) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,country.getName());
                ps.setString(2,country.getId());
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
    public boolean doDelete(String id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Countries WHERE id=?;")){
                ps.setString(1,id);
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
/*
    @Override
    public boolean doUpdate(Country country) throws SQLException {
        return false;
    }
*/
    @Override
    public boolean doCheckByLabel(String label) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT FROM Countries WHERE label=?;")){
                ResultSet rs = ps.executeQuery();
                return rs.isBeforeFirst();
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
    public boolean doCheckById(String id) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT FROM Countries WHERE id=?;")){
                ResultSet rs = ps.executeQuery();
                return rs.isBeforeFirst();
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
