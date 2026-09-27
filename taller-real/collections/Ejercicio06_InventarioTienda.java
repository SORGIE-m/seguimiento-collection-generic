package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Ejercicio 6 - Inventario de una tienda usando ArrayList
 * <p>
 * Permite agregar productos, eliminar los que están agotados, buscar uno
 * en concreto por su código y listar el inventario ordenado tanto
 * alfabéticamente como por precio.
 * </p>
 *
 * <b>SOLID:</b>
 * <ul>
 * <li><b>Responsabilidad Única (SRP):</b> Producto solo guarda sus datos;
 * InventarioTienda administra la colección y las operaciones sobre ella.
 * Ninguna imprime nada por su cuenta.</li>
 * <li><b>Abierto/Cerrado (OCP):</b> los métodos de listado reciben o
 * construyen un {@code Comparator}, así que agregar un nuevo criterio de
 * orden (por ejemplo, por cantidad disponible) no obliga a modificar los
 * métodos existentes, solo a agregar uno nuevo.</li>
 * </ul>
 * <p>
 * Para eliminar los productos agotados se usa {@code Iterator.remove()} en
 * vez de intentar borrar mientras se recorre con un for-each, que
 * lanzaría una {@code ConcurrentModificationException}.
 * </p>
 */
public class Ejercicio06_InventarioTienda {

    static class Producto {
        private final int codigo;
        private final String nombre;
        private final double precio;
        private int cantidadDisponible;

        Producto(int codigo, String nombre, double precio, int cantidadDisponible) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.cantidadDisponible = cantidadDisponible;
        }

        int getCodigo() {
            return codigo;
        }

        String getNombre() {
            return nombre;
        }

        double getPrecio() {
            return precio;
        }

        int getCantidadDisponible() {
            return cantidadDisponible;
        }

        @Override
        public String toString() {
            return "[" + codigo + "] " + nombre + " - $" + precio + " (stock: " + cantidadDisponible + ")";
        }
    }

    static class InventarioTienda {

        private final List<Producto> productos = new ArrayList<>();

        void agregarProducto(Producto producto) {
            productos.add(producto);
        }

        /**
         * Elimina del inventario todos los productos con cantidad disponible
         * en cero, recorriendo la lista con Iterator para poder borrar de
         * forma segura mientras se recorre.
         */
        void eliminarAgotados() {
            Iterator<Producto> iterador = productos.iterator();
            while (iterador.hasNext()) {
                Producto producto = iterador.next();
                if (producto.getCantidadDisponible() == 0) {
                    iterador.remove();
                }
            }
        }

        Producto buscarPorCodigo(int codigo) {
            for (Producto producto : productos) {
                if (producto.getCodigo() == codigo) {
                    return producto;
                }
            }
            return null;
        }

        List<Producto> listarPorNombre() {
            List<Producto> copia = new ArrayList<>(productos);
            copia.sort(Comparator.comparing(Producto::getNombre));
            return copia;
        }

        List<Producto> listarPorPrecio() {
            List<Producto> copia = new ArrayList<>(productos);
            copia.sort(Comparator.comparingDouble(Producto::getPrecio));
            return copia;
        }
    }

    public static void main(String[] args) {
        InventarioTienda tienda = new InventarioTienda();
        tienda.agregarProducto(new Producto(1, "Teclado mecánico", 180000, 5));
        tienda.agregarProducto(new Producto(2, "Mouse inalámbrico", 65000, 0));
        tienda.agregarProducto(new Producto(3, "Audífonos bluetooth", 120000, 8));
        tienda.agregarProducto(new Producto(4, "Cargador USB-C", 35000, 0));

        System.out.println("Buscando el producto con código 3: " + tienda.buscarPorCodigo(3));

        tienda.eliminarAgotados();
        System.out.println("Inventario luego de quitar los agotados:");
        System.out.println("Ordenado alfabéticamente: " + tienda.listarPorNombre());
        System.out.println("Ordenado por precio: " + tienda.listarPorPrecio());
    }
}
