package collections;

import java.util.Stack;

/**
 * Ejercicio 9 - Historial de un navegador web usando Stack
 * <p>
 * Cada página visitada se apila; al retroceder, se saca la página actual
 * y queda visible la anterior. Es el comportamiento clásico LIFO (Last
 * In, First Out) que pide el enunciado.
 * </p>
 * <p>
 * Nota honesta: en código moderno normalmente se preferiría
 * {@code ArrayDeque} en vez de {@code java.util.Stack}, porque Stack es
 * una clase antigua (hereda de Vector) y está sincronizada aunque no se
 * necesite en la mayoría de los casos. Aquí se usa Stack de forma
 * literal porque el enunciado lo pide explícitamente.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase solo administra el
 * historial de navegación; no dibuja nada en pantalla ni sabe cómo
 * funciona el navegador por dentro.
 */
public class Ejercicio09_NavegadorWeb {

    static class NavegadorWeb {

        private final Stack<String> historial = new Stack<>();

        void visitarPagina(String url) {
            historial.push(url);
        }

        /**
         * Retrocede a la página anterior, eliminando la actual del
         * historial.
         *
         * @return la URL a la que se retrocedió
         */
        String retroceder() {
            if (historial.isEmpty()) {
                throw new IllegalStateException("No hay páginas para retroceder");
            }
            historial.pop();
            return historial.isEmpty() ? null : historial.peek();
        }

        String paginaActual() {
            return historial.isEmpty() ? null : historial.peek();
        }
    }

    public static void main(String[] args) {
        NavegadorWeb navegador = new NavegadorWeb();
        navegador.visitarPagina("uniquindio.edu.co");
        navegador.visitarPagina("uniquindio.edu.co/estructura-datos");
        navegador.visitarPagina("uniquindio.edu.co/estructura-datos/laboratorio3");

        System.out.println("Página actual: " + navegador.paginaActual());

        System.out.println("Retrocediendo una página: " + navegador.retroceder());
        System.out.println("Retrocediendo otra página: " + navegador.retroceder());
    }
}
