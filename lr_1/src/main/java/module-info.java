module com.example.lr_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.lr_1 to javafx.fxml;
    exports com.example.lr_1;
    exports com.example.lr_1.parser;
    opens com.example.lr_1.parser to javafx.fxml;
}