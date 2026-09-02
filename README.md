<img width="1652" height="670" alt="image" src="https://github.com/user-attachments/assets/6cd8dd8d-aa1d-49c8-aeb7-d8d806dedf7e" /><img width="1652" height="670" alt="image" src="https://github.com/user-attachments/assets/7bd9b8ee-b9e3-49c4-ac59-5573954dfaa5" /># PP_TP1_53522 — Sistema de Gestión de Eventos Universitarios

**Universidad:** Universidad Tecnológica Nacional - Facultad Regional Mendoza (UTN FRM)
**Materia:** Paradigmas de Programación
**Alumno:** Juan Francisco Olivieri 
**Legajo:** 53442

## Descripción del Proyecto
Este proyecto implementa un sistema básico para la administración de eventos universitarios mediante Programación Orientada a Objetos en Java. El modelo permite crear eventos, asignarles salas físicas, gestionar distintas actividades (charlas y talleres) e inscribir estudiantes en dichas actividades.

## Conceptos Aplicados
El código fue desarrollado aplicando los fundamentos de la POO, incluyendo:
* **Encapsulamiento:** Uso adecuado de modificadores de acceso (`private`, `public`, `protected`) y constantes (`final`).
* **Relaciones entre Clases:**
  * *Agregación:* Relación entre `EventoUniversitario` y `Sala` (la sala existe independientemente del evento).
  * *Composición:* Relación entre `EventoUniversitario` y `Actividad` (el evento se compone de actividades, contenidas en una lista).
  * *Asociación:* Relación de los estudiantes con las actividades a través de la clase intermedia `Inscripcion`.
* **Herencia:** La clase abstracta `Actividad` funciona como superclase para los tipos específicos `Charla` y `Taller`.
* **Polimorfismo:** Implementado en el cálculo de costos y la visualización de datos, permitiendo tratar distintas actividades de forma unificada en las colecciones del evento.

## Estructura del Repositorio
El repositorio cuenta con todos los artefactos solicitados en las pautas de entrega:
- Directorio de código fuente generado en IntelliJ IDEA.
- Este archivo `README.md` con la documentación del proyecto.
- `mapa_memoria.png` / `mapa_memoria.jpg`: Representación gráfica del Heap y Stack de ejecución del Ejercicio 4.
- `captura_consola.png` / `captura_consola.jpg`: Evidencia de la correcta ejecución del programa y su salida en pantalla.

## Ejecución
Para ejecutar el proyecto, compilar e iniciar desde la clase `App.java`, la cual contiene el método `main` con los casos de prueba de estudiantes, eventos y actividades instanciados.
<img width="1652" height="670" alt="image" src="https://github.com/user-attachments/assets/f5fe2076-0db9-4f16-ba70-2b8faa1e927a" />
