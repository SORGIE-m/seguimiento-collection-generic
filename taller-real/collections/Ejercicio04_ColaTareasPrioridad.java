package collections;

import java.util.PriorityQueue;

/**
 * Ejercicio 4 - Cola de tareas con prioridad usando PriorityQueue
 * <p>
 * Cada {@code Tarea} tiene un nivel de importancia (mientras más alto el
 * número, más importante es). Para que PriorityQueue atienda primero las
 * tareas más importantes, el orden natural de Tarea se define de forma
 * invertida: en vez de comparar de menor a mayor, compara de mayor a
 * menor.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> Tarea solo sabe representar
 * sus datos y compararse por prioridad; GestorTareas solo administra la
 * cola. Ninguna de las dos sabe nada de cómo se van a mostrar las tareas
 * en pantalla.
 */
public class Ejercicio04_ColaTareasPrioridad {

    static class Tarea implements Comparable<Tarea> {
        private final String nombre;
        private final int prioridad; // mayor número = más importante

        Tarea(String nombre, int prioridad) {
            this.nombre = nombre;
            this.prioridad = prioridad;
        }

        String getNombre() {
            return nombre;
        }

        int getPrioridad() {
            return prioridad;
        }

        // Orden descendente por prioridad, así el más importante sale primero
        @Override
        public int compareTo(Tarea otra) {
            return Integer.compare(otra.prioridad, this.prioridad);
        }

        @Override
        public String toString() {
            return nombre + " (prioridad " + prioridad + ")";
        }
    }

    static class GestorTareas {

        private final PriorityQueue<Tarea> tareas = new PriorityQueue<>();

        void agregarTarea(Tarea tarea) {
            tareas.offer(tarea);
        }

        Tarea atenderSiguiente() {
            return tareas.poll();
        }

        Tarea verSiguiente() {
            return tareas.peek();
        }

        boolean hayTareasPendientes() {
            return !tareas.isEmpty();
        }
    }

    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        gestor.agregarTarea(new Tarea("Responder correos", 2));
        gestor.agregarTarea(new Tarea("Corregir bug en producción", 9));
        gestor.agregarTarea(new Tarea("Actualizar documentación", 3));
        gestor.agregarTarea(new Tarea("Preparar presentación", 6));

        System.out.println("Próxima tarea a atender: " + gestor.verSiguiente());

        System.out.println("Atendiendo tareas en orden de importancia:");
        while (gestor.hayTareasPendientes()) {
            System.out.println(" -> " + gestor.atenderSiguiente());
        }
    }
}
