package com.example.labworkjavafx;

import java.net.URL;
import java.nio.channels.Selector;
import java.util.ResourceBundle;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;

public class GraphicController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<String, Number> graphic;

    public void addGraphicPoint(int temperature, int pressure){
        XYChart.Series<String , Number> series = graphic.getData().get(0);
        series.getData().add(new XYChart.Data<>(String.valueOf(pressure) + "P", temperature));
    }


    @FXML
    void initialize() {
        assert graphic != null : "fx:id=\"graphic\" was not injected: check your FXML file 'Untitled'.";

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("График изменений");


        graphic.getData().add(series);
    }

}
