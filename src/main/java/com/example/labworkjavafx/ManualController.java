package com.example.labworkjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ManualController {
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
}
