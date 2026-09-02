# PP_TP1_53442 — Sistema de Gestión de Eventos Universitarios

## Descripción del proyecto
Aplicación en Java que modela un sistema de gestión de eventos universitarios, sus actividades, estudiantes e inscripciones. El proyecto fue incorporando progresivamente relaciones de composición, agregación, herencia y polimorfismo.

## Estructura de clases
* **EventoUniversitario:** representa un evento universitario. Se compone de una o más actividades (composición: las actividades no existen sin el evento) y tiene asociada una sala (agregación: la sala existe independientemente del evento).
* **Actividad (clase abstracta):** clase base de toda actividad. Define atributos y comportamiento común (id, título, cupo máximo, lista de inscripciones, método `inscribir()`, `mostrarInscripciones()`). El método `mostrarIdentificaciones()` es final (no puede redefinirse en las subclases). Los métodos `calcularCostoMateriales()` y `getTipo()` son abstractos: cada subclase concreta decide cómo implementarlos.
* **Charla (subclase de Actividad):** actividad sin costo de materiales. Tiene un atributo propio `disertante`.
* **Taller (subclase de Actividad):** actividad con costo de materiales según si requiere notebook o no. Tiene un atributo propio `requiereNotebook`.
* **Estudiante:** representa a un estudiante que puede inscribirse en actividades.
* **Inscripcion:** asocia un estudiante con una actividad, registrando fecha y estado de la inscripción.
* **Sala:** representa el espacio físico asignado a un evento.

## Polimorfismo
El método `mostrarIdentificaciones()`, definido una única vez en `Actividad` (y marcado como `final`), utiliza internamente el método abstracto `getTipo()`. Al recorrer una lista `List<Actividad>` que contiene objetos `Charla` y `Taller` indistintamente, cada objeto resuelve `getTipo()` según su propia clase real, sin necesidad de preguntar explícitamente de qué tipo es cada actividad (sin usar `if`).

## Reglas de negocio
* Si el evento es gratuito, su costo total estimado es 0.
* Si el evento no es gratuito, el costo total se calcula como: `(costoBase + suma del costo de materiales de sus actividades) * 1.21` (el 21% adicional corresponde a impuestos).
* **Charlas:** no generan costo de materiales ($0).
* **Talleres:** cuestan $5000 si requieren notebook, o $2000 si no la requieren.

## Cómo ejecutar el proyecto
1. Clonar el repositorio en Símbolo de sistema (cmd) o terminal:
   `git clone https://github.com/Quasar4b/PP_TP1_53442.git`
2. Abrir la carpeta del proyecto con IntelliJ IDEA.
3. Ejecutar la clase `App` (contiene el método `main`) para poder ver la salida.

## Ejemplo de ejecución
El programa, al ejecutarse:
1. Crea estudiantes.
2. Crea un evento universitario.
3. Le asigna una sala.
4. Crea actividades de tipo Charla y Taller para ese evento.
5. Inscribe estudiantes en cada actividad.
6. Muestra el resumen de datos del evento, recorriendo sus actividades y mostrando la identificación de cada una de forma polimórfica.
7. Muestra el total de eventos creados.

# Captura de la salida por consola de una ejecución del programa:
<img width="1652" height="670" alt="image" src="https://github.com/user-attachments/assets/f5fe2076-0db9-4f16-ba70-2b8faa1e927a" />
