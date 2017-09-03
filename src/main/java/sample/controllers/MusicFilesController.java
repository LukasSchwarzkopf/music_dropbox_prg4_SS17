package sample.controllers;

/**
 * Created by The Mad Dragon on 28.08.2017.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import sample.Main;
import sample.logic_model.FileType;
import sample.logic_model.MusicFile;
import sample.logic_model.User;

import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class MusicFilesController {

    @FXML
    private Node root;
    // CREATE MUSIC-FILE
    public static boolean create(int _user_id, String _title, String _album, String _interpret, File _file){
        String file_path = _file.getPath().toString();
        String file_extension = file_path.substring(file_path.indexOf(".") + 1, file_path.length());
        MusicFile musicFile = MusicFile.create(_title,_interpret,_album,file_path,_user_id, file_extension);
        return true;
    }

    // UPDATING MUSIC-FILE BY ID
    public static boolean update(int _id, String _title, String _album, String _interpret, File _file) throws SQLException {
        MusicFile musicfile = MusicFile.find(_id);
        if(musicfile != null){
            String file_path = _file.getPath().toString();
            String file_extension = file_path.substring(file_path.indexOf(".") + 1, file_path.length());
            // update music-file
            musicfile = musicfile.update(_title, _interpret, _album, file_path, musicfile.m_user_id, file_extension);
            if(musicfile != null){
                return true;
            } else {
                return false;
            }
        } else {
            // music-file not found
            return false;
        }
    }

    // DELETING MUSIC-FILE BY ID
    public static boolean delete(int _id) throws SQLException {
        MusicFile file = MusicFile.find(_id);
        file.delete();
        return true;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public void create(ActionEvent actionEvent) {
        GridPane musicfile_layout = (GridPane) root.lookup("#musicfile_layout");
        TextField field_title = (TextField)musicfile_layout.lookup("#title");
        TextField field_interpret = (TextField)musicfile_layout.lookup("#interpret");
        TextField field_album = (TextField)musicfile_layout.lookup("#album");
        TextField field_filepath = (TextField)musicfile_layout.lookup("#filepath");

        String title = field_title.getText();
        String interpret = field_interpret.getText();
        String album = field_album.getText();
        String filepath = field_filepath.getText();

        File file = new File(filepath);
        String fileextension = getFileExtension(file);

        MusicFile.create(title, interpret, album, filepath, Main.sm_current_user.m_id, fileextension);
    }

    public void choosefile(ActionEvent actionEvent) {
        GridPane musicfile_layout = (GridPane) root.lookup("#musicfile_layout");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = null;
        for(FileType type : FileType.all()){
             filter = new FileChooser.ExtensionFilter(type.m_title + " Files", "*." + type.m_title);
            fileChooser.getExtensionFilters().add(filter);
        }

        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        TextField filepath = (TextField)musicfile_layout.lookup("#filepath");
        filepath.setText(String.valueOf(file));
    }
}
