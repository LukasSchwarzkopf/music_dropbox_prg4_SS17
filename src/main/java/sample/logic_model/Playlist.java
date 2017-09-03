package sample.logic_model;

import sample.environment.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//
public class Playlist {

    public Playlist(int _user_id, String _title) {
        // SETTING ATTRIBUTES
        m_title = _title;
        m_user_id = _user_id;
    }

    public int m_id;
    public int m_user_id;
    public String m_title;

    // Constructor
    public Playlist (int _id, String _title, int _user_id){
        m_id = _id;
        m_title = _title;
        m_user_id = _user_id;
    }

    /**FIND PLAYLIST BY ID
     *
     * @param _id
     * @return playlist
     */
    public static Playlist find(int _id) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        Playlist playlist = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlist WHERE id = " + _id + ";";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                playlist = new Playlist(rs.getInt("id"), rs.getString("title"),rs.getInt("user_id"));
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

        return playlist;
    }

    /**FIND PLAYLIST BY TITLE
     *
     * @param _title
     * @return playlist
     */
    public static Playlist find_by_title(String _title) throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        Playlist playlist = null;

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlist WHERE title = '" + _title + "';";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                playlist = new Playlist(rs.getInt("id"), rs.getString("title"),rs.getInt("user_id"));
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

        return playlist;
    }

    /**RETURNS LIST OF ALL PLAYLISTS
     *
     * @param
     * @return playlists
     */
    public static List<Playlist> all(){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        List<Playlist> playlists = new ArrayList<Playlist>();

        try {
            stmt = Database.m_connection.createStatement();

            String query = "SELECT * FROM playlist;";

            rs = stmt.executeQuery(query);
            while(rs.next()) {
                Playlist playlist = new Playlist(rs.getInt("id"), rs.getString("title"), rs.getInt("user_id"));
                playlists.add(playlist);
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

        return playlists;
    }

    /**UPDATE PLAYLIST
     *
     * @param _title
     * @param _user_id
     * @return playlist
     */
    public Playlist update(String _title, int _user_id){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        Playlist playlist = null;
        try {
            stmt = Database.m_connection.createStatement();
            stmt.execute("UPDATE playlist SET title = '" + _title + "', user_id ="+_user_id+" WHERE id = " + this.m_id + ";");
            playlist = Playlist.find(this.m_id);
            System.out.println(playlist);
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
        return playlist;
    }

    /**DELETE A SPECIFIC PLAYLIST
     *
     * @return result
     */
    public boolean delete() throws SQLException {
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false ;

        // All entries in PlaylistMusicFileMapping related to this specific
        // Playlist have to get deleted first, before the playlist itself can be deleted.
        for(PlaylistMusicFileMapping mapping : this.playlist_musicfile_mappings()){
            mapping.delete();
        }

        for(PlaylistUserMapping mapping : this.playlist_user_mappings()){
            mapping.delete();
        }

        try {
            stmt = Database.m_connection.createStatement();

            String query = "DELETE FROM playlist WHERE id = " + this.m_id + ";";
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

    /**CREATE PLAYLIST
     *
     * @param _title
     * @param _user_id
     * @return playlist
     */
    public static Playlist create(String _title, int _user_id){
        // Do something with the Connection
        Statement stmt = null;
        ResultSet rs = null;
        Playlist playlist = null;

        try {
            stmt = Database.m_connection.createStatement();
            int affected_rows = stmt.executeUpdate("INSERT INTO playlist (title, user_id) VALUES ('" + _title + "',"+ _user_id +");", Statement.RETURN_GENERATED_KEYS);
            if(affected_rows == 0) {
                throw new SQLException("Failed to create Record");
            } else {
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    playlist = Playlist.find(rs.getInt(1));
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
        return playlist;
    }

    // RELATIONS

    /**
     * ALL MUSICFILES FOR USER
     * M : N RELATION
     *
     */
    public List<MusicFile> musicfiles() throws SQLException {
        List<MusicFile> musicfiles = new ArrayList<MusicFile>();
        for(PlaylistMusicFileMapping mapping : PlaylistMusicFileMapping.all()){
            if(mapping.m_playlist_id == this.m_id){
                MusicFile file = MusicFile.find(mapping.m_music_file_id);
                if(file != null){
                    musicfiles.add(file);
                }
            }
        }
        return musicfiles;
    }

    /*
    * ALL PLAYLISTMUSICFILEMAPPINGS FOR USER
    * M : N RELATION
    * */
    public List<PlaylistMusicFileMapping> playlist_musicfile_mappings(){
        List<PlaylistMusicFileMapping> mappings = new ArrayList<PlaylistMusicFileMapping>();
        for(PlaylistMusicFileMapping mapping : PlaylistMusicFileMapping.all()){
            if(mapping.m_playlist_id == this.m_id){
                mappings.add(mapping);
            }
        }
        return mappings;
    }

    /*
    * ALL PLAYLISTUSERMAPPINGS FOR USER
    * M : N RELATION
    * */
    public List<PlaylistUserMapping> playlist_user_mappings(){
        List<PlaylistUserMapping> mappings = new ArrayList<PlaylistUserMapping>();
        for(PlaylistUserMapping mapping : PlaylistUserMapping.all()){
            if(mapping.m_playlist_id == this.m_id){
                mappings.add(mapping);
            }
        }
        return mappings;
    }

    /* FIND USER BY ID
    *
    * 1 : N RELATION
    * */
    public User user() throws SQLException {
        return User.find(this.m_user_id);
    }
    /* ADDING A FILE TO A SPECIFIC PLAYLIST
    *
    * 1 : N Relation
    * */
    public PlaylistMusicFileMapping add(MusicFile file){
        PlaylistMusicFileMapping mapping = PlaylistMusicFileMapping.create(file.m_id, this.m_id);
        return mapping;
    }

    /* REMOVE A SPECIFIC MUSICFILE FROM A PLAYLIST
    * */
    public void remove(MusicFile file) {
        // forgot to implement
    }

    /* INVITE A USER TO THE PLAYLIST OF A SPECIFIC USER
    *
    * 1 : N Relation
    * */
    public PlaylistUserMapping invite(User _user){
        PlaylistUserMapping mapping = PlaylistUserMapping.create(_user.m_id, this.m_id);
        return mapping;
    }

    // Creates Strings for a fake playlist inside the fake database for a specific user
    @Override
    public String toString() {
        StringBuilder returnstring = new StringBuilder("<MVC.models.Playlist::{");
        returnstring.append("m_id: " + m_id);
        returnstring.append(", m_user_id: " + m_user_id);
        returnstring.append(", m_title: " + m_title);
        return returnstring.append(" }>").toString();
    }


}
