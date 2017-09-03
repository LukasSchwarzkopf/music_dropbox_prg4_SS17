package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.environment.Database;
import sample.helper.Cryptor;
import sample.logic_model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import sample.logic_model.MusicFile;
import sample.logic_model.Playlist;
import sample.logic_model.User;

import java.sql.SQLException;

public class PlaylistsController {

    // ADDING MUSIC-FILE TO PLAYLIST
    public static boolean add_musicfile(int _id, int _musicfile_id) throws SQLException {
        Playlist playlist = Playlist.find(_id);
        MusicFile file = MusicFile.find(_musicfile_id);
        playlist.add(file);
        return true;
    }

    // REMOVE MUSIC-FILE FROM PLAYLIST
    public static boolean remove_musicfile(int _id, int _musicfile_id) throws SQLException {
        Playlist playlist = Playlist.find(_id);
        MusicFile file = MusicFile.find(_musicfile_id);
        playlist.remove(file);
        return true;
    }

    // INVITE ANOTHER USER TO THE PLAYLIST
    public static boolean invite(int _owner_id, int _invited_id, int _playlist_id) throws SQLException {
        User owner = User.find(_owner_id);
        User invited_user = User.find(_invited_id);
        Playlist playlist = Playlist.find(_playlist_id);
        // invite-giver has to be the owner of the playlist
        if(playlist.m_user_id == owner.m_id){
            playlist.invite(invited_user);
            return true;
        } else {
            // user isn't playlist-owner
            return false;
        }
    };

    // DELETING PLAYLIST BY ID
    public static boolean delete(int _playlist_id) throws SQLException {
        Playlist playlist = Playlist.find(_playlist_id);
        playlist.delete();
        return true;
    }

    public void create(ActionEvent actionEvent) {
    }

    public void choosefile(ActionEvent actionEvent) {
    }
}