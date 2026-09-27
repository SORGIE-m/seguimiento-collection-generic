package collections;

import java.util.HashSet;

/**
 * Ejercicio 10 - Control de acceso de un edificio usando HashSet
 * <p>
 * Cada empleado tiene un código único. Un HashSet es la estructura
 * perfecta para esto porque garantiza que no haya IDs duplicados y
 * permite verificar en tiempo prácticamente constante si un código ya
 * está registrado.
 * </p>
 *
 * <b>SOLID - Responsabilidad Única (SRP):</b> la clase solo decide quién
 * tiene acceso registrado y quién no; no sabe nada de torniquetes,
 * cámaras ni de cómo se identifica físicamente al empleado.
 */
public class Ejercicio10_ControlAcceso {

    static class ControlAcceso {

        private final HashSet<String> empleadosRegistrados = new HashSet<>();

        /**
         * Registra un nuevo empleado con acceso al edificio.
         *
         * @param idEmpleado código único del empleado
         * @return true si se registró, false si ya estaba registrado
         */
        boolean registrarEmpleado(String idEmpleado) {
            return empleadosRegistrados.add(idEmpleado);
        }

        boolean permitirIngreso(String idEmpleado) {
            return empleadosRegistrados.contains(idEmpleado);
        }

        void revocarAcceso(String idEmpleado) {
            empleadosRegistrados.remove(idEmpleado);
        }

        int totalEmpleadosConAcceso() {
            return empleadosRegistrados.size();
        }
    }

    public static void main(String[] args) {
        ControlAcceso control = new ControlAcceso();

        System.out.println("¿Se registró EMP-001? " + control.registrarEmpleado("EMP-001"));
        System.out.println("¿Se registró EMP-002? " + control.registrarEmpleado("EMP-002"));
        System.out.println("¿Se registró EMP-001 otra vez? " + control.registrarEmpleado("EMP-001"));

        System.out.println("¿EMP-002 puede ingresar? " + control.permitirIngreso("EMP-002"));
        System.out.println("¿EMP-999 puede ingresar? " + control.permitirIngreso("EMP-999"));

        control.revocarAcceso("EMP-001");
        System.out.println("¿EMP-001 puede ingresar tras revocarle el acceso? " + control.permitirIngreso("EMP-001"));

        System.out.println("Total de empleados con acceso vigente: " + control.totalEmpleadosConAcceso());
    }
}
