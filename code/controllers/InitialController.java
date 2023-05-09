
package com.code.controllers;

import com.code.module.Main;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class InitialController implements Initializable {
    
    @FXML
    private JFXButton continueButton;
    public static HBox staticHbox;
    public static AnchorPane staticwelcompane;
    @FXML
    private MediaView mv;
    
    static File file = new File("src/com/img/video/pexels-kilian-figueras-torras-4096307-1920x1080-25fps.mp4");
    static URI p = file.toURI();
    static Media m = new Media(p.toString());
    static MediaPlayer mp = new MediaPlayer(m);
    @FXML
    private Circle onPluseClickButton;
    @FXML
    private AnchorPane leftpane;
    @FXML
    private AnchorPane rightpane;
    
    public static AnchorPane leftmainpane;
    public static AnchorPane rightmainpane;
    @FXML
    private Pane dreambubble;
    @FXML
    private VBox menuvbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leftmainpane = leftpane;
        rightmainpane = rightpane;
        mv.setMediaPlayer(mp);
        mp.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
        mp.play();
        menuvbox.setVisible(false);
        dreambubble.setVisible(false);
        leftmainpane.getChildren().clear();
        leftmainpane.getChildren().add(Main.main);
    }
    
    @FXML
    private void onCloseClick(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void onContinueButtonClick(ActionEvent event) {
        translateTransition(1930, 1930);
    }
    
    public static void translateTransition(int leftX, int rightX) {
        TranslateTransition lefttranslation = new TranslateTransition();
        lefttranslation.setDuration(Duration.millis(2000));
        
        TranslateTransition righttranslation = new TranslateTransition();
        righttranslation.setDuration(Duration.millis(2000));
        
        lefttranslation.setNode(leftmainpane);
        righttranslation.setNode(rightmainpane);
        
        lefttranslation.setByX(leftX);
        lefttranslation.play();
        
        righttranslation.setByX(rightX);
        righttranslation.play();
        
    }
    
    @FXML
    private void mouseExit(MouseEvent event) {
        dreambubble.setVisible(false);
    }
    
    @FXML
    private void mouseEntered(MouseEvent event) {
        dreambubble.setVisible(true);
    }
    
    @FXML
    private void onmouseExitmenu(MouseEvent event) {
        menuvbox.setVisible(false);
    }
    
    @FXML
    private void onmouseEnteredmenu(MouseEvent event) {
        menuvbox.setVisible(true);
    }
    
}
