package com.example.labworkjavafx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.labworkjavafx.Models.AboutData;
import com.example.labworkjavafx.Models.MainVariablesData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<MainVariablesData> variablesTable;

    @FXML
    void about_clicked(ActionEvent event) {

    }

    @FXML
    void graphic_clicked(ActionEvent event) {

    }

    @FXML
    void manual_clicked(ActionEvent event) {

    }

    @FXML
    void params_clicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
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

        variablesTable.getColumns().addAll(nameColumn,valueColumn,si);

        ArrayList<MainVariablesData> variables = new ArrayList<>(10);
        variables.add(new MainVariablesData("m", "г/моль"));
        variables.add(new MainVariablesData("Газ", ""));
        variables.add(new MainVariablesData("Объем", "л"));
        variables.add(new MainVariablesData("Универсальная газовая константа", "Дж/(Кг*моль)"));
        variables.add(new MainVariablesData("Масса", "кг"));
        variables.add(new MainVariablesData("Давление", "Па"));
        variables.add(new MainVariablesData("Температура", "К"));

        ObservableList<MainVariablesData> mainVariablesData = FXCollections.observableArrayList(variables);
        variablesTable.setItems(mainVariablesData);
    }

}
