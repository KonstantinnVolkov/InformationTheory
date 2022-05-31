module com.example.lr_4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.lr_4 to javafx.fxml;
    exports com.example.lr_4;
    exports com.example.lr_4.utils;
    opens com.example.lr_4.utils to javafx.fxml;
    exports com.example.lr_4.utils.keys;
    opens com.example.lr_4.utils.keys to javafx.fxml;
}