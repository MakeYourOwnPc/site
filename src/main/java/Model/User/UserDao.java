package Model.User;

import Model.ConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao implements IUserDao<SQLException>{
    @Override
    public ArrayList<User> doRetrieveAll() throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users;")){
                ResultSet rs = ps.executeQuery();
                ArrayList<User> list = new ArrayList<>();
                while(rs.next()){
                    User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setFirstName(rs.getString("firstname"));
                    user.setLastName(rs.getString("lastname"));
                    user.setAdmin(rs.getBoolean("admin"));
                    list.add(user);
                }
                rs.close();
                return list;
            }
            catch(SQLException e) {
                return null;
            }
        }
        catch(SQLException e){
            return null;
        }
    }

    @Override
    public User doRetrieveByEmail(String email) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE UPPER(email)=UPPER(?);")){
                ps.setString(1,email);
                ResultSet rs = ps.executeQuery();
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setAdmin(rs.getBoolean("admin"));
                rs.close();
                return user;
            }
            catch(SQLException e){
                return null;
            }
        }
        catch(SQLException e){
            return null;
        }
    }
    public boolean isPresent(String email) throws SQLException{
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE email=?;")){
                ps.setString(1,email);
                ResultSet rs = ps.executeQuery();
                return rs.isBeforeFirst();
            }catch(SQLException e) {
                return false;
            }
        }catch(SQLException e){
            return false;
        }
    }
    @Override
    public boolean doSave(User user) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (email,firstname,lastname,password,admin) VALUES (?,?,?,?,?);")){
                ps.setString(1,user.getEmail());
                ps.setString(2,user.getFirstName());
                ps.setString(3,user.getLastName());
                ps.setString(4,user.getPassword());
                ps.setBoolean(5,user.isAdmin());
                return ps.executeUpdate()>0;
            }
            catch(SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean doDelete(String email) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE email=?;")){
                ps.setString(1,email);
                ps.executeUpdate();
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
    public boolean doUpdate(User user) throws SQLException {
        try(Connection conn = ConnPool.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Users SET firstname=?, lastname=?, password=? WHERE email=?;")){
                ps.setString(1,user.getFirstName());
                ps.setString(2,user.getLastName());
                ps.setString(3,user.getPassword());
                ps.setString(4,user.getEmail());
                ps.executeUpdate();
                return true;
            }
            catch (SQLException e){
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
    @Override
    public boolean doChangeAdmin(String email,Boolean admin){
        try(Connection conn = ConnPool.getConnection()){
            if(admin!=null)
            try(PreparedStatement ps = conn.prepareStatement("UPDATE Users SET admin=? WHERE email=?;")){
                ps.setBoolean(1,admin);
                ps.setString(2,email);
                ps.executeUpdate();
                return true;
            }catch (SQLException e){
                return false;
            }
            else return false;
        }catch (SQLException e){
            return false;
        }
    }
}
