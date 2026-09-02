import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


    //--------------------CONSTRUCTORES--------------------
public abstract class Actividad {
    protected int id;
    protected String titulo;
    protected int cupoMaximo;
    public static final int CUPO_MINIMO=5;
    //--------------------LISTAS--------------------

    private List<Inscripcion> inscripciones = new ArrayList<>();

    //--------------------METODOS--------------------

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
    }

    public Inscripcion inscribir(Estudiante estudiante) {
        Inscripcion inscripcion = new Inscripcion(this, estudiante, LocalDate.now(), "REGISTRADA");
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public final void mostrarInscripciones() {
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println(
                    "Estudiante: " + inscripcion.getEstudiante().getLegajo() + "-" + inscripcion.getEstudiante().getNombre()
            );
        }
    }
        public final void mostrarIdentificaciones(){
            System.out.println(
            "ID: " + id +
            " - Título: " + titulo +
            " - Tipo: " + getTipo() +
            " - Cupo Maximo: " + cupoMaximo);
        };
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();


    //--------------------GETTERS--------------------
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public int getCupoMinimo() {
        return CUPO_MINIMO;
    }

    //--------------------SETTERS--------------------
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

}
