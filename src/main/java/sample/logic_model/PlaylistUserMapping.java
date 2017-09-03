package sample.logic_model;

import sample.environment.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistUserMapping {
    /**
     * CREATES A M:N RELATION BETWEEN USER AND A PLAYLIST
     *
     * @param _user
     * @param _playlist
     */
    public PlaylistUserMapping(User _user, Playlist _playlist){


        // SETTING ATTRIBUTES
        m_user = _user;
        m_playlist = _playlist;
        //this.persist_PlaylistUserMapping();
    }

    public int m_id;
    public User m_user;
    public Playlist m_playlist;
    public int m_user_id;
    public int m_playlist_id;

    //Constructor
    public PlaylistUserMapping (int _id, int _user_id, int _playlist_id){
        m_id = _id;
        m_user_id = _user_id;
        m_playlist_id = _playlist_id;
    }


    /* FIND USER BY ID
    *
    *
    * */
    public User user() throws SQLException {
        return User.find(this.m_user_id);
    }

    /* FIND PLAYLIST BY ID
    *
    *
    * */
    public Playlist playlist() throws SQLException {
        return Playlist.find(this.m_playlist_id);
    }



    /**FIND PLAYLISTUSERMAPPING BY ID
     *
     * @param _id
     * @return playlistusermapping
     */
    public static PlaylistUserMapping find(int _id) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        PlaylistUserMapping playlistusermapping = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlistusermapping WHERE id = " + _id + ";";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                playlistusermapping = new PlaylistUserMapping(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("playlist_id"));
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

        return playlistusermapping;
    }



    /**RETURNS LIST OF ALL PLAYLISTUSERMAPPINGS
     *
     * @param
     * @return playlistusermappings
     */
    public static List<PlaylistUserMapping> all(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        List<PlaylistUserMapping> playlistusermappings = new ArrayList<PlaylistUserMapping>();

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlistusermapping;";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                PlaylistUserMapping playlistusermapping = new PlaylistUserMapping(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("playlist_id"));
                playlistusermappings.add(playlistusermapping);
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

        return playlistusermappings;
    }

    /**UPDATE PLAYLISTUSERMAPPING
     *
     * @param _title
     * @param _user_id
     * @return playlistusermapping
     */
    public PlaylistUserMapping update(String _title, int _user_id){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        PlaylistUserMapping playlistusermapping = null;
        try {
            stmt = Database.m_connection.createStatement();
            stmt.execute("UPDATE playlistusermapping SET user_id = " + m_user_id + ", playlist_id ="+m_playlist_id+" WHERE id = " + this.m_id + ";");
            playlistusermapping = PlaylistUserMapping.find(this.m_id);
            System.out.println(playlistusermapping);
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
        return playlistusermapping;
    }

    /**DELETE A SPECIFIC PLAYLISTUSERMAPPING
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

            String query = "DELETE FROM playlistusermapping WHERE id = " + this.m_id + ";";
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

    /**CREATE PLAYLISTUSERMAPPING
     *
     * @param _user_id
     * @param _playlist_id
     * @return playlistusermapping
     */
    public static PlaylistUserMapping create(int _user_id, int _playlist_id){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        PlaylistUserMapping playlistusermapping = null;

        try {
            stmt = Database.m_connection.createStatement();
            int affected_rows = stmt.executeUpdate("INSERT INTO playlistusermapping (user_id, playlist_id) " +
                    "VALUES (" + _user_id + ","+ _playlist_id +");", Statement.RETURN_GENERATED_KEYS);
            if(affected_rows == 0) {
                throw new SQLException("Failed to create Record");
            } else {
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    playlistusermapping = PlaylistUserMapping.find(rs.getInt(1));
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
        return playlistusermapping;
    }
}