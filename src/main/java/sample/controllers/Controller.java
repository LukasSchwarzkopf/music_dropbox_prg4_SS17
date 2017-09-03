package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import sample.Main;
import sample.utils.JavaFxUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Controller {
    private final HashMap<String, Node> screens = new HashMap<>();

    private void addScreen(String fxml, Node node) {
        screens.put(fxml, node);
    }

    public Node getScreen(String name) {
        return screens.get(name);
    }

    //Loads the fxml file, add the screen to the screens collection and
    //finally injects the screenPane to the controller.
    public Node loadScreen(String fxml) {
        System.out.println("Loading " + fxml);
        try {
            Node node = screens.get(fxml);
            if (node == null) {
                System.out.println("Re-Loading Fxml File" + fxml);
                FXMLLoader myLoader = JavaFxUtils.getFxmlLoader(fxml);
                node = (Parent) myLoader.load();
                // AbstractController abstractControler = ((AbstractController) myLoader.getController());
                // abstractControler.setScreenController(this);
                addScreen(fxml, node);
            }
            return node;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
