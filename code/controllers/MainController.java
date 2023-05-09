package com.code.controllers;

import com.code.module.Main;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
    
    @FXML
    private VBox mainVbox;
    
    public static VBox staticVbox;
    @FXML
    private JFXButton dataButton;
    @FXML
    private JFXButton datashowButton;
    @FXML
    private JFXButton vggButton;
    @FXML
    private JFXButton densenetButton;
    @FXML
    private JFXButton mobilenetv2Button;
    @FXML
    private JFXButton predictionButton;
    public static String FLAG = "DATA";
    @FXML
    private JFXButton backButton;
    @FXML
    private Label label_show;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staticVbox = mainVbox;
        setRootClassNode("algo_show");
        label_show.setText(dataButton.getText());
    }
    
    private void onCloseClick(ActionEvent event) {
        System.exit(0);
    }
    
    private void setRootClassNode(String PATH) {
        try {
            MainController.staticVbox.getChildren().clear();
            MainController.staticVbox.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/com/view/" + PATH + ".fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onBackButtonClick(ActionEvent event) {
        InitialController.translateTransition(-1930, -1930);
        
    }
    
    @FXML
    private void onDataButoonClick(ActionEvent event) {
        FLAG = dataButton.getText();
        setRootClassNode("algo_show");
        label_show.setText(dataButton.getText());
    }
    
    @FXML
    private void onDataShowButtonClick(ActionEvent event) {
        FLAG = datashowButton.getText();
        MainController.staticVbox.getChildren().clear();
        MainController.staticVbox.getChildren().add(Main.dataset_show);
        label_show.setText(datashowButton.getText());
    }
    
    @FXML
    private void onVGG16Click(ActionEvent event) {
        FLAG = vggButton.getText();
        setRootClassNode("algo_show");
        label_show.setText(vggButton.getText());
        
    }
    
    @FXML
    private void onDensenetButtonClick(ActionEvent event) {
        FLAG = densenetButton.getText();
        setRootClassNode("algo_show");
        label_show.setText(densenetButton.getText());
        
    }
    
    @FXML
    private void onMobilenetButtonClick(ActionEvent event) {
        FLAG = mobilenetv2Button.getText();
        setRootClassNode("algo_show");
        label_show.setText(mobilenetv2Button.getText());
    }
    
    @FXML
    private void onPredictionButtonClick(ActionEvent event) {
        FLAG = predictionButton.getText();
        MainController.staticVbox.getChildren().clear();
        MainController.staticVbox.getChildren().add(Main.prediction);
        label_show.setText(predictionButton.getText());
    }
}
