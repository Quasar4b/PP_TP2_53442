package modelo.actividades;

import modelo.Estudiante;
import modelo.certificación.Certificable;

import java.io.Serializable;

public class Curso extends Actividad implements Serializable, Certificable {

    private int nivel;

    //--------------------CONSTRUCTORES--------------------

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    //--------------------METODOS--------------------

    @Override
    public double calcularCostoMateriales() {
            if (nivel == 1) {
                return 3000;
            } else if (nivel == 2) {
                return 4000;
            } else if (nivel == 3) {
                return 5000;
            } else System.out.println("Error, el nivel del curso debe estar entre 1 y 3");
            return 0;
    }
    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return ENTIDAD_EMISORA + " Certifica al estudiante " + estudiante.getNombre() + " de legajo "+ estudiante.getLegajo() + " por el Curso " + this.getTitulo() + " de nivel " +  nivel;
    }
}
