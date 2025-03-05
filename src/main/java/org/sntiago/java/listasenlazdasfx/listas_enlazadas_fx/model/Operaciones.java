package org.sntiago.java.listasenlazdasfx.listas_enlazadas_fx.model;

public enum Operaciones {
    AGREGAR_NUMERO("Agregar numero a lista", 1),
    MOSTRAR_LISTAS("Mostrar Listas", 2),
    COMPARAR("Elementos Comunes", 3),
    NO_COMUNES("Mostrar Elementos no comunes", 4),
    DIFERENCIAS_DE_A_B("Mostrar diferencias de lista 1 a lista 2", 5),
    DIFERENCIAS_DE_B_A("Mostrar diferencias de lista 2 a lista 1", 6);


    private String texto;
    private int codigo;

    Operaciones(String texto, int codigo) {
        this.texto = texto;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return texto;
    }
}
