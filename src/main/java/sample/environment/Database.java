package sample.environment;

import sample.logic_model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static Connection m_connection = null;
    public static List<FileType> FILETYPES = new ArrayList<FileType>();
    public static List<MusicFile> MUSICFILES = new ArrayList<MusicFile>();
    public static List<Playlist> PLAYLISTS = new ArrayList<Playlist>();
    public static List<PlaylistMusicFileMapping> PLAYLISTMUSICFILEMAPPINGS = new ArrayList<PlaylistMusicFileMapping>();
    public static List<PlaylistUserMapping> PLAYLISTUSERMAPPINGS = new ArrayList<PlaylistUserMapping>();
    public static List<User> USERS = new ArrayList<User>();
    public static String secret = "PBEWithMD5AndDES";
    public static String secret2 = "bar";

    public static boolean connect() throws SQLException {
        m_connection = DriverManager.getConnection("jdbc:mysql://localhost/music_dropbox?user=root&password=root");
        return true;
    }

    /**
     * CREATES TEST-VALUES FOR TESTING
     *
     * @return database with test-values
     *
     * ## Obsolete ##
     */


    public static boolean create_test_data(){
        /*
        try {
            m_connection = DriverManager.getConnection("jdbc:mysql://localhost/music_dropbox?user=root&password=root");
            // CREATING ALLOWED FILETYPES
            System.out.println("===============================================================================================================");
            System.out.println("===============================================================================================================");
            System.out.println("CREATING ALLOWED FILETYPES");

            FileType mp3 = new FileType("mp3");
            FileType md = new FileType("md");
            FileType wav = new FileType("wav");

            System.out.println(mp3);
            System.out.println(md);
            System.out.println(wav);

            // CREATING TESTUSERS
            System.out.println("===============================================================================================================");
            System.out.println("===============================================================================================================");
            System.out.println("CREATING TESTUSERS");

            User admin = new User("liam", "bla", "admin", "admin", "admin");
            System.out.println(admin);

            int max_users = 10;

            for(int i = 0; i < max_users; i++){
                User user = new User("Max", "Mustermann " + i, "user", "max_mustermann_" + i, "max_mustermann_" + i);
                System.out.println(user);
            }

            // CREATING TESTMUSICFILES (WITHOUT FILES)
            System.out.println("===============================================================================================================");
            System.out.println("===============================================================================================================");
            System.out.println("CREATING TESTMUSICFILES");

            int max_files_per_user = 10;
            for(User cur_user : User.all()){
                System.out.println("===============================================================================================================");
                System.out.println("===============================================================================================================");
                System.out.println("CREATING FILES FOR " + cur_user.full_name());

                for(int i = 0; i < max_files_per_user; i++){
                    Path path = Paths.get("README.md");
                    File real_file = new File(path.toUri());
                    try{
                        MusicFile file = new MusicFile(cur_user, "Testfile " + i + " " + cur_user.full_name(), "Testalbum " + cur_user.full_name(), "Interpret 1", real_file);
                        System.out.println("\t" + file);
                    } catch (NullPointerException e){
                        // REACT TO INVALID FILE TYPE
                        e.printStackTrace();
                    }
                }
            }

            // CREATING TESTPLAYLISTS (WITHOUT FILES)
            System.out.println("===============================================================================================================");
            System.out.println("===============================================================================================================");

            System.out.println("CREATING TESTPLAYLIST FOR LAST USER");
            Playlist playlist = new Playlist(User.first(), "Best of - Rammstein");
            System.out.println(playlist);

            System.out.println("\tADD MUSICFILES TO PLAYLIST");
            for(MusicFile musicfile : User.last().musicfiles()){
                playlist.add(musicfile);
            }

            for(MusicFile musicfile : playlist.musicfiles()){
                System.out.println("\t" + musicfile);
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        */
        boolean result = true;
        return result;
    };

}
