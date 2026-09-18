# EA2 - Implementación en Java del Diagrama de Clases UML — Sistema de Biblioteca

Repositorio de la actividad **EA2: Implementación en Java del Diagrama de Clases UML**,
correspondiente a la asignatura de Programación Orientada a Objetos II - Avanzada.
Implementa en Java el diagrama de clases diseñado en la Actividad 1 (Sistema de Biblioteca).

## Integrantes

- [Nombre completo integrante 1]
- [DEIVER ALONSO PORRAS ALVAREZ]

## Descripción del sistema

El sistema modela la gestión de una biblioteca: administración de libros (físicos y
digitales), sus autores, y el registro de préstamos realizados sobre dichos libros.
Esta versión traduce a código Java el diseño UML de la Actividad 1.

## Estructura del proyecto

```
biblioteca-poo/
├── src/
│   ├── modelo/
│   │   ├── Autor.java
│   │   ├── Libro.java          (clase abstracta)
│   │   ├── LibroDigital.java   (hereda de Libro)
│   │   └── Prestamo.java
│   ├── servicio/
│   │   └── Biblioteca.java
│   └── main/
│       └── Main.java
└── README.md
```

## Cómo compilar y ejecutar

```bash
cd src
javac -encoding UTF-8 modelo/*.java servicio/*.java main/*.java
java -Dfile.encoding=UTF-8 main.Main
```

## Relaciones UML traducidas a código

| Relación UML | Implementación en Java |
|---|---|
| Herencia (`LibroDigital` --|> `Libro`) | `class LibroDigital extends Libro` |
| Asociación (`Libro` → `Autor`) | Atributo `private Autor autor;` dentro de `Libro` |
| Agregación (`Biblioteca` o-- `Libro`) | `List<Libro> libros` en `Biblioteca`; los libros pueden existir independientemente de la biblioteca |
| Composición (`Prestamo` *-- `Libro`) | Atributo `private Libro libro;` en `Prestamo`, parte esencial e inseparable del préstamo |
| Composición (`Biblioteca` *-- `Prestamo`) | `List<Prestamo> prestamos` en `Biblioteca`; los préstamos no existen fuera de la biblioteca que los gestiona |

## Pilares de POO aplicados

- **Abstracción**: `Libro` es una clase abstracta que define el contrato común de
  cualquier libro, sin implementar los detalles particulares de cada tipo.
- **Encapsulamiento**: todos los atributos son `private` o `protected`, expuestos
  únicamente mediante getters/setters.
- **Herencia**: `LibroDigital extends Libro`.
- **Polimorfismo**: `Biblioteca` almacena objetos `Libro`, pero en tiempo de
  ejecución puede contener instancias de `LibroDigital` (o futuras subclases),
  y cada una responde según su propio comportamiento (ver `Main.java`).
- **Sobrescritura (@Override)**: `LibroDigital.prestar()` sobrescribe
  `Libro.prestar()`.
- **Sobrecarga**: `Biblioteca.agregarLibro(Libro)` y
  `Biblioteca.agregarLibro(Libro, int cantidadCopias)`.

## Principios SOLID aplicados

1. **SRP (Single Responsibility Principle)**: cada clase tiene una única razón
   para cambiar. Ej.: `Autor` solo gestiona datos del escritor, `Prestamo` solo
   gestiona fechas y estado del préstamo, `Biblioteca` solo coordina catálogo y
   préstamos (ver comentarios en el código fuente).
2. **OCP (Open/Closed Principle)**: `Libro` está abierta a extensión (nuevas
   subclases como `LibroDigital`) y cerrada a modificación: no fue necesario
   tocar `Libro` para agregar el comportamiento digital.
3. **LSP (Liskov Substitution Principle)**: cualquier `LibroDigital` puede
   usarse donde se espera un `Libro` (por ejemplo en `List<Libro>` dentro de
   `Biblioteca`) sin romper el programa.
4. **DIP (Dependency Inversion Principle)**: `Biblioteca` depende de la
   abstracción `Libro`, no de clases concretas como `LibroDigital`.
5. **ISP (Interface Segregation Principle)**: las interfaces y clases están diseñadas
   para que las clases no se vean obligadas a implementar métodos que no utilizan.  

La justificación detallada, capturas de ejecución y el análisis completo se
encuentran en el informe PDF entregado en la plataforma (normas APA 7.ª ed.).

## Enlaces

- Video de sustentación: [pendiente de agregar]
