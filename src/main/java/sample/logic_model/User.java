package sample.logic_model;

import sample.environment.Database;
import sample.helper.Cryptor;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {


    public User() {
    }

    /** CREATING NEW USER
     *
     * currently no check for restrictions for input.
     *
     * @param _firstname
     * @param _lastname
     * @param _role
     * @param _username
     * @param _password
     */
    public User(String _firstname, String _lastname, String _role, String _username, String _password, String _email){
        // SETTING ATTRIBUTES
        m_firstname = _firstname;
        m_lastname = _lastname;
        m_username = _username;
        m_email = _email;
        m_password = _password;
        m_role = _role;

        //Database.USERS.add(this);
    };

    public int m_id;
    public String m_username;
    public String m_password;
    public String m_firstname;
    public String m_lastname;
    public String m_email;
    public String m_role;

    /**
     * LIST OF ALL ADMINS
     *
     * @return list of admins
     *
     * Creates a list of users with the role "admin" to
     * seperate this user-group from other groups
     */
    public static List<User> admins(){
        List<User> admins = new ArrayList();
        for(User cur_user : User.all()){
            if(cur_user.m_role.equals("admin")){
                admins.add(cur_user);
            }
        }
        return admins;
    }


    public User (int _id, String _firstname, String _lastname, String _username, String _email, String _password, String _role){
        m_id = _id;
        m_firstname = _firstname;
        m_lastname = _lastname;
        m_username = _username;
        m_email = _email;
        m_password = _password;
        m_role = _role;
    }

    /**FIND USER BY ID
     *
     * @param _id
     * @return user
     */
    public static User find(int _id) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM user WHERE id = " + _id + ";";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("username"),
                        rs.getString("email"),rs.getString("password"), rs.getString("role"));
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return user;
    }

    /**FIND USER BY USERNAME
     *
     * @param _username
     * @return user
     */
    public static User find_by_username(String _username) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM user WHERE username = '" + _username + "';";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("username"),
                        rs.getString("email"),rs.getString("password"), rs.getString("role"));
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return user;
    }

    /**RETURNS A LIST OF ALL USERS
     *
     * @param
     * @return users
     */
    public static List<User> all(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM user;";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("username"),
                        rs.getString("email"),rs.getString("password"), rs.getString("role"));
                users.add(user);
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

        return users;
    }

    /**UPDATE USER
     *
     * @param _firstname
     * @param _lastname
     * @param _username
     * @param _email
     * @param _password
     * @param _role
     *
     * @return user
     */
    public User update(String _firstname, String _lastname, String _username, String _email, String _password, String _role){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            stmt = Database.m_connection.createStatement();
            stmt.execute("UPDATE user SET firstname = '" + _firstname + "',lastname = '" + _lastname + "', username='"+ _username+"', email = '"+ _email+"'," +
                    " password = '"+ _password+"', role = '"+_role+"' WHERE id = " + this.m_id + ";");
            user = User.find(this.m_id);
            System.out.println(user);
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return user;
    }

    /**DELETE A SPECIFIC USER
     *
     * @return result
     */
    public boolean delete(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false ;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "DELETE FROM user WHERE id = " + this.m_id + ";";
            if(stmt.execute(query)){
                result = true;
            }
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return result;
    }

    /**CREATE USER
     *
     * @param _firstname
     * @param _lastname
     * @param _username
     * @param _email
     * @param _password
     * @param _role
     *
     * @return user
     */
    public static User create(String _firstname, String _lastname, String _username, String _email, String _password, String _role){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            stmt = Database.m_connection.createStatement();
            _password = Cryptor.encrypt(Database.secret, Database.secret2, _password);
            int affected_rows = stmt.executeUpdate("INSERT INTO user (firstname,lastname,username,email,password,role) VALUES ('" + _firstname + "','" + _lastname + "', '"+ _username+"', '"+ _email+"', '"+ _password+"', '"+_role+"');", Statement.RETURN_GENERATED_KEYS);
            if(affected_rows == 0) {
                throw new SQLException("Failed to create Record");
            } else {
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    user = User.find(rs.getInt(1));
                }
            }
            rs = stmt.getResultSet();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return user;
    }




    /**
     * CREATES THE FULLNAME OF A USER
     *
     * @return full name of a user
     *
     */
    public String full_name(){
        return "" + m_firstname + " " + m_lastname;
    }

    // CHECKS IF USER EXISTS
    public static boolean exists(String _username){
        for(User user : User.all()){
            if(user.m_username == _username){
                return true;
            }

        }
        return false;
    }

    // RELATIONS

    /**
     * ALL MUSICFILES THAT BELONG TO A SPECIFIC USER
     *
     *
     * M : N Relation
     *
     */
    public List<MusicFile> musicfiles(){
        List<MusicFile> musicfiles = new ArrayList<MusicFile>();
        for (MusicFile musicfile : MusicFile.all()) {
            if(musicfile.m_user_id == this.m_id){
                musicfiles.add(musicfile);
            }
        }
        return musicfiles;
    }

    /* ALL PLAYLISTS THAT BELONG TO A SPECIFIC USER
    *
    * M : N Relation
    * */
    public List<Playlist> playlists(){
        List<Playlist> playlists = new ArrayList<Playlist>();
        for (Playlist playlist : Playlist.all()) {
            if(playlist.m_user_id == this.m_id){
                playlists.add(playlist);
            }
        }
        return playlists;
    }


    // SEARCHES FOR USER IN CASE OF LOGIN
    public static User find_by_authentication_data(String _username, String _password){
        for(User user : User.all()){
            if(user.m_username.equals(_username) && user.m_password.equals(_password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder returnstring = new StringBuilder("<MVC.models.User::{");
        returnstring.append("m_id: " + m_id);
        returnstring.append(", m_username: " + m_username);
        returnstring.append(", m_firstname: " + m_firstname);
        returnstring.append(", m_lastname: " + m_lastname);
        returnstring.append(", m_role: " + m_role);
        return returnstring.append(" }>").toString();
    }

    // ASKS FOR USER_ROLE
    public boolean has_role(String _role) {
        return this.m_role == _role;
    }
}
