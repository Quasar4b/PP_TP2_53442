package modelo;
import exepciones.CupoExcedidoException;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;
import modelo.certificación.Certificable;
import hilos.EnvioTicketsThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 5App {
    public App() {
    }

    public static void main(String[] args) throws CupoExcedidoException {
        Scanner scanner = new Scanner(System.in);
        int id = 1;
        boolean esGratuito = false;
        boolean continuar = true;
        //se crea la lista de estudiantes//
        System.out.println("----REGISTRO DE ESTUDIANTES----");
        List<Estudiante> estudiantes = new ArrayList<>();
        while (continuar) {
            //pedimos los datos con variables locales
            System.out.println("Ingrese el legajo del estudiante: ");
            String legajo = scanner.nextLine();
            System.out.println("Ingrese el nombre del estudiante: ");
            String nombre = scanner.nextLine();
            estudiantes.add(new Estudiante(legajo, nombre));
            System.out.println("¿Desea cargar otro estudiante? si/no");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                continuar = true;
            } else {
                continuar = false;
            }
        }
        //Construimos Eventos
        System.out.println("\n----REGISTRO DE EVENTOS----");
        continuar = true;
        while (continuar) {
            System.out.println("Ingrese un titulo para el evento: ");
            String titulo = scanner.nextLine();
            System.out.println("¿El evento es gratuito? si/no");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                esGratuito = true;
            } else {
                esGratuito = false;
            }
            // Preguntamos el costo si NO es gratuito
            double costoBase = 0;
            if (!esGratuito) {
                System.out.println("Ingrese el costo base del evento: ");
                costoBase = scanner.nextDouble();
                scanner.nextLine();
            }
            EventoUniversitario evento = new EventoUniversitario("EVT-" + id, titulo, costoBase, esGratuito);
            id++;

            //creamos y asignamos sala al evento
            System.out.println("Ingrese el nombre de la sala donde se realizará el evento:");
            String nombreSala = scanner.nextLine();
            Sala sala = new Sala(id, nombreSala);
            evento.asignarSala(sala);
            /* Se crean las actividades del evento */
            System.out.println("\nREGISTRO DE ACTIVIDADES PARA EL EVENTO " + evento.getTitulo());
            int idActividad = 1;
            while (continuar) {
                System.out.println("Ingrese el tipo de actividad (Taller, Charla o Curso): ");
                String tipoActividad = scanner.nextLine().trim().toLowerCase();
                System.out.println("Ingrese el título de la actividad: ");
                String tituloActividad = scanner.nextLine();
                System.out.println("Ingrese el cupo máximo de estudiantes admitidos para la actividad: ");
                int cupoMaximo = scanner.nextInt();
                scanner.nextLine(); //Se consume la linea.
                evento.crearActividad(id, tituloActividad, cupoMaximo, tipoActividad); //aca llamamos para crear la actividad
                System.out.println("¿Desea crear otra actividad para el evento " + evento.getTitulo() + "? si/no");
                respuesta = scanner.nextLine().trim().toLowerCase();
                continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
                ++idActividad;
            }
            // Se inscriben estudiantes en actividades
            System.out.println("\nINSCRIPCIÓN DE ESTUDIANTES EN ACTIVIDADES DEL EVENTO " + evento.getTitulo());
            continuar = true;
            while (continuar) {
                try {
                    System.out.println("Ingrese legajo del estudiante a inscribir: ");
                    String legajo = scanner.nextLine();
                    System.out.println("Ingrese id de la Actividad a la que desea inscribir al estudiante: ");
                    idActividad = scanner.nextInt();
                    scanner.nextLine();

                    for (Estudiante estudiante : estudiantes) {
                        if (estudiante.getLegajo().equals(legajo)) {
                            Inscripcion inscripcion = evento.getActividades().get(--idActividad).inscribir(estudiante);

                            System.out.println("¿Desea confirmar esta inscripción ahora? si/no");
                            respuesta = scanner.nextLine().trim().toLowerCase();

                            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                                inscripcion.setEstado("CONFIRMADA");
                                Inscripcion.TicketDeAcceso ticket = inscripcion.new TicketDeAcceso();
                                inscripcion.setTicket(ticket);
                            }
                        }
                    }
                } catch (CupoExcedidoException e) {
                    System.out.println("Excepción atrapada " + e.getMessage());
                } finally {
                    System.out.println("Inscripción Finalizada");
                }
                System.out.println("¿Desea generar otra inscripción? si/no");
                respuesta = scanner.nextLine().trim().toLowerCase();
                continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
            }
            System.out.println("\nDATOS DEL EVENTO");
            evento.mostrarDatos();

            for(Actividad actividad : evento.getActividades()) {
                if (actividad instanceof Certificable certificable) {
                    System.out.println("\nCERTIFICADOS EMITIDOS PARA LA ACTIVIDAD " + actividad.getTitulo());

                    for(Inscripcion inscripcion : actividad.getInscripciones()) {
                        String certificado = certificable.generarCertificado(inscripcion.getEstudiante());
                        System.out.println(certificado);
                    }
                }
            }
            //Guardamos el ID del Evento.dat
            evento.persistirEvento();
            //Recuperamos eventos creados
            continuar = true;
            System.out.println("\n ¿Desea recuperar un Evento Creado? si/no");
            respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("si")) {
                while (continuar) {
                    System.out.println("\n Ingrese el ID del Evento a Recuperar: ");
                    String recuperarId = scanner.nextLine();
                    EventoUniversitario eventoRecuperado = evento.recuperarEvento(recuperarId);
                    if(eventoRecuperado != null){
                        System.out.println("\n ----EVENTO RECUPERADO CON EXITO----");
                    eventoRecuperado.mostrarDatos();
                    }
                    System.out.println("\n ¿Desea recuperar otro Evento? si/no");
                    respuesta = scanner.nextLine().trim().toLowerCase();
                    continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
                }
            }
            System.out.println("\n ¿Desea crear otro evento? si/no");
            respuesta = scanner.nextLine().trim().toLowerCase();
            continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;

            List<Taller> ListaTalleres = evento.filtrarActividadesPorTipo(Taller.class);
            List<Charla> ListaCharlas = evento.filtrarActividadesPorTipo(Charla.class);
            List<Curso> ListaCursos = evento.filtrarActividadesPorTipo(Curso.class);

            Thread envioTicketsThread = new EnvioTicketsThread(evento);
            envioTicketsThread.start();

            System.out.println("Cantidad de Talleres: " + ListaTalleres.size());
            System.out.println("Cantidad de Charlas: " + ListaCharlas.size());
            System.out.println("Cantidad de Cursos: " + ListaCursos.size());
            System.out.println("El precio de los Talleres fue de: " + evento.calcularCostoMateriales(ListaTalleres) + "pesos");
            System.out.println("El precio de las Charlas fue de: " + evento.calcularCostoMateriales(ListaCharlas) + "pesos");
            System.out.println("El precio de los Cursos fue de: " + evento.calcularCostoMateriales(ListaCursos) + "pesos");
        }
        System.out.println("\n Cantidad total de eventos creados: "+EventoUniversitario.getCantidadEventos());
    }
}
