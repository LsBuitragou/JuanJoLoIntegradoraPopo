module org.example.animations {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.animations to javafx.fxml;
    exports org.example.animations;
    exports org.example.animations.control;
    opens org.example.animations.control to javafx.fxml;
}