package com.example.labworkjavafx;

import java.net.URL;
import java.nio.channels.Selector;
import java.util.ResourceBundle;
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

    @FXML
    private Slider temperatureSlider;

    public void setTemperatureSlider(Slider temperatureSlider) {
        this.temperatureSlider = temperatureSlider;
    }

    void addGraphicPoint(){
        temperatureSlider.getValue();

        XYChart.Series<String, Number> series = graphic.getData().get(0);
        series.setName("График изменений");

        series.getData().add(new XYChart.Data<>("temp"+temperatureSlider.getValue(), 5.0));
    }

    @FXML
    void initialize() {
        assert graphic != null : "fx:id=\"graphic\" was not injected: check your FXML file 'Untitled'.";

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("График изменений");

        series.getData().add(new XYChart.Data<>("temp", 2.0));
        series.getData().add(new XYChart.Data<>("Category 2", 3.0));
        series.getData().add(new XYChart.Data<>("Category 3", 5.0));



        graphic.getData().add(series);
    }

}
