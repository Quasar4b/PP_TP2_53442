package modelo;

import java.io.Serializable;

public class Sala implements Serializable {
    private int id;
    private String nombre;
    //--------------------CONSTRUCTORES--------------------
    public Sala(int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }
    // --------------------LISTAS--------------------
    //--------------------METODOS--------------------
    //--------------------GETTERS--------------------

    public String getNombre() {
        return nombre;
    }
    public int getId() {
        return id;
    }
    //--------------------SETTERS-------------------
}



