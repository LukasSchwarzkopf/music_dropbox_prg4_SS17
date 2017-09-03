package sample.logic_model;

import sample.environment.Database;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MusicFile{

    public MusicFile() {
    }

    /**
     * ADDING NEW MUSICFILES
     *
     * @param _title
     * @param _album
     * @param _interpret
     //* @param _file
     *
     * Allows the user to add music-files from a local storage to the database
     *
     * The user can add an files with a prior allowed set of extension. and has to add a title and interpret and album
     * of the audio-file. Title, interpret and album could later be used for sorting purposes.
     *
     * At the moment there are no character restrictions for the input of the title, interpret and album
     * because its planned to outsource these to the forms of the web- or userinterface of the final program
     *
     */
    public MusicFile(int _user_id, String _title, String _album,String _interpret, String _file_path, String file_extension){
        if(MusicFile.allowed_to_upload(file_extension)){

            // SETTING ATTRIBUTES
            m_title = _title;
            m_user_id = _user_id;
            m_album = _album;
            m_interpret = _interpret;
            m_file_extension = file_extension;
            //MusicFile.create();

            // Database.MUSICFILES.add(this);
        } else {
            System.out.println("INVALID FILETYPE");
        }
    };

    public int m_id;
    public String m_title;
    public String m_interpret;
    public String m_file_path;
    public String m_file_extension;
    public String m_album;
    public int m_user_id;


    /** CHECKS IF MUSIC-FILE EXTENSION IS ALLOWED
     *
     * @param _file_extension
     * @return
     */
    public static boolean allowed_to_upload(String _file_extension) {
        boolean result = false;
        for(FileType type : FileType.all()){
            if(type.m_title.equals(_file_extension)){
                result = true;
            }
        }
        // extension not allowed
        return result;
    }

    // DATABASE TRANSACTIONS

    /* FIND USER BY ID
    *
    * */
    public User user() throws SQLException {
        return User.find(this.m_user_id);
    }

    // Constructor
    public MusicFile(int _id, String _title, String _interpret, String _album, String _file_path, int _user_id, String _file_extension){
        m_id = _id;
        m_title = _title;
        m_interpret = _interpret;
        m_album = _album;
        m_file_path = _file_path;
        m_file_extension = _file_extension;
        m_user_id = _user_id;
    }

    /**FIND MUSICFILE BY ID
     *
     * @param _id
     * @return musicfile
     */
    public static MusicFile find(int _id) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        MusicFile musicfile = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM musicfile WHERE id = " + _id + ";";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                musicfile = new MusicFile(rs.getInt("id"), rs.getString("title"), rs.getString("interpret"),rs.getString("album"),
                        rs.getString("file"),rs.getInt("user_id"), rs.getString("file_extension"));
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
        return musicfile;
    }

    /**FIND MUSICFILE BY TITLE
     *
     * @param _title
     * @return musicfile
     */
    public static MusicFile find_by_title(String _title) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        MusicFile musicfile = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM musicfile WHERE title = '" + _title + "';";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                musicfile = new MusicFile(rs.getInt("id"), rs.getString("title"), rs.getString("interpret"),rs.getString("album"),
                        rs.getString("file"),rs.getInt("user_id"), rs.getString("file_extension"));
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
        return musicfile;
    }

    /**RETURNS LIST OF ALL MUSICFILES
     *
     * @param
     * @return musicfiles
     */
    public static List<MusicFile> all(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        List<MusicFile> musicfiles = new ArrayList<MusicFile>();

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM musicfile;";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                MusicFile musicfile = new MusicFile(rs.getInt("id"), rs.getString("title"), rs.getString("interpret"),rs.getString("album"),
                        rs.getString("file"),rs.getInt("user_id"), rs.getString("file_extension"));
                musicfiles.add(musicfile);
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

        return musicfiles;
    }

    /**UPDATE MUSICFILE
     *
     * @param _title
     * @param _interpret
     * @param _album
     * @param _file_path
     * @param _user_id
     * @param _file_extension
     * @return musicfile
     */
    public MusicFile update(String _title, String _interpret, String _album, String _file_path, int _user_id, String _file_extension){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        MusicFile musicfile = null;
        try {
            stmt = Database.m_connection.createStatement();
            stmt.execute("UPDATE musicfile SET title = '" + _title + "',interpret = '" + _interpret + "', album='"+ _album+"', file = '"+ _file_path+"'," +
                    " user_id = "+ _user_id+", file_extension= '"+_file_extension+"' WHERE id = " + this.m_id + ";");
            musicfile = MusicFile.find(this.m_id);
            System.out.println(musicfile);
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
        return musicfile;
    }

    /**DELETE A SPECIFIC MUSICFILE
     *
     * @param
     * @return result
     */
    public boolean delete(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false ;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "DELETE FROM musicfile WHERE id = " + this.m_id + ";";
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

    /**CREATE MUSICFILE
     *
     * @param _title
     * @param _interpret
     * @param _album
     * @param _file_path
     * @param _user_id
     * @param _file_extension
     * @return musicfile
     */
    public static MusicFile create(String _title, String _interpret, String _album, String _file_path, int _user_id, String _file_extension){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        MusicFile musicfile = null;

        try {
            stmt = Database.m_connection.createStatement();
            int affected_rows = stmt.executeUpdate("INSERT INTO musicfile (title,interpret,album,file,user_id,file_extension) " +
                    "VALUES ('" + _title + "','" + _interpret + "', '"+ _album+"', '"+ _file_path+"', "+ _user_id+", '"+_file_extension+"');", Statement.RETURN_GENERATED_KEYS);
            if(affected_rows == 0) {
                throw new SQLException("Failed to create Record");
            } else {
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    musicfile = MusicFile.find(rs.getInt(1));
                }
            }
            rs = stmt.getResultSet();
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
        return musicfile;
    }


    @Override
    public String toString() {
        StringBuilder returnstring = new StringBuilder("<MVC.models.MusicFile::{");
        returnstring.append("m_id: " + m_id);
        returnstring.append(", m_title: " + m_title);
        returnstring.append(", m_interpret: " + m_interpret);
        returnstring.append(", m_album: " + m_album);
        return returnstring.append(" }>").toString();
    }
}
