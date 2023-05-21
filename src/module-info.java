module PenguinPlundgePortA2.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires reactor.core;
    requires org.slf4j;
    requires java.sql;
    requires java.desktop;
    requires javafx.media;


    opens com.twopercent.main to javafx.fxml;
    exports com.twopercent.main;
    opens com.valgriz.screen to javafx.fxml;
    exports com.valgriz.screen;

}
