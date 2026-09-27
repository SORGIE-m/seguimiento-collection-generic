package collections;

import java.time.LocalDate;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Ejercicio 17 - Agenda de eventos usando TreeMap
 * <p>
 * Cada evento se guarda usando su fecha como clave. Al ser un TreeMap,
 * los eventos quedan ordenados cronológicamente sin esfuerzo extra, y se
 * puede consultar fácilmente cuál es el próximo evento o qué eventos hay
 * desde una fecha en adelante.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase solo administra la
 * agenda; no decide cómo se notifica al usuario ni cómo se muestra en una
 * interfaz gráfica.
 */
public class Ejercicio17_AgendaEventos {

    static class AgendaEventos {

        private final TreeMap<LocalDate, String> eventos = new TreeMap<>();

        void programarEvento(LocalDate fecha, String descripcion) {
            eventos.put(fecha, descripcion);
        }

        /**
         * Devuelve el evento más próximo en la agenda (la clave más
         * pequeña, gracias al orden que mantiene TreeMap).
         */
        Map.Entry<LocalDate, String> proximoEvento() {
            return eventos.firstEntry();
        }

        /**
         * Devuelve todos los eventos programados desde una fecha en
         * adelante, incluida esa fecha.
         */
        SortedMap<LocalDate, String> eventosDesde(LocalDate fecha) {
            return eventos.tailMap(fecha);
        }
    }

    public static void main(String[] args) {
        AgendaEventos agenda = new AgendaEventos();
        agenda.programarEvento(LocalDate.of(2026, 10, 15), "Entrega del laboratorio de Collections");
        agenda.programarEvento(LocalDate.of(2026, 10, 3), "Sustentación del proyecto");
        agenda.programarEvento(LocalDate.of(2026, 11, 1), "Examen final");

        System.out.println("Próximo evento: " + agenda.proximoEvento());
        System.out.println("Eventos desde el 10 de octubre: " + agenda.eventosDesde(LocalDate.of(2026, 10, 10)));
    }
}
