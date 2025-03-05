package org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.Direccion;
import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.ListaEnlazada;
import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.Nodo;
import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.Operaciones;

import java.util.List;

public class ListaViewController {
    @FXML
    private Canvas canvas;
    @FXML
    private TextField elementoTxt;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ComboBox<String> comboLista;
    @FXML
    private ComboBox<Direccion> comboDireccion;
    @FXML
    private ComboBox<Operaciones> comboOperaciones;

    private ListaEnlazada<Integer> lista1 = new ListaEnlazada<>();
    private ListaEnlazada<Integer> lista2 = new ListaEnlazada<>();
    private int contador = 1;

    @FXML
    public void initialize() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        comboDireccion.getItems().addAll(Direccion.values());
        comboLista.getItems().addAll(List.of("Lista 1", "Lista 2"));
        comboOperaciones.getItems().addAll(Operaciones.values());
    }

    @FXML
    protected void ejecutarOp() {
        Operaciones operacionSeleccionada = comboOperaciones.getValue();
        if (operacionSeleccionada == null) return;

        switch (operacionSeleccionada) {
            case MOSTRAR_LISTAS:
                dibujarAmbasListas();
                break;
            case COMPARAR:
                dibujarLista(comparar(lista1, lista2));
                break;
            case NO_COMUNES:
                dibujarLista(noComunes(lista1, lista2));
                break;
            case DIFERENCIAS_DE_A_B:
                dibujarLista(diferenciasEntreListas(lista1, lista2, true));
                break;
            case DIFERENCIAS_DE_B_A:
                dibujarLista(diferenciasEntreListas(lista2, lista1, true));
                break;
        }
    }

    private void dibujarLista(ListaEnlazada<Integer> listaDibujar) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        dibujarListaConPosicion(listaDibujar, 50, canvas.getHeight() / 2, Color.BLUE);
    }

    private void dibujarAmbasListas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        dibujarListaConPosicion(lista1, 50, canvas.getHeight() / 3, Color.BLUE);
        dibujarListaConPosicion(lista2, 50, 2 * canvas.getHeight() / 3, Color.GREEN);
    }

    private void dibujarListaConPosicion(ListaEnlazada<Integer> listaDibujar, double x, double y, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Nodo<Integer> auxiliar = listaDibujar.getPtr();

        while (auxiliar != null) {
            int valor = auxiliar.getComponente();
            gc.setFill(color);
            gc.fillOval(x, y - 30, 60, 60);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(x, y - 30, 60, 60);
            gc.setFill(Color.WHITE);
            gc.fillText(String.valueOf(valor), x + 25, y + 5);
            if (auxiliar.siguiente != null) {
                gc.strokeLine(x + 60, y, x + 100, y);
                gc.strokeLine(x + 90, y - 5, x + 100, y);
                gc.strokeLine(x + 90, y + 5, x + 100, y);
            }
            x += 100;
            auxiliar = auxiliar.siguiente;
        }
    }

    @FXML
    protected void agregar() {
        String listaSeleccionada = comboLista.getValue();
        if (listaSeleccionada == null || elementoTxt.getText().isEmpty()) return;
        int valor = Integer.parseInt(elementoTxt.getText());
        Direccion direccion = comboDireccion.getValue();
        if (listaSeleccionada.equals("Lista 1")) {
            lista1.ingresar(new Nodo<>(valor), direccion);
        } else {
            lista2.ingresar(new Nodo<>(valor), direccion);
        }

        dibujar();
        ajustarCanvas();
    }

    @FXML
    protected void limpiarLista() {
        lista1.limpiar();
        lista2.limpiar();
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.setWidth(0);
    }

    private void dibujar() {
        dibujarAmbasListas();
    }

    private void ajustarCanvas() {
        double nuevoAncho = contador * 150;
        canvas.setWidth(nuevoAncho);
        dibujar();
        contador++;
    }
    private static ListaEnlazada<Integer> comparar(ListaEnlazada<Integer> lista1, ListaEnlazada<Integer> lista2) {
        ListaEnlazada<Integer> listaResultado = new ListaEnlazada<>();
        Nodo<Integer> aux = lista1.getPtr();
        while (aux != null) {
            if (lista2.buscarElemento(aux.getComponente()) != null) {
                listaResultado.ingresar(new Nodo<>(aux.getComponente()), Direccion.DERECHA);
            }
            aux = aux.siguiente;
        }
        return listaResultado;
    }

    private static ListaEnlazada<Integer> noComunes(ListaEnlazada<Integer> lista1, ListaEnlazada<Integer> lista2) {
        ListaEnlazada<Integer> listaResultado = diferenciasEntreListas(lista1, lista2, true);
        Nodo<Integer> aux = diferenciasEntreListas(lista2, lista1, true).getPtr();
        while (aux != null) {
            listaResultado.ingresar(new Nodo<>(aux.getComponente()), Direccion.DERECHA);
            aux = aux.siguiente;
        }
        return listaResultado;
    }

    private static ListaEnlazada<Integer> diferenciasEntreListas(ListaEnlazada<Integer> lista1, ListaEnlazada<Integer> lista2, boolean deAaB) {
        Nodo<Integer> aux = lista1.getPtr();
        ListaEnlazada<Integer> listaResultado = new ListaEnlazada<>();
        while (aux != null) {
            if (lista2.buscarElemento(aux.getComponente()) == null) {
                listaResultado.ingresar(new Nodo<>(aux.getComponente()), Direccion.DERECHA);
            }
            aux = aux.siguiente;
        }
        return listaResultado;
    }
}
