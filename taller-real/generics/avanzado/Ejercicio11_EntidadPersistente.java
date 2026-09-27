package generics.avanzado;

/**
 * Nivel Avanzado - Ejercicio 11: EntidadPersistente&lt;T extends Number &amp; Comparable&lt;T&gt;&gt;
 * <p>
 * Esta vez se combinan dos restricciones sobre el mismo tipo T: debe ser un
 * número (Number) y además debe poder compararse consigo mismo
 * (Comparable&lt;T&gt;). La idea de "EntidadPersistente" simula el típico caso
 * de una entidad que se guardaría en base de datos y que necesita un valor
 * numérico que sirva tanto para operaciones matemáticas como para
 * comparaciones (por ejemplo, un saldo o un puntaje).
 * </p>
 *
 * <b>SOLID - Sustitución de Liskov (LSP):</b> gracias a que T siempre va a
 * ser un Number que también es Comparable, cualquier tipo concreto que se
 * use (Integer, Double, Long...) se comporta de manera consistente dentro
 * de esta clase, sin sorpresas ni casos especiales que rompan el contrato.
 *
 * @param <T> tipo numérico y comparable que representa el valor de la entidad
 */
public class Ejercicio11_EntidadPersistente {

    static class EntidadPersistente<T extends Number & Comparable<T>> {

        private final T valor;

        EntidadPersistente(T valor) {
            this.valor = valor;
        }

        T obtenerValor() {
            return valor;
        }

        /**
         * Compara esta entidad contra otra del mismo tipo.
         *
         * @param otra la otra entidad a comparar
         * @return negativo si esta es menor, cero si son iguales, positivo si es mayor
         */
        int compararCon(EntidadPersistente<T> otra) {
            return this.valor.compareTo(otra.valor);
        }

        double aDouble() {
            return valor.doubleValue();
        }
    }

    public static void main(String[] args) {
        EntidadPersistente<Integer> saldoCuentaA = new EntidadPersistente<>(150000);
        EntidadPersistente<Integer> saldoCuentaB = new EntidadPersistente<>(98000);

        int resultado = saldoCuentaA.compararCon(saldoCuentaB);
        if (resultado > 0) {
            System.out.println("La cuenta A tiene más saldo que la cuenta B");
        } else if (resultado < 0) {
            System.out.println("La cuenta B tiene más saldo que la cuenta A");
        } else {
            System.out.println("Ambas cuentas tienen el mismo saldo");
        }

        System.out.println("Saldo de A como double: " + saldoCuentaA.aDouble());
    }
}
