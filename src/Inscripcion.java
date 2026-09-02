import java.time.LocalDate;

public class Inscripcion {
    private final Actividad actividad;
    private final Estudiante estudiante;
    private LocalDate fecha;
    private String estado;
    //--------------------CONSTRUCTORES--------------------
    public Inscripcion(Actividad actividad, Estudiante estudiante, LocalDate fecha, String estado) {
        this.actividad = actividad;
        this.estudiante = estudiante;
        this.fecha = fecha;
        this.estado = estado;
    }
    // --------------------LISTAS--------------------
    //--------------------METODOS--------------------
    //--------------------GETTERS--------------------
    public String getEstado() {
        return estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
    //--------------------SETTERS--------------------



