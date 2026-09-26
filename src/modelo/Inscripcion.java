package modelo;
import modelo.actividades.Actividad;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private final Actividad actividad;
    private final Estudiante estudiante;
    private LocalDate fecha;
    private String estado;
    private TicketDeAcceso ticket;
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

    public TicketDeAcceso getTicket() {
    return ticket;
}

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTicket(TicketDeAcceso ticket) {
        this.ticket = ticket;
    }

    //--------------------SETTERS--------------------
    public final class TicketDeAcceso implements Serializable {
        private String idTicket;
        private LocalDate fechaEmision;
        public TicketDeAcceso(){
            this.idTicket = "TICKET-" + actividad.getId();
            this.fechaEmision = LocalDate.now();
            System.out.println("El Ticket fue creado con Éxito");
        }

        public void enviarTicket(){
            System.out.println("El estudiante " + estudiante.getNombre() + " de legajo "+ estudiante.getLegajo() + " obtuvo el ticket " + idTicket  + " en la actividad " + actividad.getTitulo());
        }

    }
}

