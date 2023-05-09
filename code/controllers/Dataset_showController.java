package com.code.controllers;

import com.code.module.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Dataset_showController implements Initializable {

    @FXML
    private GridPane main_grid;

    private void setdatasetImg(GridPane grid) {
        int column = 0;
        int row = 0;

        try {
            grid.getChildren().clear();
            int count = 0;
            for (int i = 0; i < Main.dataset.size(); i++) {
                count++;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/view/x-ray_img.fxml"));
                Pane pane = fxmlLoader.load();

                X_ray_imgController xray = fxmlLoader.getController();
                xray.setXrayImages(Main.Path + Main.dataset.get(i));
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(pane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(pane, new javafx.geometry.Insets(3));
            }
        } catch (IOException e) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setdatasetImg(main_grid);
    }

}
