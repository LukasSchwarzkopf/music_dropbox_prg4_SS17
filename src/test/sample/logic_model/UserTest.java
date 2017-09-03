package sample.logic_model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void admins() throws Exception {

    }

    @Test
    public void find() throws Exception {
        // Create testuser
        User user = User.create( "Max", "Mustermann ", "user", "user" + "@music_dropbox.de", "user", "user");
        // Find user by id
        User user1 = User.find(user.m_id);
        assertEquals(user1.m_id, user.m_id);
    }

    @Test
    public void find_by_username() throws Exception {
        // Create testuser
        User user = User.create( "Max", "Mustermann ", "user", "user" + "@music_dropbox.de", "user", "user");
        // Find User by username
        User user1 = User.find_by_username("user");
        assertEquals(user1.m_username, user.m_username);
    }

    @Test
    public void all() throws Exception {
        //create a set of users
        for(int i = 0; i < 10; i++){
            User user = User.create( "Max", "Mustermann ", "user"+i, "user"+ i + "@music_dropbox.de", "user", "user");
        }
        //
        List<User> users = User.all();
        assertNotEquals(0,users.size());
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {
        for(User username : User.all()){
            username.delete();
        }
    }

    @Test
    public void create() throws Exception {
        User user = User.create( "Max", "Mustermann ", "user", "user" + "@music_dropbox.de", "user", "user");
    }

    @Test
    public void full_name() throws Exception {
    }

    @Test
    public void exists() throws Exception {
    }

    @Test
    public void musicfiles() throws Exception {
    }

    @Test
    public void playlists() throws Exception {
    }

    @Test
    public void find_by_authentication_data() throws Exception {

    }

    @Test
    public void has_role() throws Exception {
    }

}