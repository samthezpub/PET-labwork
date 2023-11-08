module com.example.labworkjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.labworkjavafx to javafx.fxml;
    exports com.example.labworkjavafx;

    requires java.desktop;
}