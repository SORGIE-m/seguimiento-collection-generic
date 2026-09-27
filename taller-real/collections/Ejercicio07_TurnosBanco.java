package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Ejercicio 7 - Sistema de turnos de un banco usando LinkedList
 * <p>
 * Los clientes se van agregando al final de la fila y se atienden desde
 * el principio, como cualquier fila normal. La particularidad es que un
 * cliente puede marcarse como urgente, en cuyo caso pasa directo al
 * inicio con {@code addFirst}, que en una LinkedList es una operación en
 * tiempo constante y no obliga a mover al resto de la fila (a diferencia
 * de lo que pasaría con un ArrayList).
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase solo administra la
 * fila de espera; no decide cómo se llama al cliente ni qué trámite se le
 * hace, eso es responsabilidad de otra parte del sistema.
 */
public class Ejercicio07_TurnosBanco {

    static class SistemaTurnosBanco {

        private final LinkedList<String> turnos = new LinkedList<>();

        void agregarCliente(String nombreCliente) {
            turnos.addLast(nombreCliente);
        }

        /**
         * Inserta un cliente urgente directo al inicio de la fila, sin
         * afectar el orden ni el rendimiento del resto de la cola.
         */
        void agregarClienteUrgente(String nombreCliente) {
            turnos.addFirst(nombreCliente);
        }

        String atenderSiguiente() {
            return turnos.pollFirst();
        }

        boolean hayClientesEnEspera() {
            return !turnos.isEmpty();
        }

        List<String> verFilaActual() {
            return new ArrayList<>(turnos);
        }
    }

    public static void main(String[] args) {
        SistemaTurnosBanco banco = new SistemaTurnosBanco();
        banco.agregarCliente("Laura Torres");
        banco.agregarCliente("Julian Mesa");
        banco.agregarCliente("Paula Rios");

        System.out.println("Fila antes de la urgencia: " + banco.verFilaActual());

        banco.agregarClienteUrgente("Cliente con cita médica");
        System.out.println("Fila después de la urgencia: " + banco.verFilaActual());

        System.out.println("Atendiendo clientes en orden:");
        while (banco.hayClientesEnEspera()) {
            System.out.println(" -> " + banco.atenderSiguiente());
        }
    }
}
