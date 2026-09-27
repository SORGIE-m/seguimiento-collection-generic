package generics.intermedio;

/**
 * Nivel Intermedio - Ejercicio 6: CajaNumerica&lt;T extends Number&gt;
 * <p>
 * Es una variación de la Caja del ejercicio básico, pero esta vez
 * restringida a números. Al poner el límite {@code T extends Number} le
 * estamos diciendo al compilador "aquí solo entran Integer, Double, Float,
 * Long... cualquier cosa que sea un número", y a cambio ganamos la
 * posibilidad de usar métodos propios de Number como {@code doubleValue()}.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase sigue haciendo una
 * sola cosa, guardar un número y calcular su doble. No se mezcla con
 * validaciones de negocio ni con impresión de resultados.
 *
 * @param <T> cualquier subtipo de Number (Integer, Double, Float, etc.)
 */
public class CajaNumerica<T extends Number> {

    private T valor;

    public CajaNumerica(T valor) {
        this.valor = valor;
    }

    /**
     * Calcula el doble del número guardado.
     * Se devuelve como double para no perder precisión, sin importar si
     * el valor original era un Integer, un Float o un Double.
     *
     * @return el valor guardado multiplicado por dos
     */
    public double doble() {
        return valor.doubleValue() * 2;
    }

    public T obtenerValor() {
        return valor;
    }

    public static void main(String[] args) {
        CajaNumerica<Integer> cajaEntera = new CajaNumerica<>(15);
        System.out.println("Valor guardado: " + cajaEntera.obtenerValor());
        System.out.println("El doble es: " + cajaEntera.doble());

        CajaNumerica<Double> cajaDecimal = new CajaNumerica<>(3.75);
        System.out.println("Valor guardado: " + cajaDecimal.obtenerValor());
        System.out.println("El doble es: " + cajaDecimal.doble());
    }
}
