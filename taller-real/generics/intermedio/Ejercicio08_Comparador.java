package generics.intermedio;

/**
 * Nivel Intermedio - Ejercicio 8: Comparador&lt;T extends Comparable&lt;T&gt;&gt;
 * <p>
 * Aquí la restricción cambia: en vez de pedir números, se le pide a T que
 * sepa compararse consigo mismo, es decir, que implemente
 * {@code Comparable<T>}. Gracias a eso podemos escribir un solo método
 * {@code mayor(a, b)} que sirve tanto para números como para Strings o
 * cualquier clase propia que implemente Comparable.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> esta clase únicamente decide
 * cuál de dos elementos es "mayor"; no hace nada más. Al depender de la
 * abstracción {@code Comparable<T>} y no de una clase concreta como
 * Integer o String, también estamos aplicando un poco de Inversión de
 * Dependencias (DIP): el Comparador no le importa qué tipo exacto está
 * comparando, solo que sepa compararse.
 *
 * @param <T> tipo de dato que implementa Comparable consigo mismo
 */
public class Comparador<T extends Comparable<T>> {

    /**
     * Devuelve el mayor entre dos elementos comparables.
     * Si son iguales, se devuelve el primero.
     *
     * @param a primer elemento
     * @param b segundo elemento
     * @return el elemento más grande según su orden natural
     */
    public T mayor(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static void main(String[] args) {
        Comparador<Integer> comparadorDeNumeros = new Comparador<>();
        System.out.println("El mayor entre 15 y 42 es: " + comparadorDeNumeros.mayor(15, 42));

        Comparador<String> comparadorDeTextos = new Comparador<>();
        System.out.println("El mayor (alfabéticamente) entre 'manzana' y 'pera' es: "
                + comparadorDeTextos.mayor("manzana", "pera"));
    }
}
