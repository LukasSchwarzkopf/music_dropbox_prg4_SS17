package sample.environment;

import sample.controllers.FileTypesController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/*
WORK IN PROGRESS

UNIT TESTS

I started to write some unit tests, but I haven't had the time to complete this task ...

* */
public class Application {

    public static boolean test(boolean _silent) throws SQLException {
        test_relations(_silent);
        test_controllers(_silent);
        test_encryption(_silent);
        return true;
    }

    public static boolean test_relations(boolean _silent){

        return true;
    }

    public static boolean test_controllers(boolean _silent) throws SQLException {
        test_file_types_controller(_silent);
        return true;
    }

    // Testing file-types
    private static boolean test_file_types_controller(boolean _silent) throws SQLException {
        boolean result;
        result = FileTypesController.create(1, "foo");
        if(!result){
            System.out.println("everything okay");
            result = FileTypesController.create(0, "foo");
            if(result){
                System.out.println("everything okay");
                result = FileTypesController.delete(0, 0);
                if(result){
                    System.out.println("everything okay");
                    return true;
                } else {
                    System.out.println("something went wrong");
                }
            } else {
                System.out.println("something went wrong");
            }

        } else {
            System.out.println("something went wrong");
        }
        return false;
    }

    private static boolean test_music_files_controller(boolean _silent) {
        boolean result;
        return false;
    }

    public static boolean test_encryption(boolean _silent){


        return true;
    }
}
