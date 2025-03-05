package org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.utils;

import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.Direccion;
import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.ListaEnlazada;
import org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model.Nodo;

public class Utils {
    public static ListaEnlazada<Integer> comparar(ListaEnlazada<Integer> lista1, ListaEnlazada<Integer> lista2) {
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

    public static ListaEnlazada<Integer> noComunes(ListaEnlazada<Integer> lista1, ListaEnlazada<Integer> lista2) {
        ListaEnlazada<Integer> listaResultado = diferenciasEntreListas(lista1, lista2, true);
        Nodo<Integer> aux = diferenciasEntreListas(lista2, lista1, true).getPtr();
        while (aux != null) {
            listaResultado.ingresar(new Nodo<>(aux.getComponente()), Direccion.DERECHA);
            aux = aux.siguiente;
        }
        return listaResultado;
    }

    public static ListaEnlazada<Integer> diferenciasEntreListas(ListaEnlazada<Integer> lista1, ListaEnlazada<Integer> lista2, boolean deAaB) {
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
