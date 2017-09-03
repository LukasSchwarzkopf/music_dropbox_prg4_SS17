package sample.logic_model;

import sample.environment.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMusicFileMapping {
    public int m_id;
    public int m_music_file_id;
    public int m_playlist_id;


    public PlaylistMusicFileMapping (int _id, int _music_file_id, int _playlist_id){
        m_id = _id;
        m_music_file_id = _music_file_id;
        m_playlist_id = _playlist_id;
    }

    /* FIND MUSICFILE BY ID
    *
    * 1 : N Relation
    * */
    public MusicFile musicfile() throws SQLException {
        MusicFile musicfile = MusicFile.find(this.m_music_file_id);
        return musicfile;
    }

    /* FIND PLAYLIST BY ID
    *
    * 1 : N Relation
    * */
    public Playlist playlist() throws SQLException {
        Playlist playlist = Playlist.find(this.m_playlist_id);
        return playlist;
    }

    /**FIND PLAYLISTMUSICFILEMAPPING BY ID
     *
     * @param _id
     * @return playlistmusicfilemapping
     */
    public static PlaylistMusicFileMapping find(int _id) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        PlaylistMusicFileMapping playlistmusicfilemapping = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlistmusicfilemapping WHERE id = " + _id + ";";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                playlistmusicfilemapping = new PlaylistMusicFileMapping(rs.getInt("id"), rs.getInt("music_file_id"),rs.getInt("playlist_id"));
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

        return playlistmusicfilemapping;
    }



    /**RETURNS LIST OF ALL PLAYLISTMUSICFILEMAPPING
     *
     * @param
     * @return playlistmusicfilemappings
     */
    public static List<PlaylistMusicFileMapping> all(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        List<PlaylistMusicFileMapping> playlistmusicfilemappings = new ArrayList<PlaylistMusicFileMapping>();

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlistmusicfilemapping;";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                PlaylistMusicFileMapping playlistmusicfilemapping = new PlaylistMusicFileMapping(rs.getInt("id"), rs.getInt("music_file_id"), rs.getInt("playlist_id"));
                playlistmusicfilemappings.add(playlistmusicfilemapping);
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

        return playlistmusicfilemappings;
    }

    /**UPDATE PLAYLISTMUSICFILEMAPPING
     *
     * @param _title
     * @param _user_id
     * @return playlistmusicfilemapping
     */
    public PlaylistMusicFileMapping update(String _title, int _user_id){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        PlaylistMusicFileMapping playlistmusicfilemapping = null;
        try {
            stmt = Database.m_connection.createStatement();
            stmt.execute("UPDATE playlistmusicfilemapping SET music_file_id = " + m_music_file_id + ", playlist_id ="+m_playlist_id+" WHERE id = " + this.m_id + ";");
            playlistmusicfilemapping = PlaylistMusicFileMapping.find(this.m_id);
            System.out.println(playlistmusicfilemapping);
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
        return playlistmusicfilemapping;
    }

    /**DELETE A SPECIFIC PLAYLISTMUSICFILEMAPPING
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

            String query = "DELETE FROM playlistmusicfilemapping WHERE id = " + this.m_id + ";";
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

    /**CREATE PLAYLISTMUSICFILEMAPPING
     *
     * @param _music_file_id
     * @param _playlist_id
     * @return playlistmusicfilemapping
     */
    public static PlaylistMusicFileMapping create(int _music_file_id, int _playlist_id){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        PlaylistMusicFileMapping playlistmusicfilemapping = null;

        try {
            stmt = Database.m_connection.createStatement();
            int affected_rows = stmt.executeUpdate("INSERT INTO playlistmusicfilemapping (music_file_id, playlist_id) " +
                    "VALUES (" + _music_file_id + ","+ _playlist_id +");", Statement.RETURN_GENERATED_KEYS);
            if(affected_rows == 0) {
                throw new SQLException("Failed to create Record");
            } else {
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    playlistmusicfilemapping = PlaylistMusicFileMapping.find(rs.getInt(1));
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
        return playlistmusicfilemapping;
    }

    /*
    ## Private Keys Stuff
    */


    /*
    public void delete(){
        Database.PLAYLISTMUSICFILEMAPPINGS.remove(this);
    }
    */

    @Override
    public String toString() {
        StringBuilder returnstring = new StringBuilder("<MVC.models.PlaylistMusicFileMapping::{");
        returnstring.append("m_id: " + m_id);
        returnstring.append(", m_music_file_id: " + m_music_file_id);
        returnstring.append(", m_playlist_id: " + m_playlist_id);
        return returnstring.append(" }>").toString();
    }
}
