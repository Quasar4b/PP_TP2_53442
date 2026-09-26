package modelo.actividades;

import modelo.Estudiante;
import modelo.certificación.Certificable;

import java.io.Serializable;

public class Taller extends Actividad implements Serializable, Certificable {

    private boolean requiereNotebook;

    //--------------------CONSTRUCTORES--------------------

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    //--------------------METODOS--------------------

    @Override
    public double calcularCostoMateriales(){
        if(requiereNotebook) {
            return 5000;
        } else {
            return 2000;
        }
    }
    @Override
    public String getTipo(){
        return "Taller";
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ENTIDAD_EMISORA + "Certifica al estudiante " + estudiante.getNombre() + " de legajo "+ estudiante.getLegajo() + " por el Taller " + this.getTitulo();
    }
}
