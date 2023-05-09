
package com.code.controllers;

import com.code.module.Main;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.stage.FileChooser;


public class PredictionController implements Initializable {

    @FXML
    private JFXButton filechooseButton;
    private File selectedFile = null;
    @FXML
    private ImageView xray_imgBox;
    @FXML
    private JFXButton predictButton;
    Media audio;
    MediaPlayer mediaPlayer;
    @FXML
    private Label predictionFlagimageview;
    @FXML
    private Label prediction_label;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileChooser();
        xray_imgBox.setY(0);
        xray_imgBox.setX(0);

    }

    private void fileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpeg)", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG);
        fileChooser.setInitialDirectory(new File("D:/dataset/input/chest-xray-pneumonia/chest_xray/chest_xray/"));
        predictButton.setVisible(false);
        filechooseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    selectedFile = fileChooser.showOpenDialog(Main.main_stage);
                    xray_imgBox.setImage(new Image(new FileInputStream(selectedFile.getPath()), 393, 462, false, false));
                    Rectangle clip = new Rectangle(
                            xray_imgBox.getFitWidth(), xray_imgBox.getFitHeight()
                    );
                    clip.setArcWidth(60);
                    clip.setArcHeight(60);
                    xray_imgBox.setClip(clip);
                    SnapshotParameters parameters = new SnapshotParameters();
                    parameters.setFill(Color.TRANSPARENT);
                    WritableImage image = xray_imgBox.snapshot(parameters, null);

                    xray_imgBox.setClip(null);
                    xray_imgBox.setImage(image);
                    predictButton.setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PredictionController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    private Node getClip(ImageView imageView, double radiusTop, double radiusBot) {
        Path clip;

        double height = imageView.getFitHeight();
        double width = imageView.getFitWidth();
        double radius1 = height * radiusTop;
        double radius2 = height * radiusBot;
        clip = new Path(new MoveTo(0, radius1), new ArcTo(radius1, radius1, 0, radius1, 0, false, true),
                new HLineTo(width),
                new VLineTo(height - radius2),
                new ArcTo(radius2, radius2, 0, width - radius2, height, false, true),
                new HLineTo(0));

        clip.setFill(Color.ALICEBLUE);

        return clip;

    }

    private Node getClip(ImageView imageView, double topLeft, double topRight, double bottomLeft, double bottomRight) {
        Path clip;

        double height = imageView.getFitHeight();
        double width = imageView.getFitWidth();
        double radius1 = height * topLeft;
        double radius2 = height * topRight;
        double radius3 = height * bottomLeft;
        double radius4 = height * bottomRight;

        clip = new Path(new MoveTo(0, radius1),
                new ArcTo(radius1, radius1, 0, radius1, 0, false, true),
                new HLineTo(width - radius2),
                new ArcTo(radius2, radius2, 0, width, radius2, false, true),
                new VLineTo(height - radius4),
                new ArcTo(radius4, radius4, 0, width - radius4, height, false, true),
                new HLineTo(radius3),
                new ArcTo(radius3, radius3, 0, 0, height - radius3, false, true));

        clip.setFill(Color.ALICEBLUE);

        return clip;

    }

    private void createFile() {
        try {
            File myObj = new File("./src/com/code/python/script/Prediction/prediction_test.py");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeFile() {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter("./src/com/code/python/script/Prediction/prediction_test.py");
            myWriter.write("import numpy as np\n"
                    + "import pandas as pd\n"
                    + "import matplotlib\n"
                    + "import matplotlib.pyplot as plt\n"
                    + "import cv2, os, random\n"
                    + "import plotly\n"
                    + "import plotly.graph_objs as go\n"
                    + "import plotly.express as px\n"
                    + "from plotly.offline import init_notebook_mode, plot, iplot\n"
                    + "\n"
                    + "#____________________________________________________________\n"
                    + "\n"
                    + "import glob\n"
                    + "import tensorflow\n"
                    + "from tensorflow.keras.preprocessing.image import array_to_img, img_to_array, load_img\n"
                    + "from tensorflow.keras.layers import Conv2D, Flatten, MaxPooling2D, Dense, Dropout, BatchNormalization\n"
                    + "from tensorflow.keras.models import Sequential\n"
                    + "from mlxtend.plotting import plot_confusion_matrix\n"
                    + "from tensorflow.keras.preprocessing import image\n"
                    + "from tensorflow.keras.preprocessing.image import ImageDataGenerator\n"
                    + "from sklearn.metrics import classification_report, confusion_matrix, accuracy_score\n"
                    + "from tensorflow.keras.callbacks import ReduceLROnPlateau\n"
                    + "from tensorflow.keras.applications.vgg16 import VGG16\n"
                    + "from sklearn.model_selection import train_test_split\n"
                    + "\n"
                    + "#____________________________________________________________\n"
                    + "\n"
                    + "from tqdm.notebook import tqdm\n"
                    + "from termcolor import colored\n"
                    + "import albumentations as A\n"
                    + "\n"
                    + "#____________________________________________________________\n"
                    + "\n"
                    + "from warnings import filterwarnings\n"
                    + "filterwarnings(\"ignore\")\n"
                    + "\n"
                    + "from sklearn import set_config\n"
                    + "set_config(print_changed_only = False)\n"
                    + "directory = \"D:/dataset/input/chest-xray-pneumonia/chest_xray/\"\n"
                    + "#____________________________________________________________\n"
                    + "\n"
                    + "model_loaded = tensorflow.keras.models.load_model(\"./src/com/code/python/script/Prediction/stacked_model.h5\")\n"
                    + "def image_prediction(new_image_path):\n"
                    + "    test_image = image.load_img(new_image_path, target_size = (224, 224))\n"
                    + "    test_image = image.img_to_array(test_image)\n"
                    + "    #test_image = np.reshape(test_image, (224, 224, 3))\n"
                    + "    test_image = np.expand_dims(test_image, axis = 0)\n"
                    + "    test_image = test_image / 255.0\n"
                    + "    prediction = model_loaded.predict(test_image)\n"
                    + "    test_image_for_plotting = image.load_img(new_image_path, target_size = (224, 224))\n"
                    + "    plt.imshow(test_image_for_plotting)\n"
                    + "    if(prediction[0] > 0.5):\n"
                    + "        statistic = prediction[0] * 100 \n"
                    + "        print(\"positive\")\n"
                    + "        print(\"This image is %.3f percent %s\"% (statistic, \"P N E U M O N I A\"))\n"
                    + "    else:\n"
                    + "        statistic = (1.0 - prediction[0]) * 100\n"
                    + "        print(\"negative\")\n"
                    + "        print(\"This image is %.3f percent %s\" % (statistic, \"N O R M A L\"))\n"
                    + "     \n"
                    + " \n"
                    + "# call and use the function\n"
                    + "image_prediction(\"" + selectedFile.getPath().replace("\\", "/") + "\")");
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(PredictionController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(PredictionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void onPredictButtonClick(ActionEvent event) {
        predictionFlagimageview.getStyleClass().add("predictio_inProcess");
        try {
            createFile();
            writeFile();
            List<String> predictionoutput = Main.cmdRun("Prediction/prediction_test");
            for (String line : predictionoutput) {
                if (line.equals("positive")) {
                    predictionFlagimageview.getStyleClass().clear();
                    predictionFlagimageview.getStyleClass().add("prediction_indicate_pneumonia");
                    audio = new Media(new File("src/com/img/music/pneumonia affected.mp3").toURI().toString());
                    prediction_label.setText("PNEUMONIA");
                    prediction_label.setStyle("-fx-text-fill : red;");
                    mediaPlayer = new MediaPlayer(audio);
                    mediaPlayer.play();

                } else if (line.equals("negative")) {
                    predictionFlagimageview.getStyleClass().clear();
                    predictionFlagimageview.getStyleClass().add("rediction_indicate_normal");
                    audio = new Media(new File("src/com/img/music/healthy person result.mp3").toURI().toString());
                    prediction_label.setText("Normal");
                    prediction_label.setStyle("-fx-text-fill : green;");
                    mediaPlayer = new MediaPlayer(audio);
                    mediaPlayer.play();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PredictionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

