package generics.enunciados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Enunciado 2 - DirectorioContactos con orden natural por nombre
 * <p>
 * {@code Contacto} define su orden natural comparando por nombre
 * (implementa Comparable&lt;Contacto&gt;). {@code DirectorioContactos} guarda los
 * contactos en una LinkedList y ofrece dos formas de trabajar con ellos:
 * buscar por dominio de correo (recorriendo solo con Iterator, tal como
 * pide el enunciado) y ordenar por teléfono usando un Comparator externo.
 * </p>
 *
 * <b>SOLID:</b>
 * <ul>
 * <li><b>Responsabilidad Única (SRP):</b> Contacto solo representa los
 * datos de un contacto y sabe compararse por nombre; DirectorioContactos
 * se encarga de administrar la colección de contactos. Cada clase tiene
 * una única razón para cambiar.</li>
 * <li><b>Inversión de Dependencias (DIP):</b> el orden por teléfono no está
 * "quemado" dentro de Contacto, sino que se recibe como un Comparator
 * externo, así el directorio no depende de una única forma de ordenar.</li>
 * </ul>
 */
public class Ejercicio02_DirectorioContactos {

    static class Contacto implements Comparable<Contacto> {

        private final String nombre;
        private final String telefono;
        private final String email;

        Contacto(String nombre, String telefono, String email) {
            this.nombre = nombre;
            this.telefono = telefono;
            this.email = email;
        }

        String getNombre() {
            return nombre;
        }

        String getTelefono() {
            return telefono;
        }

        String getEmail() {
            return email;
        }

        // Orden natural: alfabético por nombre, sin distinguir mayúsculas
        @Override
        public int compareTo(Contacto otro) {
            return this.nombre.compareToIgnoreCase(otro.nombre);
        }

        @Override
        public String toString() {
            return nombre + " (" + telefono + ", " + email + ")";
        }
    }

    static class DirectorioContactos {

        private final LinkedList<Contacto> contactos = new LinkedList<>();

        void registrar(Contacto contacto) {
            contactos.add(contacto);
        }

        /**
         * Busca los contactos cuyo correo termina en el dominio indicado.
         * Recorre la lista únicamente con Iterator, sin for-each ni streams,
         * tal como lo exige el enunciado.
         */
        List<Contacto> buscarPorDominio(String dominio) {
            List<Contacto> coincidencias = new ArrayList<>();
            Iterator<Contacto> iterador = contactos.iterator();
            while (iterador.hasNext()) {
                Contacto contacto = iterador.next();
                if (contacto.getEmail().endsWith(dominio)) {
                    coincidencias.add(contacto);
                }
            }
            return coincidencias;
        }

        /**
         * Devuelve una copia de los contactos ordenada por teléfono,
         * usando un Comparator en vez del orden natural de Contacto.
         */
        List<Contacto> ordenarPorTelefono() {
            List<Contacto> copia = new ArrayList<>(contactos);
            Comparator<Contacto> porTelefono = Comparator.comparing(Contacto::getTelefono);
            Collections.sort(copia, porTelefono);
            return copia;
        }

        /**
         * Devuelve una copia ordenada usando el orden natural de Contacto
         * (por nombre), definido en su compareTo.
         */
        List<Contacto> ordenarPorNombre() {
            List<Contacto> copia = new ArrayList<>(contactos);
            Collections.sort(copia);
            return copia;
        }
    }

    public static void main(String[] args) {
        DirectorioContactos directorio = new DirectorioContactos();
        directorio.registrar(new Contacto("Camila Restrepo", "3216549870", "camila@uniquindio.edu.co"));
        directorio.registrar(new Contacto("Andres Gomez", "3001234567", "andres@gmail.com"));
        directorio.registrar(new Contacto("Sofia Londono", "3109876543", "sofia@uniquindio.edu.co"));

        System.out.println("Contactos con dominio uniquindio.edu.co:");
        System.out.println(directorio.buscarPorDominio("uniquindio.edu.co"));

        System.out.println("Contactos ordenados por teléfono:");
        System.out.println(directorio.ordenarPorTelefono());

        System.out.println("Contactos ordenados por nombre (orden natural):");
        System.out.println(directorio.ordenarPorNombre());
    }
}
