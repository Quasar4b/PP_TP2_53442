# PP_TP2_53442 — Sistema de Gestión de Eventos Universitarios (Upgrade)

## Descripción del proyecto
Aplicación en Java que modela un sistema de gestión de eventos universitarios, sus actividades, estudiantes e inscripciones. En esta segunda etapa, el proyecto escala su arquitectura incorporando manejo de excepciones, persistencia de datos mediante serialización, uso de interfaces, genéricos, comodines (wildcards), clases anidadas y ejecución concurrente mediante hilos.

## Estructura de clases
* **EventoUniversitario:** representa un evento que compone actividades y agrega una sala. Ahora incorpora métodos genéricos parametrizados para filtrar sus actividades y persistencia de su estado.
* **Actividad (clase abstracta):** define comportamiento base y lanza excepciones controladas al superar el límite de inscriptos.
* **Charla:** actividad sin costo de materiales.
* **Taller:** actividad con costo dependiente del uso de notebook. Implementa la interfaz `Certificable`.
* **Curso (Nuevo):** actividad con costo de materiales dependiente de su nivel (1, 2 o 3). Implementa la interfaz `Certificable`.
* **Estudiante, Inscripcion y Sala:** entidades básicas del modelo.
* **TicketDeAcceso (Clase Anidada Miembro):** clase interna a `Inscripcion` que solo se instancia cuando la inscripción se encuentra en estado "CONFIRMADA".
* **CupoExcedidoException:** excepción chequeada personalizada para manejar de forma robusta los intentos de inscripción en actividades llenas.
* **EnvioTicketsThread:** clase que hereda de `Thread` y se encarga de ejecutar de forma concurrente el envío de los tickets generados.
* **Certificable (Interfaz):** protocolo común que define el método `generarCertificado()` para las actividades que lo requieran.

## Conceptos Avanzados de POO Aplicados
* **Excepciones y Persistencia:** Manejo de fallos con bloques `try-catch` y `finally`. Todo el ecosistema de un evento puede guardarse en un archivo `.dat` (`ObjectOutputStream`) y recuperarse (`ObjectInputStream`) sin perder su estado.
* **Interfaces y Polimorfismo múltiple:** A través de `Certificable`, el sistema detecta en tiempo de ejecución (mediante `instanceof`) cuáles actividades pueden generar diplomas, dejando fuera a las charlas.
* **Genéricos y Wildcards:** El método `filtrarActividadesPorTipo()` permite devolver listas puras tipadas dinámicamente, mientras que `calcularCostoMateriales(List)` acepta cualquier subtipo gracias a los comodines acotados.
* **Hilos y Clases Internas:** Instanciación de clases fuertemente vinculadas mediante la sintaxis `.new`, y ejecución paralela para simular el envío asíncrono de tickets mientras el sistema principal sigue operando.

## Reglas de negocio
* Si el evento es gratuito, su costo total estimado es 0.
* Si el evento no es gratuito, el costo total se calcula como: `(costoBase + suma del costo de materiales de sus actividades) * 1.21` (IVA).
* **Charlas:** no generan costo de materiales ($0) y no emiten certificado.
* **Talleres:** cuestan $5000 si requieren notebook, o $2000 si no la requieren.
* **Cursos:** cuestan $3000 (Nivel 1), $4000 (Nivel 2) o $5000 (Nivel 3).
* Un **Ticket de Acceso** se emite única y exclusivamente si el usuario decide confirmar la inscripción al momento de registrarla.

## Cómo ejecutar el proyecto
1. Clonar el repositorio en Símbolo de sistema (cmd) o terminal:
   `git clone https://github.com/Quasar4b/PP_TP2_53442.git`
2. Abrir la carpeta del proyecto con IntelliJ IDEA.
3. Ejecutar la clase `App` (contiene el método `main`) para interactuar con el sistema por consola.

## Ejemplo de ejecución
El programa, al ejecutarse:
1. Crea una lista de estudiantes y un evento con su costo base.
2. Asigna una sala y permite crear múltiples actividades (Charla, Taller, Curso).
3. Inscribe estudiantes manejando la excepción si se supera el cupo, y permite confirmar la inscripción para instanciar su ticket de acceso.
4. Emite e imprime por consola los certificados de asistencia.
5. Persiste el evento en memoria (`evento_EVT-1.dat`) y demuestra su correcta recuperación.
6. Filtra la lista de actividades por tipo demostrando el uso de métodos parametrizados.
7. Dispara un hilo secundario que envía progresivamente los tickets confirmados, entremezclando sus mensajes en la consola con las últimas impresiones del hilo principal.

# Captura de la salida por consola de una ejecución del programa:
<img width="985" height="1166" alt="image" src="https://github.com/user-attachments/assets/fcb7efbe-6b47-4bfa-b029-06c570656c78" />
<img width="1022" height="716" alt="image" src="https://github.com/user-attachments/assets/94ed90d2-3f16-491d-a270-f7103c54ce7b" />
