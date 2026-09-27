package collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Ejercicio 2 - Pila que solo acepta elementos del mismo tipo que la cima
 * <p>
 * La pila puede recibir objetos de cualquier tipo, pero una vez hay algo
 * en la cima, solo se permite apilar elementos de esa misma clase. Por
 * ejemplo, si la cima es un Integer, no se puede apilar un String encima
 * hasta que la pila quede vacía de nuevo.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase solo administra la
 * pila y valida el tipo antes de apilar; no decide qué hacer si el tipo no
 * coincide (eso lo maneja quien la use, según el valor booleano que
 * devuelve {@code apilar}).
 */
public class Ejercicio02_PilaTipada {

    static class PilaTipada {

        private final Deque<Object> elementos = new ArrayDeque<>();

        /**
         * Intenta apilar un elemento. Si la pila está vacía, se acepta
         * cualquier tipo; si ya hay algo, el nuevo elemento debe ser de la
         * misma clase que el que está en la cima.
         *
         * @param elemento el objeto a apilar
         * @return true si se apiló, false si el tipo no coincidía
         */
        boolean apilar(Object elemento) {
            if (!elementos.isEmpty()) {
                Object cima = elementos.peek();
                if (!cima.getClass().equals(elemento.getClass())) {
                    return false;
                }
            }
            elementos.push(elemento);
            return true;
        }

        Object desapilar() {
            if (elementos.isEmpty()) {
                throw new NoSuchElementException("La pila está vacía");
            }
            return elementos.pop();
        }

        Object verCima() {
            return elementos.peek();
        }

        boolean estaVacia() {
            return elementos.isEmpty();
        }

        int tamano() {
            return elementos.size();
        }
    }

    public static void main(String[] args) {
        PilaTipada pila = new PilaTipada();

        System.out.println("¿Se apiló 10? " + pila.apilar(10));
        System.out.println("¿Se apiló 25? " + pila.apilar(25));
        System.out.println("¿Se apiló \"hola\"? " + pila.apilar("hola")); // debería fallar, la cima es Integer

        System.out.println("Cima actual: " + pila.verCima());
        System.out.println("Tamaño de la pila: " + pila.tamano());

        pila.desapilar();
        pila.desapilar();
        System.out.println("¿Pila vacía tras desapilar dos veces? " + pila.estaVacia());

        System.out.println("¿Se apiló \"hola\" ahora que está vacía? " + pila.apilar("hola"));
    }
}
