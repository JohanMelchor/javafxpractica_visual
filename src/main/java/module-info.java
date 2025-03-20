module co.edu.uniquindio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires transitive javafx.graphics;

    opens co.edu.uniquindio to javafx.fxml;
    exports co.edu.uniquindio;
    exports co.edu.uniquindio.controller;
    opens co.edu.uniquindio.controller to javafx.fxml;
}
