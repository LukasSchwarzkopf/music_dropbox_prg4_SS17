package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.controllers.MainViewController;
import sample.environment.Database;
import sample.logic_model.*;
import sample.utils.JavaFxUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

//from  ww  w.  j a  va2  s  .  co m
public class Main extends Application {
    public static User sm_current_user;
    public static MainViewController mainViewController = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader myLoader = JavaFxUtils.getFxmlLoader("main.fxml");
        Parent node = (Parent)myLoader.load();
        //    Group root = new Group();
//        node.getChildren().addAll(mainController);
        mainViewController = myLoader.getController();
        Scene scene = new Scene(node, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException {
        // Database.create_test_data();
        Database.connect();

        User admin = User.create("Lukas","Schwarzkopf","admin","lukas@music_dropbox.de","admin","admin");
        if(admin != null){
            System.out.println(admin);

            // ## Testing Users

            // creating users
            for(int i = 0; i < 20; i++){
                User user = User.create("Max", "Mustermann "+i, "user" + i, "user" + i + "@music_dropbox.de", "user", "user");
                //System.out.println(user);
            }

            // ## Testing Musicfiles

            // create musicfiles
            String file_ext = "mp3";
            for(int i = 0; i < 20; i ++){
                MusicFile musicFile = MusicFile.create("Track" + i, "Interpret", "Album", "foobar/track"+ i + "." + file_ext, admin.m_id, file_ext);
                System.out.println(musicFile);
            }


            // ## Testing Playlist
            //Creating Playlists/

            for(int i=0; i < 5; i++){
                Playlist playlist = Playlist.create("Playlist"+i, admin.m_id);
                if(i == 0){
                    for(MusicFile file : MusicFile.all()){
                        playlist.add(file);
                    }
                }
                System.out.println(playlist);
            }

            // ## Creating Filetypes
            FileType new_filetype = FileType.create("mp3");
            FileType new_filetype1 = FileType.create("mp4");
            FileType new_filetype2 = FileType.create("wav");
            FileType new_filetype4 = FileType.create("md");
            System.out.println(new_filetype);
        }
        launch(args);
    }
}