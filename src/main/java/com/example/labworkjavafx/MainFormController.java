package com.example.labworkjavafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.labworkjavafx.Enums.GasTypeEnum;
import com.example.labworkjavafx.Models.AboutData;
import com.example.labworkjavafx.Models.ExperimentEntity;
import com.example.labworkjavafx.Models.MainVariablesData;
import com.example.labworkjavafx.Utils.ExperimentMath;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button start_button;

    @FXML
    private Button stop_button;

    @FXML
    private ChoiceBox<GasTypeEnum> gasTypeChoiseBox;

    @FXML
    private ChoiceBox<Double> volumeChoiseBox;
    @FXML
    private ChoiceBox<String> roundChoiseBox;

    @FXML
    private Slider temperatureSlider;

    @FXML
    private AnchorPane paramsSettings;


    @FXML
    private CheckMenuItem paramsMenuItem;

    @FXML
    private TableView<MainVariablesData> variablesTable;

    private MyThread thread = null;

    @FXML
    void about_clicked(ActionEvent event) {

    }

    @FXML
    void graphic_clicked(ActionEvent event) {

    }

    @FXML
    void manual_clicked(ActionEvent event) {

    }

    class MyThread extends Thread {
        public void run() {
            try {
                for (int i = 0; i < temperatureSlider.getMax()-1; i++) {
                    if (temperatureSlider.getValue() >= 500){
                        Platform.runLater(() -> {
                            start_button.setDisable(false);
                            stop_button.setDisable(true);
                        });
                        this.interrupt();
                        return;
                    }
                    sleep(100);
                    temperatureSlider.setValue(temperatureSlider.getValue() + 1);
                    changeTableValues();

                }
            } catch (InterruptedException e) {

            }

        }
    }

    @FXML
    void startButton_clicked(ActionEvent event) {
        start_button.setDisable(true);
        stop_button.setDisable(false);

        thread = new MyThread();
        thread.start();
    }

    @FXML
    void stopButton_clicked(ActionEvent event) {
        start_button.setDisable(false);
        stop_button.setDisable(true);

        thread.interrupt();
    }

    @FXML
    void temperatureSlider_drag(MouseEvent event) {

    }


    @FXML
    void showParams(ActionEvent event) {
        paramsSettings.setVisible(paramsMenuItem.isSelected());
    }

    @FXML
    void initialize() {
        paramsSettings.setVisible(true);
        paramsMenuItem.setSelected(true);
        paramsMenuItem.setOnAction(this::showParams);

        start_button.setDisable(false);
        start_button.setOnAction(this::startButton_clicked);
        stop_button.setOnAction(this::stopButton_clicked);

        initialiseTemperatureSlider();
        initializeChoiseboxes();
        initializeTable();

    }


    /**
     * Этот метод отвечает за инициализацию всех ChoiseBox'ов на форме
     *
     * @see ChoiceBox
     */
    private void initializeChoiseboxes() {
        Random random = new Random();

        List<GasTypeEnum> gasTypesArray = new ArrayList<>();
        gasTypesArray.add(GasTypeEnum.Nitrogen);
        gasTypesArray.add(GasTypeEnum.Neon);
        gasTypesArray.add(GasTypeEnum.Oxygen);
        gasTypesArray.add(GasTypeEnum.Argon);
        gasTypesArray.add(GasTypeEnum.Fluorine);
        gasTypesArray.add(GasTypeEnum.CarbonOxide);
        gasTypesArray.add(GasTypeEnum.NitrogenOxide);

        ObservableList<GasTypeEnum> gasTypesObservable = FXCollections.observableList(gasTypesArray);
        gasTypeChoiseBox.setItems(gasTypesObservable);
        gasTypeChoiseBox.setValue(gasTypesArray.get(random.nextInt(0, gasTypesArray.size()))); // Рандомный выбор элемента каждый запуск приложения


        volumeChoiseBox.setItems(FXCollections.observableArrayList(0.1, 0.2, 0.3, 0.4, 0.5));
        volumeChoiseBox.setValue(volumeChoiseBox.getItems().get(random.nextInt(0, volumeChoiseBox.getItems().size())));

        roundChoiseBox.setItems(FXCollections.observableArrayList(
                "2 знака после запятой",
                "3 знака после запятой",
                "4 знака после запятой",
                "5 знаков после запятой",
                "без округления"
        ));

        roundChoiseBox.setValue(roundChoiseBox.getItems().get(random.nextInt(0, roundChoiseBox.getItems().size())));

        gasTypeChoiseBox.setOnAction(this::changeTableValues);
        volumeChoiseBox.setOnAction(this::changeTableValues);
        roundChoiseBox.setOnAction(this::changeTableValues);

    }


    private void changeTableValues(ActionEvent actionEvent) {

        ExperimentEntity experimentEntity = ExperimentMath.calculate(gasTypeChoiseBox.getValue(), volumeChoiseBox.getValue(), temperatureSlider.getValue(), roundChoiseBox.getValue());

        setTableValues(experimentEntity);
    }

    private void setTableValues(ExperimentEntity experimentEntity) {
        ArrayList<MainVariablesData> variables = new ArrayList<>(10);
        variables.add(new MainVariablesData("m", "г/моль"));
        variables.add(new MainVariablesData("Газ", experimentEntity.getGasName(), gasTypeChoiseBox.getValue().getChemical()));
        variables.add(new MainVariablesData("Объем", experimentEntity.getVolume(), "л"));
        variables.add(new MainVariablesData("Универсальная газовая константа", experimentEntity.getGasConstantR(), "Дж/(Кг*моль)"));
        variables.add(new MainVariablesData("Масса", experimentEntity.getWeight(), "кг"));
        variables.add(new MainVariablesData("Давление", experimentEntity.getPressure(), "Па"));
        variables.add(new MainVariablesData("Температура", String.valueOf(experimentEntity.getTemperature()), "К"));

        ObservableList<MainVariablesData> mainVariablesData = FXCollections.observableArrayList(variables);
        variablesTable.setItems(mainVariablesData);

    }

    private void changeTableValues(MouseEvent actionEvent) {

        ExperimentEntity experimentEntity = ExperimentMath.calculate(gasTypeChoiseBox.getValue(), volumeChoiseBox.getValue(), temperatureSlider.getValue(), roundChoiseBox.getValue());

        setTableValues(experimentEntity);
    }

    private void changeTableValues() {
        ExperimentEntity experimentEntity = ExperimentMath.calculate(gasTypeChoiseBox.getValue(), volumeChoiseBox.getValue(), temperatureSlider.getValue(), roundChoiseBox.getValue());

        setTableValues(experimentEntity);
    }

    private void initialiseTemperatureSlider() {
        temperatureSlider.setOnMouseDragged(this::changeTableValues);
    }

    /**
     * Этот метод отвечает за инициализацию таблицы на главной форме
     *
     * @see TableView
     */
    private void initializeTable() {
        assert variablesTable != null : "fx:id=\"variablesTable\" was not injected: check your FXML file 'Untitled'.";

        TableColumn<MainVariablesData, String> nameColumn = new TableColumn<>("Переменная");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("variable")); // "variable - это имя поля в классе MyData
        nameColumn.setResizable(false);
        nameColumn.setPrefWidth(175);

        TableColumn<MainVariablesData, String> valueColumn = new TableColumn<>("Значение");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setPrefWidth(100);
        valueColumn.setResizable(false);

        TableColumn<MainVariablesData, String> si = new TableColumn<>("СИ");
        si.setCellValueFactory(new PropertyValueFactory<>("si"));
        si.setPrefWidth(85);
        si.setResizable(false);

        variablesTable.getColumns().addAll(nameColumn, valueColumn, si);

        changeTableValues();

    }
}
