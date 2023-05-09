package com.code.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Img_showController implements Initializable {

    @FXML
    private ImageView imgView;
    String path;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setXrayImages(String PATH) {
        path = PATH;
        try {
            imgView.setImage(new Image(new FileInputStream(PATH), 260, 370, false, false));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(X_ray_imgController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initialize();

    }

    public void initialize() {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(imgView.getFitWidth(), imgView.getFitHeight());

        clip.setArcWidth(10);
        clip.setArcHeight(10);
        imgView.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imgView.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imgView.setClip(null);

        // apply a shadow effect.
        imgView.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        imgView.setImage(image);
    }

    @FXML
    private void onClicked(MouseEvent event) {
        try {
            Algo_showController.mainimageview.setImage(new Image(new FileInputStream(path), 700, 740, false, false));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Img_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
