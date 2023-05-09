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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class X_ray_imgController implements Initializable {

    @FXML
    private ImageView xray_img;
    public static ImageView staticimageview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setXrayImages(String PATH) {
        try {
            xray_img.setImage(new Image(new FileInputStream(PATH), 240, 330, false, false));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(X_ray_imgController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initialize();

    }

    public void initialize() {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(xray_img.getFitWidth(), xray_img.getFitHeight());

        clip.setArcWidth(50);
        clip.setArcHeight(50);
        xray_img.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = xray_img.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        xray_img.setClip(null);

        // apply a shadow effect.
        xray_img.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        xray_img.setImage(image);
    }
}
