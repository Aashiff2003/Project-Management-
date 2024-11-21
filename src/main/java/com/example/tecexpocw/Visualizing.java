package com.example.tecexpocw;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Visualizing implements Initializable {
    @FXML
    public BarChart<Number,String> chart;
    @FXML
    public CategoryAxis project;
    @FXML
    public NumberAxis points;
    @FXML
    public ImageView exit;

    ArrayList<String[]> projectPointList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectPointList = ProjectManager.getInstance().getProjectPointList();

        XYChart.Series<Number,String> series = new XYChart.Series<>();
        series.setName("Project Points");
        int count =0;

        int counter = 0;
        for (String[] projectPoint : projectPointList) {
            series.getData().add(new XYChart.Data<>(Integer.parseInt(projectPoint[2]),
                    "Project Id: " + projectPoint[0] + "\n Country: " +
                            projectPoint[1]));
            counter++;
            if (counter == 3) {
                break;
            }
        }


        chart.getData().addAll(series);


    }

    public void onExitButtonClick(MouseEvent mouseEvent) {
        ProjectManager.getInstance().saveProjectDetails();
        System.exit(0);
    }
}