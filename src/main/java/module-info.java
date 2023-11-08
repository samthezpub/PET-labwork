module com.example.labworkjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires maven.model;
    requires lombok;

    opens com.example.labworkjavafx to javafx.fxml;
    opens com.example.labworkjavafx.Models to javafx.base;
    exports com.example.labworkjavafx;

    requires java.desktop;
}