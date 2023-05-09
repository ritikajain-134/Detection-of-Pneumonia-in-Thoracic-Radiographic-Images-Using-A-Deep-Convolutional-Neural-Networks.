package com.code.module;

import com.code.controllers.Algo_showController;
import com.code.controllers.PredictionController;
import static com.code.module.Main.dataset;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Node main;
    public static Node dataset_show;
    public static Node algo_show;
    public static Node prediction;
    public static Stage main_stage;
    public static List<String> dataset = new ArrayList<>();
    public static final String Path = "D:/dataset/input/chest-xray-pneumonia/chest_xray/chest_xray/";

    private void loadNode() {
        try {
            dataset_show = (Node) FXMLLoader.load(getClass().getResource("/com/view/dataset_show.fxml"));
            prediction = (Node) FXMLLoader.load(getClass().getResource("/com/view/prediction.fxml"));
            main = (Node) FXMLLoader.load(getClass().getResource("/com/view/main.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        main_stage = stage;
        loadNode();
        Parent root = FXMLLoader.load(getClass().getResource("/com/view/initial.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/com/style/main.css");
        scene.getStylesheets().add("/com/style/style_second.css");

        main_stage.initStyle(StageStyle.TRANSPARENT);
        main_stage.initStyle(StageStyle.UNDECORATED);
        main_stage.setMaximized(true);
        main_stage.setScene(scene);
        main_stage.show();
    }

    public static void main(String[] args) {
        DataoLoad data = new DataoLoad();
        data.start();
        launch(args);
    }

//    private void loadDataset() {
//
//    }

    public static List cmdRun(String File_name) throws IOException {
        System.out.println(File_name);
        List<String> words_list = new ArrayList<>();
        ProcessBuilder builder = new ProcessBuilder("python", System.getProperty("user.dir") + "./src/com/code/python/script/" + File_name + ".py");
        Process process = builder.start();

        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String[] words = null;
        String line;
        int count = 0;
        while ((line = error.readLine()) != null) {
            System.out.println(line);
        }
        while ((line = output.readLine()) != null) {
            words_list.add(line);
        }
        return words_list;
    }

}

class DataoLoad extends Thread {

    public void run() {
        String str = Algo_showController.readOutputFile("DataSet Ref/dataset dir");
        str = str.replace("\n", "");
        String[] arrOfStr = str.split(",");

        for (String a : arrOfStr) {
            Main.dataset.add(a);
        }
    }
}
