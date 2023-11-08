package com.example.labworkjavafx;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Desktop;

public class MainFormController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public void about_clicked(ActionEvent event) {

    }

    @FXML
    public void graphic_clicked(ActionEvent event) {

    }

    @FXML
    public void manual_clicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manual.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Методичка");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Невозможно создать новое окно");
        }
    }

    @FXML
    void openLink1(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.chem.msu.su/rus/teaching/realgases/chap1%283%29.html?ysclid=lon8u593gk566547179"));
    }

    @FXML
    void openLink2(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.chem-astu.ru/chair/study/physics-part1/?p=198"));
    }

    @FXML
    void openLink3(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://portal.tpu.ru/SHARED/e/ELENALIS/rabota/Tab4/Lk21.pdf"));
    }

    @FXML
    public void params_clicked(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
