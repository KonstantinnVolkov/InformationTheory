module com.example.lb_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.lb_1 to javafx.fxml;
    exports com.example.lb_1;
}