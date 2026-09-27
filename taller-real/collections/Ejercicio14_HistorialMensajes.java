package collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 14 - Historial de mensajes recientes usando ArrayDeque
 * <p>
 * Se guardan los últimos mensajes enviados, con un límite fijo. Cuando se
 * llega al límite, el mensaje más antiguo se descarta automáticamente
 * antes de agregar el nuevo, así que el historial siempre refleja
 * solamente los mensajes más recientes.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase solo administra el
 * historial reciente; no envía los mensajes ni sabe nada del protocolo de
 * mensajería que hay detrás.
 */
public class Ejercicio14_HistorialMensajes {

    static class HistorialMensajes {

        private static final int LIMITE_MENSAJES = 10;
        private final ArrayDeque<String> mensajes = new ArrayDeque<>();

        void enviarMensaje(String mensaje) {
            if (mensajes.size() == LIMITE_MENSAJES) {
                mensajes.pollFirst(); // se descarta el mensaje más antiguo
            }
            mensajes.addLast(mensaje);
        }

        /**
         * Devuelve los mensajes guardados, del más antiguo al más reciente.
         */
        List<String> obtenerUltimosMensajes() {
            return new ArrayList<>(mensajes);
        }

        int cantidadGuardada() {
            return mensajes.size();
        }
    }

    public static void main(String[] args) {
        HistorialMensajes chat = new HistorialMensajes();

        for (int i = 1; i <= 12; i++) {
            chat.enviarMensaje("Mensaje número " + i);
        }

        System.out.println("Mensajes guardados: " + chat.cantidadGuardada());
        System.out.println("Últimos mensajes (solo caben 10, los primeros 2 ya se descartaron):");
        System.out.println(chat.obtenerUltimosMensajes());
    }
}
