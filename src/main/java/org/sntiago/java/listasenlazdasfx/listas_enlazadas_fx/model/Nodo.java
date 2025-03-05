package org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model;

public class Nodo<T> {
    private T componente;
    public Nodo<T> siguiente;

    public Nodo() {
    }

    public Nodo(T componente) {
        this.componente = componente;
    }

    public T getComponente() {
        return componente;
    }

    public void setComponente(T componente) {
        this.componente = componente;
    }

    @Override
    public String toString() {
        return "Nodo = ( componente= " + this.componente + " )";
    }
}
