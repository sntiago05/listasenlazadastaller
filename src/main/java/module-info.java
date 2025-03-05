module org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx to javafx.fxml;
    exports org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx;
    exports org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.controller;
    opens org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.controller to javafx.fxml;
}