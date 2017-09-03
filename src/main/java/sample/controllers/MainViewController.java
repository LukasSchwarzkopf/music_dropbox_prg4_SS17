package sample.controllers;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author rnimmagadda
 */
public class MainViewController extends AnchorPane implements Initializable {

    private final static Controller screensControler = new Controller();

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu scheduleJobMenu;
    @FXML
    private MenuItem todaysScheduleMenu;
    @FXML
    private MenuItem scheduleJobEventsSearchMenu;
    @FXML
    private MenuItem searchScheduleJobsMenu;
    @FXML
    private MenuItem newScheduleJob;
    @FXML
    private Menu batchOrderMenu;
    @FXML
    private MenuItem searchBatchOrdersMenu;
    @FXML
    private Menu helpMenu;
    @FXML
    private MenuItem aboutMenu;
    @FXML
    private AnchorPane mainAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMainView("unsigned/users/signin.fxml");
    }

    public void loadMainView(String fxmlFile) {
        System.out.println(getChildren().size());
        Node node = screensControler.loadScreen(fxmlFile);
        if (node != null) {
            if (!mainAnchorPane.getChildren().isEmpty()) {
                mainAnchorPane.getChildren().remove(0);
            }
            mainAnchorPane.getChildren().add(node);
        }
    }

}
