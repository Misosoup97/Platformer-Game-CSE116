module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires junit;
    requires java.desktop;
    requires org.apache.commons.lang3;
    
    exports app.tests to junit;

    opens app.gameengine.graphics to javafx.fxml;
    exports app.gameengine.graphics;
    exports app;
    opens app to javafx.fxml;
}
