package sample.utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author rnimmagadda
 */
public class JavaFxUtils {
    public static FXMLLoader getFxmlLoader(String fxmlFile) throws MalformedURLException {
        URL url = new File("src/main/java/sample/fxmls/" + fxmlFile).toURL();
        return new FXMLLoader(url);
    }


}
