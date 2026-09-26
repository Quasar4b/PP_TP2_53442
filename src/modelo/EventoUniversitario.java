package modelo;
import exepciones.CupoExcedidoException;
import modelo.*;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventoUniversitario implements Serializable {
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

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Costo Total: " + calcularCostoEstimado());
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

    public void crearActividad (int id, String titulo, int cupoMaximo, String tipoActividad){
        Scanner scanner = new Scanner(System.in);
        if (tipoActividad.equals("taller")){
            System.out.println("¿Requiere el uso de Notebook? si/no");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if(respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")){
                Actividad taller = new Taller(id,titulo,cupoMaximo,true);
                actividades.add(taller);
            } else {
                Actividad taller = new Taller(id,titulo,cupoMaximo,false);
                actividades.add(taller);
            }
        } else if (tipoActividad.equals("charla")) {
            System.out.println("Ingrese el disertante de la charla");
            String disertante = scanner.nextLine();
            Actividad charla = new Charla(id,titulo,cupoMaximo,disertante);
            actividades.add(charla);
        } else if (tipoActividad.equals("curso")) {
            System.out.println("Ingrese el nivel del Curso");
            int nivel = scanner.nextInt();
            Actividad curso = new Curso(id, titulo, cupoMaximo, nivel);
            actividades.add(curso);
        } else {
            System.out.println("Esa actividad no existe");
        }
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        } else {
            double costoActividades = 0;
            for (Actividad actividad : actividades) {
                costoActividades += actividad.calcularCostoMateriales();
            }

            return (costoBase + costoActividades) * 1.21;
        }
    }
    public boolean persistirEvento(){
        try {
            FileOutputStream fos = new FileOutputStream(this.id + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
            return false;
        }
        catch (IOException ex) {
            System.out.println("Se produjo un error de E/S");
            return false;
        }
    }
    public EventoUniversitario recuperarEvento(String id){
        EventoUniversitario eventoUniversitario = null;
        try {
            FileInputStream fis = new FileInputStream(id + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            eventoUniversitario = (EventoUniversitario) ois.readObject();
        ois.close();
        fis.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("No se encontro el archivo");
        }
        catch (IOException ex) {
            System.out.println("Se produjo un error de E/S");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Error de conversion de clase");
        }
        return eventoUniversitario;
    }
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();

        for (Actividad actividad : actividades) {
            if (tipo.isInstance(actividad)) {
                resultado.add(tipo.cast(actividad));
            }
        }
        return resultado;
    }
    public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        double costoTotal = 0.0;

        for (Actividad actividad : actividades) {
            costoTotal += actividad.calcularCostoMateriales();
        }

        return costoTotal;
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