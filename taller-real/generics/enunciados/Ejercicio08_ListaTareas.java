package generics.enunciados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Enunciado 8 - ListaTareas&lt;T extends Comparable&lt;T&gt;&gt; con iterador inverso
 * <p>
 * Esta clase implementa {@code Iterable<T>}, así que se puede recorrer con
 * un for-each normal (usando el iterador por defecto, del primero al
 * último). Además, expone un segundo iterador construido a mano como clase
 * interna privada, que recorre del último elemento hacia el primero. El
 * método {@code obtenerEntre} usa EXCLUSIVAMENTE ese iterador inverso para
 * armar el resultado, tal como lo pide el enunciado.
 * </p>
 *
 * <b>SOLID:</b>
 * <ul>
 * <li><b>Responsabilidad Única (SRP):</b> la clase se dedica a guardar
 * tareas y a recorrerlas, sea hacia adelante o hacia atrás; no hace nada
 * relacionado con persistencia ni presentación.</li>
 * <li><b>Abierto/Cerrado (OCP):</b> al implementar Iterable&lt;T&gt;, esta clase
 * se puede usar en cualquier lugar que espere algo iterable (un for-each,
 * un método que reciba Iterable&lt;T&gt;, etc.) sin tener que modificar nada
 * más, solo extendiendo su comportamiento con el iterador inverso.</li>
 * </ul>
 *
 * @param <T> tipo de las tareas, debe ser comparable consigo mismo
 */
public class Ejercicio08_ListaTareas<T extends Comparable<T>> implements Iterable<T> {

    private final List<T> tareas = new ArrayList<>();

    public void agregar(T tarea) {
        tareas.add(tarea);
    }

    // Iterador "normal": recorre de la primera tarea a la última
    @Override
    public Iterator<T> iterator() {
        return tareas.iterator();
    }

    // Iterador inverso: recorre de la última tarea a la primera
    public Iterator<T> iteradorInverso() {
        return new IteradorInverso();
    }

    private class IteradorInverso implements Iterator<T> {

        private int indiceActual = tareas.size() - 1;

        @Override
        public boolean hasNext() {
            return indiceActual >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Ya no quedan tareas hacia atrás");
            }
            return tareas.get(indiceActual--);
        }
    }

    /**
     * Devuelve las tareas cuyo valor está entre min y max (ambos incluidos).
     * El recorrido se hace exclusivamente con el iterador inverso, como
     * lo exige el enunciado.
     */
    public List<T> obtenerEntre(T minimo, T maximo) {
        List<T> resultado = new ArrayList<>();
        Iterator<T> inverso = iteradorInverso();
        while (inverso.hasNext()) {
            T tarea = inverso.next();
            if (tarea.compareTo(minimo) >= 0 && tarea.compareTo(maximo) <= 0) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        Ejercicio08_ListaTareas<Integer> tareasPorPrioridad = new Ejercicio08_ListaTareas<>();
        tareasPorPrioridad.agregar(2);
        tareasPorPrioridad.agregar(5);
        tareasPorPrioridad.agregar(1);
        tareasPorPrioridad.agregar(8);
        tareasPorPrioridad.agregar(4);

        System.out.print("Recorrido normal (for-each, usa iterator()): ");
        for (Integer tarea : tareasPorPrioridad) {
            System.out.print(tarea + " ");
        }
        System.out.println();

        System.out.print("Recorrido inverso: ");
        Iterator<Integer> inverso = tareasPorPrioridad.iteradorInverso();
        while (inverso.hasNext()) {
            System.out.print(inverso.next() + " ");
        }
        System.out.println();

        System.out.println("Tareas con prioridad entre 2 y 5: " + tareasPorPrioridad.obtenerEntre(2, 5));
    }
}
