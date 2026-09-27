package generics.enunciados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Enunciado 1 - InventarioCaja&lt;T extends Comparable&lt;T&gt;&gt; con filtro por Comparable
 * <p>
 * Guarda elementos en una lista interna y permite recuperar únicamente los
 * que superan un umbral dado. El enunciado pide explícitamente que ese
 * filtrado se haga recorriendo la lista SOLO con {@code Iterator}, sin usar
 * for-each, así que el recorrido se hace "a mano" con
 * {@code hasNext()}/{@code next()}.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase se dedica
 * exclusivamente a guardar elementos y a filtrarlos por un umbral; no sabe
 * nada de cómo se van a mostrar ni de dónde vienen los datos originales.
 *
 * @param <T> tipo de los elementos guardados, debe ser comparable consigo mismo
 */
public class Ejercicio01_InventarioCaja {

    static class InventarioCaja<T extends Comparable<T>> {

        private final List<T> elementos = new ArrayList<>();

        void agregar(T elemento) {
            elementos.add(elemento);
        }

        /**
         * Devuelve una nueva lista con los elementos estrictamente mayores
         * al umbral recibido. El recorrido se hace únicamente con Iterator,
         * tal como lo pide el enunciado (nada de for-each ni streams).
         *
         * @param umbral valor de referencia para filtrar
         * @return lista nueva con los elementos mayores al umbral
         */
        List<T> obtenerMayoresQue(T umbral) {
            List<T> resultado = new ArrayList<>();
            Iterator<T> iterador = elementos.iterator();
            while (iterador.hasNext()) {
                T actual = iterador.next();
                if (actual.compareTo(umbral) > 0) {
                    resultado.add(actual);
                }
            }
            return resultado;
        }

        int tamano() {
            return elementos.size();
        }
    }

    public static void main(String[] args) {
        InventarioCaja<Integer> inventarioDeStock = new InventarioCaja<>();
        inventarioDeStock.agregar(10);
        inventarioDeStock.agregar(45);
        inventarioDeStock.agregar(3);
        inventarioDeStock.agregar(78);
        inventarioDeStock.agregar(22);

        System.out.println("Total de elementos guardados: " + inventarioDeStock.tamano());
        System.out.println("Elementos con stock mayor a 20: " + inventarioDeStock.obtenerMayoresQue(20));
    }
}
