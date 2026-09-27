package generics.basico;

/**
 * Nivel Básico - Ejercicio 1: Caja&lt;T&gt;
 * <p>
 * La idea es la más simple posible dentro de Generics: una caja que puede
 * guardar cualquier cosa (un texto, un número, un objeto propio) sin tener
 * que escribir una clase distinta por cada tipo de dato.
 * </p>
 *
 * <b>Principio SOLID aplicado - Responsabilidad Única (SRP):</b> esta clase
 * solo sabe hacer dos cosas, guardar un valor y devolverlo. No valida datos
 * de negocio, no imprime nada por su cuenta y no sabe nada del mundo
 * exterior; si mañana cambia la forma de "guardar" algo más complejo, esa
 * lógica debería vivir en otra clase, no aquí.
 *
 * @param <T> tipo de dato que la caja va a contener
 */
public class Caja<T> {

    private T contenido;

    public Caja() {
        // Caja vacía, lista para usarse
    }

    public Caja(T contenidoInicial) {
        this.contenido = contenidoInicial;
    }

    /**
     * Reemplaza lo que hay dentro de la caja por un nuevo valor.
     *
     * @param valor el nuevo contenido de la caja
     */
    public void guardar(T valor) {
        this.contenido = valor;
    }

    /**
     * Devuelve lo que actualmente está guardado en la caja.
     *
     * @return el contenido actual (puede ser null si nunca se guardó nada)
     */
    public T obtener() {
        return contenido;
    }

    public static void main(String[] args) {
        // Una caja de texto
        Caja<String> cajaDeTexto = new Caja<>();
        cajaDeTexto.guardar("Primer laboratorio de Generics");
        System.out.println("Contenido de la caja de texto: " + cajaDeTexto.obtener());

        // Una caja de números, esta vez con contenido inicial en el constructor
        Caja<Integer> cajaDeNumeros = new Caja<>(10);
        System.out.println("Valor inicial de la caja numérica: " + cajaDeNumeros.obtener());
        cajaDeNumeros.guardar(99);
        System.out.println("Valor luego de guardar de nuevo: " + cajaDeNumeros.obtener());

        // Y hasta una caja que guarda otra caja, para comprobar que no hay límite
        Caja<Caja<String>> cajaDeCajas = new Caja<>(cajaDeTexto);
        System.out.println("Contenido de la caja dentro de la caja: " + cajaDeCajas.obtener().obtener());
    }
}
