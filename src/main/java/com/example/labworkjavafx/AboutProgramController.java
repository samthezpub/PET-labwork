package com.example.labworkjavafx;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.labworkjavafx.Models.AboutData;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import java.io.FileReader;

public class AboutProgramController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AboutData> about_table;

    @FXML
    void initialize() {
        assert about_table != null : "fx:id=\"about_table\" was not injected: check your FXML file 'Untitled'.";


        TableColumn<AboutData, String> nameColumn = new TableColumn<>("Переменная");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("variableName")); // "name" - это имя поля в классе MyData

        TableColumn<AboutData, String> valueColumn = new TableColumn<>("Значение");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setPrefWidth(415);

        about_table.getColumns().addAll(nameColumn, valueColumn);

        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));

            String groupId = model.getGroupId();
            String artifactId = model.getArtifactId();
            String version = model.getVersion();
            String name = model.getName();


            List<AboutData> aboutDataList = new ArrayList<>();
            aboutDataList.add(new AboutData("Версия", version));
            aboutDataList.add(new AboutData("Организация", "Java-прогеры"));
            aboutDataList.add(new AboutData("Имя", name));
            ObservableList<AboutData> list = FXCollections.observableArrayList(aboutDataList);

            about_table.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
