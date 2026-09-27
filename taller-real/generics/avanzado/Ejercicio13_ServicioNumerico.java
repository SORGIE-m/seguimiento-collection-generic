package generics.avanzado;

import java.util.List;

/**
 * Nivel Avanzado - Ejercicio 13: Servicio&lt;T extends Number &amp; Comparable&lt;T&gt;&gt;
 * <p>
 * Se define la interfaz {@code Servicio} con dos operaciones típicas de
 * cualquier capa de servicio que trabaje con listas de números: encontrar
 * el mínimo y el máximo. {@code ServicioNumerico} es la implementación que
 * realmente recorre la lista y calcula esos valores.
 * </p>
 *
 * <b>SOLID:</b>
 * <ul>
 * <li><b>Segregación de Interfaces (ISP):</b> la interfaz solo tiene los
 * dos métodos que de verdad se necesitan, nada de operaciones que no
 * apliquen a todos los que la implementen.</li>
 * <li><b>Inversión de Dependencias (DIP):</b> en un proyecto real, otras
 * clases dependerían de la interfaz {@code Servicio}, no directamente de
 * {@code ServicioNumerico}, así que se podría cambiar la implementación
 * (por ejemplo, una que use streams o una que consulte una base de datos)
 * sin afectar a quien la usa.</li>
 * </ul>
 *
 * @param <T> tipo numérico y comparable sobre el que trabaja el servicio
 */
public class Ejercicio13_ServicioNumerico {

    interface Servicio<T extends Number & Comparable<T>> {
        T minimo(List<T> lista);

        T maximo(List<T> lista);
    }

    static class ServicioNumerico<T extends Number & Comparable<T>> implements Servicio<T> {

        @Override
        public T minimo(List<T> lista) {
            validarNoVacia(lista);
            T menor = lista.get(0);
            for (T valor : lista) {
                if (valor.compareTo(menor) < 0) {
                    menor = valor;
                }
            }
            return menor;
        }

        @Override
        public T maximo(List<T> lista) {
            validarNoVacia(lista);
            T mayor = lista.get(0);
            for (T valor : lista) {
                if (valor.compareTo(mayor) > 0) {
                    mayor = valor;
                }
            }
            return mayor;
        }

        private void validarNoVacia(List<T> lista) {
            if (lista == null || lista.isEmpty()) {
                throw new IllegalArgumentException("La lista no puede estar vacía ni ser nula");
            }
        }
    }

    public static void main(String[] args) {
        Servicio<Double> servicioDeCalificaciones = new ServicioNumerico<>();
        List<Double> calificaciones = List.of(3.5, 4.8, 2.9, 4.2, 3.9);

        System.out.println("Calificación mínima: " + servicioDeCalificaciones.minimo(calificaciones));
        System.out.println("Calificación máxima: " + servicioDeCalificaciones.maximo(calificaciones));
    }
}
