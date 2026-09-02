
import jdk.jfr.Frequency;

import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos=0;
    private Sala sala;

    //--------------------CONSTRUCTORES--------------------
    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
    }
    //CONSTRUCTOR COPIA DE EVENTO
    public EventoUniversitario(EventoUniversitario otro){
        this.id = otro.id +  "-COPIA";
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        cantidadEventos++;
    }
    // --------------------LISTAS--------------------

    private List<Actividad> actividades= new ArrayList<>();
    public List<Actividad> getActividades() {
        return this.actividades;
    }
    //--------------------METODOS--------------------
    public void mostrarDatos(){
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Costo Base: " + calcularCostoEstimado());
        if (sala != null) {
            System.out.println("Sala: " + sala.getNombre());
        } else {
            System.out.println("Sala: Sin asignar");
        }
        System.out.println("--------Actividades--------");

        for (Actividad actividad : actividades) {
            actividad.mostrarIdentificaciones();
            actividad.mostrarInscripciones();
        }
    }
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupoMaximo, String tipo, boolean requiereNotebook) {
        if (tipo.equals("Taller")) {
            Taller taller = new Taller(id, titulo, cupoMaximo, requiereNotebook);
            actividades.add(taller);
        }
    }

    public void crearActividad(int id, String titulo, int cupoMaximo, String tipo, String disertante) {
        if (tipo.equals("Charla")) {
            Charla charla = new Charla(id, titulo, cupoMaximo, disertante);
            actividades.add(charla);
        }
    }

    public double calcularCostoEstimado(){
        if (gratuito)
            return 0;
        else
            return costoBase * 1.21;
    }

    //--------------------GETTERS--------------------
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    //--------------------SETTERS--------------------
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return;
        }
        this.titulo = titulo;
    }

    public void setCostoBase(double costoBase) {
        if(gratuito) {
            this.costoBase = 0;
        } else {
            this.costoBase = costoBase * 1.21;
        }
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }
}