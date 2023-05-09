package com.code.controllers;

import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Algo_showController implements Initializable {

    @FXML
    private JFXTextArea outputTextArea;
    @FXML
    private GridPane gridshowdata;
    @FXML
    private ImageView previewShow;

    private final List<String> vgg16list = new ArrayList<>();
    private final List<String> densenetlist = new ArrayList<>();
    private final List<String> mobilenetlist = new ArrayList<>();
    public static ImageView mainimageview;
    @FXML
    private Tab imgPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mainimageview = previewShow;
        addData();
        switch (MainController.FLAG) {
            case "DATA":
                outputTextArea.setText(readOutputFile("DataSet Ref/data discribe"));
                imgPane.setDisable(true);
                break;
            case "VGG16":
                outputTextArea.setText(readOutputFile("VGG16/vgg16_output"));
                showData(gridshowdata, vgg16list);
                break;
            case "DENSENET169":
                outputTextArea.setText(readOutputFile("Densenet169 Model/densenet169_output"));
                showData(gridshowdata, densenetlist);
                break;
            case "MOBILENETV2":
                outputTextArea.setText(readOutputFile("MobileNet Model/mobilenet_output"));
                showData(gridshowdata, mobilenetlist);
                break;
        }
    }

    private void addData() {
//        vgg16
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_normal_person.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_pneumonia_person.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/visualization_1.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/visualization_2.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/visualization_3.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/visualization_4.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/visualization_5.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_model_accuracy.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_model_loss.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_confusion_matrix.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_correct_predict.png");
        vgg16list.add("src/com/code/python/script/VGG16/vgg16_output_img/vgg_wrong_predict.png");

//        densenet
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/visualization_1.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/visualization_2.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/traning_validation_plot.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/densenet_confusion_matrix.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_1.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_2.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_3.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_4.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_5.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_6.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_7.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_8.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_9.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/predicted_output_10.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/Correct prediction.png");
        densenetlist.add("src/com/code/python/script/Densenet169 Model/densenet_output_img/incorrect prediction.png");

//        mobilenet
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/visualization_1.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/visualization_2.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/training_validation_output.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/confusion_matrix_output.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_1.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_2.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_3.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_4.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_5.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_6.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_7.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_8.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_9.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/predicted_output_10.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/correct prediction.png");
        mobilenetlist.add("src/com/code/python/script/MobileNet Model/mobilenet_output_img/incorrect prediction.png");
    }

    public static String readOutputFile(String fileName) {
        String line = "";
        try {
            File file = new File("./src/com/code/python/script/" + fileName + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                line = line + "\n" + st;
            }
        } catch (IOException ex) {
            Logger.getLogger(Algo_showController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line;
    }

    private void showData(GridPane grid, List<String> list) {
        {
            int column = 0;
            int row = 0;

            try {
                grid.getChildren().clear();
                int count = 0;
                for (int i = 0; i < list.size(); i++) {
                    count++;
                    count++;
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/view/img_show.fxml"));
                    Pane pane = fxmlLoader.load();

                    Img_showController imgShow = fxmlLoader.getController();
                    imgShow.setXrayImages(list.get(i));
                    if (column == 2) {
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
    }
}
