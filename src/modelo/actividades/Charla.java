package modelo.actividades;

import modelo.Estudiante;

import java.io.Serializable;

public class Charla extends Actividad implements Serializable{

    private String disertante;

    //--------------------CONSTRUCTORES--------------------

    public Charla(int id, String titulo, int cupoMaximo, String disertante) {
        super(id, titulo, cupoMaximo);
        this.disertante = disertante;
    }


    //--------------------METODOS--------------------

    @Override
    public double calcularCostoMateriales(){
    return 0;
    }
    @Override
    public String getTipo(){
        return "Charla";
    }

    public String getDisertante() {
        return disertante;
    }
}
