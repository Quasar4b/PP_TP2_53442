package hilos;

import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.actividades.Actividad;

public class EnvioTicketsThread extends Thread{
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento){
        super("Estoy ejecutando el hilo");
        this.evento = evento;
    }

    public void run(){
        System.out.println("[" + getName() + "] Inicio del envio de tickets");

        for (Actividad actividad: evento.getActividades()){
            for (Inscripcion inscripcion: actividad.getInscripciones()){
                if ("CONFIRMADA".equals(inscripcion.getEstado())){
                    inscripcion.getTicket().enviarTicket();
                    try{
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
