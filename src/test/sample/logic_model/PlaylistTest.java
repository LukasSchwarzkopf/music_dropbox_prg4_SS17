package sample.logic_model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlaylistTest {
    User testuser = User.create("test", "user" , "user", "user@user.de", "asdf", "user");
    @Test
    public void find() throws Exception {
        // Create Playlist
        Playlist playlist = Playlist.create("testplaylist", testuser.m_id);
        // Find Playlist by ID
        Playlist playlist1 = Playlist.find(playlist.m_id);
        assertEquals(playlist.m_title, playlist1.m_title);
    }

    @Test
    public void find_by_title() throws Exception {
        // Find playlist by title
        Playlist playlist = Playlist.create("testplaylist", testuser.m_id);
        Playlist playlist1 = Playlist.find_by_title("testplaylist");
        assertEquals(playlist.m_id, playlist1.m_id);
    }

    @Test
    public void all() throws Exception {
        //create a set of playlists
        for(int i = 0; i < 10; i++){
            Playlist playlist = Playlist.create("testplaylist"+i, testuser.m_id);
        }
        //
        List<Playlist> playlists = testuser.playlists();
        assertNotEquals(0,playlists.size());
    };

    @Test
    public void update() throws Exception {
        //Playlist playlist = Playlist
    }

    @Test
    public void delete() throws Exception {
        // Delete all playlists
        for(Playlist title : Playlist.all()){
            title.delete();
        }
    }

    @Test
    public void create() throws Exception {
        // CREATE PLAYLIST
        Playlist playlist = Playlist.create("testplaylist", 0);
        for(MusicFile file : MusicFile.all()){
            playlist.add(file);
        }
    }

    @Test
    public void musicfiles() throws Exception {

    }

    @Test
    public void playlist_musicfile_mappings() throws Exception {

    }

    @Test
    public void playlist_user_mappings() throws Exception {

    }

    @Test
    public void user() throws Exception {

    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

    @Test
    public void invite() throws Exception {

    }

}