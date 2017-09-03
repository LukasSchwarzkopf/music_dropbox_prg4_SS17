package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import sample.Main;
import sample.logic_model.MusicFile;
import sample.logic_model.Playlist;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController {
    @FXML
    private Node root;

    public void initialize(){
        BorderPane main = (BorderPane)root.lookup("#main_layout");
        System.out.println(main);
        GridPane master = (GridPane)main.lookup("#master");
        GridPane navigation = (GridPane)main.lookup("#navigation");
        GridPane detail = (GridPane)main.lookup("#detail");
        ListView list = (ListView)master.lookup("#playlists");
        refresh_playlists(list, detail);
    }

    public void refresh_master(Playlist playlist, GridPane _master) throws SQLException {
        System.out.println(playlist);
        ListView musicfiles = (ListView)_master.lookup("#musicfiles");
        musicfiles.getItems().clear();
        for(MusicFile file : playlist.musicfiles()){
            Text element = new Text();
            element.setId(String.valueOf(file.m_id));
            element.setText(file.m_title);
            musicfiles.getItems().add(element);
        }
    }

    public void refresh_playlists(final ListView _list, final GridPane _master){
        _list.getItems().clear();
        for (Playlist playlist : Playlist.all()) {
            Text text = new Text();
            text.setId(String.valueOf(playlist.m_id));
            text.setText(playlist.m_title);
            _list.getItems().add(text);
        }
        _list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Text element = (Text) _list.getSelectionModel().getSelectedItem();
                try {
                    Playlist playlist = Playlist.find(Integer.parseInt(element.getId()));
                    refresh_master(playlist, _master);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void renderNewPlaylist(ActionEvent actionEvent) {
        BorderPane main = (BorderPane)root.lookup("#main_layout");
        System.out.println(main);
        GridPane master = (GridPane)main.lookup("#master");
        GridPane navigation = (GridPane)main.lookup("#navigation");
        GridPane detail = (GridPane)main.lookup("#detail");
        ListView list = (ListView)master.lookup("#playlists");
        refresh_playlists(list, detail);
    }

    public void renderNewMusicfile(ActionEvent actionEvent) {
        Main.mainViewController.loadMainView("signed/musicfiles/create.fxml");
    }
}
