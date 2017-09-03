package sample.controllers;

import sample.logic_model.FileType;
import sample.logic_model.User;

import java.sql.SQLException;

public class FileTypesController {

    // CREATE NEW FILE-TYPE
    public static boolean create(int _user_id, String _title) throws SQLException {
        User user = User.find(_user_id);
        if(user != null){
            if(user.has_role("admin")){
                // create new file-type
                FileType file_type = FileType.create(_title);
                if(file_type != null){
                    return true;
                } else {
                    // file-type already exist
                    return false;
                }
            } else {
                // user is no admin
                return false;
            }
        }
        return false;
    }

    // DELETE FILE-TYPE
    public static boolean delete(int _id, int _user_id) throws SQLException {
        User user = User.find(_user_id);
        FileType type = FileType.find(_id);
        if(type != null){
            if(user.has_role("admin")) {
                type.delete();
                return true;
            } else {
                // user is no admin
                return false;
            }
        } else {
            // File-type doesn't exist
            return false;
        }
    }
}