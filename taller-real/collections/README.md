# Ejercicios de Collections - Taller real

10 de los 17 ejercicios de `Collections_Taller.pdf`, elegidos para cubrir
la mayor variedad posible de estructuras sin repetir tipo. Mismo nivel de
documentación, SOLID y nombres en español que la parte de Generics.

## 📂 Ejercicios incluidos

| # original | Clase | Estructura | Caso de uso |
|---|---|---|---|
| 1 | `Ejercicio01_EmpresaTreeSet` | `TreeSet<Producto>` | Productos ordenados por código, búsqueda con `ceiling()` |
| 2 | `Ejercicio02_PilaTipada` | `ArrayDeque` como pila | Pila que solo acepta el tipo de la cima |
| 3 | `Ejercicio03_ListaSinDuplicados` | `ArrayList` + `Iterator` | Lista sin duplicados, impresión con iterador |
| 4 | `Ejercicio04_ColaTareasPrioridad` | `PriorityQueue<Tarea>` | Tareas atendidas por importancia |
| 6 | `Ejercicio06_InventarioTienda` | `ArrayList<Producto>` | Agregar, eliminar agotados, buscar y listar (por nombre y precio) |
| 7 | `Ejercicio07_TurnosBanco` | `LinkedList<String>` | Fila de turnos con inserción urgente al inicio |
| 9 | `Ejercicio09_NavegadorWeb` | `Stack<String>` | Historial de navegación LIFO |
| 10 | `Ejercicio10_ControlAcceso` | `HashSet<String>` | IDs de empleados sin duplicados |
| 14 | `Ejercicio14_HistorialMensajes` | `ArrayDeque<String>` | Últimos 10 mensajes enviados |
| 17 | `Ejercicio17_AgendaEventos` | `TreeMap<LocalDate, String>` | Eventos ordenados cronológicamente |

## 🧱 SOLID aplicado

Cada clase trae su nota puntual en el Javadoc, pero en general:

- **SRP:** las clases de datos (`Producto`, `Tarea`) solo representan
  información y comparación; las clases "gestoras" (`Empresa`,
  `InventarioTienda`, `GestorTareas`, etc.) solo administran la colección.
- **OCP:** en `InventarioTienda`, los criterios de orden se arman con
  `Comparator` en vez de estar fijos dentro de `Producto`, así que agregar
  un nuevo criterio de orden no obliga a tocar código existente.

## ⚠️ Quedaron pendientes (por si los quieres después)

De los 17 originales, no se incluyeron: #5 (comparar HashMap vs
LinkedHashMap vs TreeMap), #8 (Vector con función deshacer), #11
(LinkedHashSet de canciones favoritas), #12 (TreeSet de estudiantes), #13
(PriorityQueue de pacientes — se repetía la estructura con el #4), #15
(HashMap de directorio telefónico) y #16 (LinkedHashMap de compras del
supermercado). Dime si quieres que arme también alguno de esos.

## ▶️ Cómo ejecutar

```bash
javac -d out collections/Ejercicio01_EmpresaTreeSet.java
java -cp out collections.Ejercicio01_EmpresaTreeSet

# o compilar todo de una vez
javac -d out $(find collections -name "*.java")
java -cp out collections.Ejercicio17_AgendaEventos
```
