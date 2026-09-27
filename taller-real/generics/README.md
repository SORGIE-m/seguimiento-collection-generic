# Ejercicios de Generics - Taller real

Estos ejercicios están tomados directamente del documento
`Ejercicios_Generics.pdf` que enviaste. Se seleccionaron:

- **1 ejercicio del nivel básico**
- **3 ejercicios del nivel intermedio**
- **3 ejercicios del nivel avanzado**
- **3 de los "Enunciados"** (los más largos, con Iterator, Comparable y
  Comparator)

## 📂 Estructura

```
generics/
├── basico/
│   └── Ejercicio01_Caja.java
├── intermedio/
│   ├── Ejercicio06_CajaNumerica.java
│   ├── Ejercicio08_Comparador.java
│   └── Ejercicio09_Almacenable.java
├── avanzado/
│   ├── Ejercicio11_EntidadPersistente.java
│   ├── Ejercicio13_ServicioNumerico.java
│   └── Ejercicio15_CalculadoraAvanzada.java
└── enunciados/
    ├── Ejercicio01_InventarioCaja.java
    ├── Ejercicio02_DirectorioContactos.java
    └── Ejercicio08_ListaTareas.java
```

## 📑 Qué contiene cada uno

| Carpeta | # original en el PDF | Clase | Tema central |
|---|---|---|---|
| básico | 1 | `Caja<T>` | Clase genérica simple |
| intermedio | 6 | `CajaNumerica<T extends Number>` | Restricción con `extends Number` |
| intermedio | 8 | `Comparador<T extends Comparable<T>>` | Restricción con `Comparable` |
| intermedio | 9 | `Almacenable<T extends Comparable<T>>` | Interfaz genérica + implementación |
| avanzado | 11 | `EntidadPersistente<T extends Number & Comparable<T>>` | Restricciones múltiples |
| avanzado | 13 | `Servicio<T extends Number & Comparable<T>>` | Interfaz + implementación con `List<T>` |
| avanzado | 15 | `CalculadoraAvanzada<T extends Number & Comparable<T>>` | Varias operaciones numéricas |
| enunciado | 1 | `InventarioCaja<T>` | Filtro con **solo** `Iterator`, sin for-each |
| enunciado | 2 | `DirectorioContactos` + `Contacto` | Orden natural (`Comparable`) + `Comparator` |
| enunciado | 8 | `ListaTareas<T>` | `Iterable<T>` + iterador inverso personalizado |

## 🧱 Sobre los principios SOLID aplicados

Cada clase trae en su Javadoc una nota puntual de qué principio aplica y
por qué, pero en resumen:

- **SRP (Responsabilidad Única):** cada clase hace una sola cosa. `Caja`
  guarda y entrega un valor, nada más; `DirectorioContactos` administra
  contactos, pero no decide cómo se ven ni de dónde vienen.
- **OCP (Abierto/Cerrado):** las interfaces (`Almacenable`, `Servicio`)
  permiten agregar nuevas implementaciones sin tocar el contrato ya
  existente.
- **LSP (Sustitución de Liskov):** las restricciones como
  `T extends Number & Comparable<T>` garantizan que cualquier tipo
  concreto que se use se comporte de forma consistente dentro de la
  clase, sin romper nada.
- **ISP (Segregación de Interfaces):** `Almacenable` y `Servicio` son
  interfaces pequeñas, con solo los métodos que de verdad se necesitan.
- **DIP (Inversión de Dependencias):** por ejemplo, el orden por
  teléfono en `DirectorioContactos` se recibe como un `Comparator`
  externo en vez de estar fijo dentro de `Contacto`.

## ▶️ Cómo ejecutar cada ejercicio

```bash
javac -d out generics/basico/Ejercicio01_Caja.java
java -cp out generics.basico.Ejercicio01_Caja

# o compilar todo de una vez
javac -d out $(find generics -name "*.java")
java -cp out generics.enunciados.Ejercicio08_ListaTareas
```

## Nota sobre el laboratorio de Collections

El documento `Collections_Taller.pdf` que enviaste trae 17 ejercicios
distintos a los que había hecho antes (productos con TreeSet, pila con
restricción de tipo, turnos de banco, Vector para deshacer cambios,
navegador web con Stack, etc.). Dime cuáles 9 (o más) quieres que
desarrolle de esa lista y te armo esa parte con el mismo nivel de
documentación y SOLID que estos de Generics.
