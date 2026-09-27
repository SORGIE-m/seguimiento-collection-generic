package generics.avanzado;

/**
 * Nivel Avanzado - Ejercicio 15: CalculadoraAvanzada&lt;T extends Number &amp; Comparable&lt;T&gt;&gt;
 * <p>
 * Una pequeña calculadora que funciona con cualquier tipo numérico y
 * comparable: suma, resta, y encuentra el máximo o el mínimo entre dos
 * valores. Es básicamente la unión de las ideas de los ejercicios
 * anteriores (CajaNumerica y Comparador) en una sola clase con más
 * operaciones.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> aunque tiene cuatro métodos,
 * todos giran alrededor de una sola responsabilidad, operar sobre dos
 * valores numéricos comparables. No valida entradas de usuario, no
 * imprime resultados por su cuenta y no conoce nada de la interfaz gráfica
 * ni de dónde vienen los números.
 *
 * @param <T> tipo numérico y comparable sobre el que opera la calculadora
 */
public class Ejercicio15_CalculadoraAvanzada {

    static class CalculadoraAvanzada<T extends Number & Comparable<T>> {

        double sumar(T a, T b) {
            return a.doubleValue() + b.doubleValue();
        }

        double restar(T a, T b) {
            return a.doubleValue() - b.doubleValue();
        }

        T maximo(T a, T b) {
            return a.compareTo(b) >= 0 ? a : b;
        }

        T minimo(T a, T b) {
            return a.compareTo(b) <= 0 ? a : b;
        }
    }

    public static void main(String[] args) {
        CalculadoraAvanzada<Integer> calculadoraDeEnteros = new CalculadoraAvanzada<>();
        System.out.println("Suma de 12 y 8: " + calculadoraDeEnteros.sumar(12, 8));
        System.out.println("Resta de 12 y 8: " + calculadoraDeEnteros.restar(12, 8));
        System.out.println("Máximo entre 12 y 8: " + calculadoraDeEnteros.maximo(12, 8));
        System.out.println("Mínimo entre 12 y 8: " + calculadoraDeEnteros.minimo(12, 8));

        CalculadoraAvanzada<Double> calculadoraDeDecimales = new CalculadoraAvanzada<>();
        System.out.println("Suma de 5.5 y 2.25: " + calculadoraDeDecimales.sumar(5.5, 2.25));
        System.out.println("Máximo entre 5.5 y 2.25: " + calculadoraDeDecimales.maximo(5.5, 2.25));
    }
}
