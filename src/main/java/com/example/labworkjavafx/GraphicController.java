package com.example.labworkjavafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class GraphicController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button refresh_button;

    @FXML
    void abzabza(ActionEvent event) {
        refresh_button.setText("abzabza");


    }


    @FXML
    void initialize() {
        assert refresh_button != null : "fx:id=\"refresh_button\" was not injected: check your FXML file 'graphic.fxml'.";

        refresh_button.setOnAction(this::abzabza);
    }

}
