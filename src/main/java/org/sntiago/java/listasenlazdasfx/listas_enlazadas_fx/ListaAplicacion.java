package org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaAplicacion extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListaAplicacion.class.getResource("lista-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lista Enlazada App");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}