package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Ejercicio 3 - Lista de elementos sin duplicados, impresa con iteradores
 * <p>
 * Es una lista genérica que rechaza cualquier elemento que ya esté
 * guardado (comparando con {@code equals}). Para mostrar el contenido no
 * se usa for-each ni {@code System.out.println(lista)} directo, sino un
 * {@code Iterator} explícito, tal como pide el enunciado.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> esta clase se encarga
 * únicamente de mantener la lista sin duplicados y de recorrerla; no le
 * importa qué se hace con lo que imprime ni de dónde vienen los datos.
 *
 * @param <T> tipo de los elementos que se van a guardar
 */
public class Ejercicio03_ListaSinDuplicados {

    static class ListaSinDuplicados<T> {

        private final List<T> elementos = new ArrayList<>();

        /**
         * Agrega el elemento solo si todavía no estaba en la lista.
         *
         * @param elemento el elemento a agregar
         * @return true si se agregó, false si ya existía y se descartó
         */
        boolean agregar(T elemento) {
            if (elementos.contains(elemento)) {
                return false;
            }
            elementos.add(elemento);
            return true;
        }

        /**
         * Imprime todos los elementos guardados, recorriendo la lista con
         * un Iterator explícito.
         */
        void imprimir() {
            Iterator<T> iterador = elementos.iterator();
            while (iterador.hasNext()) {
                System.out.println(" -> " + iterador.next());
            }
        }

        int tamano() {
            return elementos.size();
        }
    }

    public static void main(String[] args) {
        ListaSinDuplicados<String> nombresUnicos = new ListaSinDuplicados<>();

        System.out.println("¿Se agregó 'Camila'? " + nombresUnicos.agregar("Camila"));
        System.out.println("¿Se agregó 'Andres'? " + nombresUnicos.agregar("Andres"));
        System.out.println("¿Se agregó 'Camila' otra vez? " + nombresUnicos.agregar("Camila"));
        System.out.println("¿Se agregó 'Sofia'? " + nombresUnicos.agregar("Sofia"));

        System.out.println("Total de nombres guardados: " + nombresUnicos.tamano());
        System.out.println("Contenido de la lista:");
        nombresUnicos.imprimir();
    }
}
