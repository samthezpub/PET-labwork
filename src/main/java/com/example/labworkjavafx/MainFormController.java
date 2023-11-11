package com.example.labworkjavafx;




import java.io.FileReader;
import java.io.IOException;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class MainFormController extends Parent {

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

    private Parent root;
    private MyThread thread = null;
    private Stage graphicStage;

    private GraphicController graphicController;

    @FXML
    private Label pressure_display;

    @FXML
    private Label temperature_display;


    @FXML
    void about_clicked(ActionEvent event) {
        // тут должна быть форма, но я пока впихну диалоговое окно
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;

        try {
            model = reader.read(new FileReader("pom.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("О программе");
        alert.setHeaderText(null);

        alert.setContentText("Имя: " + model.getName() + "\n"
                + "Версия: " + model.getVersion() + "\n"
                + "Авторы: " + "Java-прогеры" + "\n");

        alert.setGraphic(new ImageView(this.getClass().getResource("/pictures/aboutlogo.png").toString()));

        alert.showAndWait();
    }

    @FXML
    void graphic_clicked(ActionEvent event) {
        graphicStage.show();
        graphicStage.setResizable(false);
    }

    private void initializeGraphicForm() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graphic.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        graphicController = fxmlLoader.getController();

        Scene scene = new Scene(root, 700, 500); // Создание сцены для дочерней формы
        Stage stage = new Stage();
        stage.setTitle("График");
        stage.setScene(scene);

        graphicStage = stage;

        graphicStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/pictures/aboutlogo.png")));
    }


    @FXML
    void manual_clicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manual.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Методичка");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);

            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/pictures/aboutlogo.png")));

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    class MyThread extends Thread {
        public void run() {
            try {
                for (int i = 0; i < temperatureSlider.getMax()-1; i++) {
                    if (temperatureSlider.getValue() >= temperatureSlider.getMax()){
                        Platform.runLater(() -> {
                            start_button.setText("Сначала");

                            start_button.setDisable(false);
                            stop_button.setDisable(true);

                            paramsSettings.setDisable(false);
                        });
                        this.interrupt();
                        return;
                    }
                    sleep(100);

                    int pressure = (int) Double.parseDouble(pressure_display.getText());
                    graphicController.addGraphicPoint((int) temperatureSlider.getValue(), pressure);
                    temperatureSlider.setValue(temperatureSlider.getValue() + 1);
                    changeTableValues();
                }
            } catch (InterruptedException e) {

            }

        }
    }


    @FXML
    void start_clicked(ActionEvent event) {

    }

    @FXML
    void stop_clicked(ActionEvent event) {

    }


    @FXML
    void startButton_clicked(ActionEvent event) {
        start_button.setDisable(true);
        stop_button.setDisable(false);

        if (temperatureSlider.getValue() == temperatureSlider.getMax()){
            temperatureSlider.setValue(0);
            graphicController.clearGraphic();
        }

        start_button.setText("Продолжить");

        paramsSettings.setDisable(true);

        thread = new MyThread();
        thread.start();
    }

    @FXML
    void stopButton_clicked(ActionEvent event) {
        start_button.setDisable(false);
        stop_button.setDisable(true);

        paramsSettings.setDisable(false);

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
        stop_button.setDisable(true); // отключаем для избежания лишних ошибок
        paramsMenuItem.setOnAction(this::showParams);

        start_button.setDisable(false);
        start_button.setOnAction(this::startButton_clicked);
        stop_button.setOnAction(this::stopButton_clicked);

        initialiseTemperatureSlider();
        initializeChoiseboxes();
        initializeTable();
        initializeGraphicForm();

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
        variables.add(new MainVariablesData("Молярная масса (m)", experimentEntity.getMolarMass() ,"г/моль"));
        variables.add(new MainVariablesData("Газ", experimentEntity.getGasName(), gasTypeChoiseBox.getValue().getChemical()));
        variables.add(new MainVariablesData("Объем (V)", experimentEntity.getVolume(), "л"));
        variables.add(new MainVariablesData("Газовая константа (R)", experimentEntity.getGasConstantR(), "Дж/(Кг*моль)"));
        variables.add(new MainVariablesData("Масса (m)", experimentEntity.getWeight(), "кг"));
        variables.add(new MainVariablesData("Давление (P)", experimentEntity.getPressure(), "Па"));
        variables.add(new MainVariablesData("Температура (T)", String.valueOf(experimentEntity.getTemperature()), "К"));

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



        /*
            Поток для изменения label на аппарате
        */
        Thread backgroundThread = new Thread(() -> {
            Platform.runLater(() -> {
                pressure_display.setText(experimentEntity.getPressure());
                temperature_display.setText(experimentEntity.getTemperature());
            });
        });

        backgroundThread.start();
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
