import java.util.ArrayList;
import java.util.List;

public class App {
    public App(){
    }
    public static void main(String[] args) {
        App app = new App();
        app.crearEstudiante();
        app.crearEventos();
    }
    
        // --------------------LISTAS--------------------

        List<Estudiante> estudiantes = new ArrayList<>();

        //--------------------METODOS--------------------

        public void crearEstudiante() {
            Estudiante estudiante1 = new Estudiante(
                    "53442",
                    "Juan Francisco Olivieri");
            Estudiante estudiante2 = new Estudiante(
                    "53522",
                    "Valentina Antonella Toledo");
            Estudiante estudiante3 = new Estudiante(
                    "53468",
                    "Lucas Reynoso");
            estudiantes.add(estudiante1);
            estudiantes.add(estudiante2);
            estudiantes.add(estudiante3);
        }

        public void crearEventos() {

            EventoUniversitario evento1 = new EventoUniversitario(
                    "E001",
                    "Charla de Programación",
                    5000,
                    false);
            EventoUniversitario evento2 = new EventoUniversitario(
                    "E002",
                    "Taller de Java",
                    10000,
                    false);

            //--------------------CREADORES DE INSTANCIAS--------------------

            Sala sala1 = new Sala(
                    1,
                    "Lab1"
            );
            Sala sala2 = new Sala(
                    2,
                    "Lab2"
            );

            EventoUniversitario copiaEvento1 = new EventoUniversitario(evento1);

            //--------------------ASIGNACIONES--------------------

            evento1.asignarSala(sala1);
            evento2.asignarSala(sala2);

            evento1.crearActividad(1, "Charla de Programación Multilenguaje", 10, "Charla", "Julio");
            evento2.crearActividad(2, "Taller de Java", 10, "Taller", true);
            copiaEvento1.crearActividad(1, "Charla de Programación Multilenguaje", 10, "Charla", "Julio");

            evento1.getActividades().get(0).inscribir(estudiantes.get(0));
            evento1.getActividades().get(0).inscribir(estudiantes.get(1));
            evento2.getActividades().get(0).inscribir(estudiantes.get(0));
            evento2.getActividades().get(0).inscribir(estudiantes.get(2));

            //--------------------IMPRESIONES EN PANTALLA--------------------

            System.out.println("--------EVENTO NUMERO 1--------");
            evento1.mostrarDatos();
            System.out.println("--------EVENTO NUMERO 2--------");
            evento2.mostrarDatos();
            System.out.println("--------COPIA DEL EVENTO 1--------");
            copiaEvento1.mostrarDatos();
            System.out.println("-----------------------------");
            System.out.println("Cantidad de Eventos: " + EventoUniversitario.getCantidadEventos());
        }
    }