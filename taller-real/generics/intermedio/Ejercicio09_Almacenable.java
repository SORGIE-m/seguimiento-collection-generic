package generics.intermedio;

import java.util.ArrayList;
import java.util.List;

/**
 * Nivel Intermedio - Ejercicio 9: Almacenable&lt;T extends Comparable&lt;T&gt;&gt;
 * <p>
 * Se define un contrato ({@code Almacenable}) con dos operaciones muy
 * puntuales: guardar un elemento y consultar el máximo guardado hasta el
 * momento. La implementación concreta, {@code AlmacenSimple}, decide cómo
 * se guardan internamente los datos (en este caso, en una lista) y cómo se
 * calcula el máximo.
 * </p>
 *
 * <b>SOLID:</b>
 * <ul>
 * <li><b>Segregación de Interfaces (ISP):</b> la interfaz es pequeña y
 * puntual, solo tiene lo que un "almacén" realmente necesita, sin métodos
 * de más que nadie vaya a usar.</li>
 * <li><b>Abierto/Cerrado (OCP):</b> si mañana necesitamos un almacén que
 * guarde en base de datos en vez de en memoria, basta con crear otra clase
 * que implemente {@code Almacenable}, sin tocar el contrato ni el código
 * que ya lo usa.</li>
 * </ul>
 *
 * @param <T> tipo de los elementos que se guardan, debe ser comparable
 */
public class Ejercicio09_Almacenable {

    interface Almacenable<T extends Comparable<T>> {
        void guardar(T item);

        T maximo();
    }

    /**
     * Implementación sencilla de Almacenable respaldada por una lista.
     */
    static class AlmacenSimple<T extends Comparable<T>> implements Almacenable<T> {

        private final List<T> elementos = new ArrayList<>();

        @Override
        public void guardar(T item) {
            elementos.add(item);
        }

        @Override
        public T maximo() {
            if (elementos.isEmpty()) {
                throw new IllegalStateException("Todavía no se ha guardado ningún elemento");
            }
            T mayor = elementos.get(0);
            for (T elemento : elementos) {
                if (elemento.compareTo(mayor) > 0) {
                    mayor = elemento;
                }
            }
            return mayor;
        }
    }

    public static void main(String[] args) {
        Almacenable<Integer> almacenDePuntajes = new AlmacenSimple<>();
        almacenDePuntajes.guardar(120);
        almacenDePuntajes.guardar(340);
        almacenDePuntajes.guardar(75);
        almacenDePuntajes.guardar(280);

        System.out.println("Puntaje máximo guardado hasta ahora: " + almacenDePuntajes.maximo());
    }
}
