package collections;

import java.util.TreeSet;

/**
 * Ejercicio 1 - Empresa con TreeSet de productos, búsqueda por código
 * <p>
 * {@code Producto} implementa {@code Comparable<Producto>} comparando por
 * código, así que al guardarlos en un TreeSet quedan ordenados
 * automáticamente sin que la Empresa tenga que preocuparse por eso.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> Producto solo representa los
 * datos de un producto y sabe compararse por código; Empresa se encarga
 * únicamente de administrar la colección y de buscar dentro de ella.
 *
 * <p>Para la búsqueda se aprovecha que un TreeSet es un NavigableSet: en
 * vez de recorrer producto por producto, se usa {@code ceiling()} para ir
 * directo al candidato más cercano por código y solo se confirma que sea
 * una coincidencia exacta.</p>
 */
public class Ejercicio01_EmpresaTreeSet {

    static class Producto implements Comparable<Producto> {
        private final int codigo;
        private final String nombre;
        private final double precio;

        Producto(int codigo, String nombre, double precio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
        }

        int getCodigo() {
            return codigo;
        }

        // Orden natural: por código, que es el identificador único del producto
        @Override
        public int compareTo(Producto otro) {
            return Integer.compare(this.codigo, otro.codigo);
        }

        @Override
        public String toString() {
            return "[" + codigo + "] " + nombre + " - $" + precio;
        }
    }

    static class Empresa {

        private final TreeSet<Producto> productos = new TreeSet<>();

        void agregarProducto(Producto producto) {
            productos.add(producto);
        }

        /**
         * Busca un producto por su código apoyándose en el orden del TreeSet.
         *
         * @param codigo código del producto a buscar
         * @return el producto si existe, o null si no se encontró
         */
        Producto buscarPorCodigo(int codigo) {
            Producto comodin = new Producto(codigo, "", 0);
            Producto candidato = productos.ceiling(comodin);
            if (candidato != null && candidato.getCodigo() == codigo) {
                return candidato;
            }
            return null;
        }

        TreeSet<Producto> getProductos() {
            return productos;
        }
    }

    public static void main(String[] args) {
        Empresa joyeria = new Empresa();
        joyeria.agregarProducto(new Producto(101, "Anillo de plata", 85000));
        joyeria.agregarProducto(new Producto(305, "Cadena de oro", 450000));
        joyeria.agregarProducto(new Producto(202, "Pulsera artesanal", 32000));

        System.out.println("Productos ordenados por código: " + joyeria.getProductos());

        Producto encontrado = joyeria.buscarPorCodigo(202);
        System.out.println("Búsqueda del código 202: " + encontrado);

        Producto noExiste = joyeria.buscarPorCodigo(999);
        System.out.println("Búsqueda del código 999: " + noExiste);
    }
}
