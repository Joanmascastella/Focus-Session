module com.mas.joan.focussession {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mas.joan.focussession to javafx.fxml;
    exports com.mas.joan.focussession;
    exports com.mas.joan.focussession.Controller;
    opens com.mas.joan.focussession.Controller to javafx.fxml;
}