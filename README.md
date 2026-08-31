# EA1 - Diagrama de Clases UML — Sistema de Biblioteca

Repositorio de la actividad **EA1: Diagrama de Clases UML**, correspondiente a la asignatura de Programación Orientada a Objetos. Contiene el diseño de un sistema de biblioteca aplicando abstracción, encapsulamiento, herencia, polimorfismo, sobrecarga/sobrescritura y principios SOLID.

## Integrantes

- [Nombre completo integrante 1]
- [Nombre completo integrante 2]
- [Nombre completo integrante 3]

**Grupo:** [X]

## Descripción del sistema

El sistema modela la gestión de una biblioteca: administración de libros (físicos y digitales), sus autores, y el registro de préstamos realizados sobre dichos libros.

## Clases del diagrama

| Clase | Responsabilidad |
|---|---|
| `Autor` | Representa al escritor de un libro (nombre, nacionalidad). |
| `Libro` (abstracta) | Representa un libro del catálogo: título, autor, disponibilidad. |
| `LibroDigital` | Hereda de `Libro`; añade formato de archivo y tamaño, y sobrescribe `prestar()`. |
| `Prestamo` | Registra el préstamo de un libro con fecha de préstamo y devolución. |
| `Biblioteca` | Coordina el catálogo de libros y el registro de préstamos. |

## Relaciones UML aplicadas

- **Herencia**: `LibroDigital` → `Libro`
- **Asociación**: `Libro` → `Autor` (0..* a 1)
- **Agregación**: `Biblioteca` ◇— `Libro` (1 a 0..*)
- **Composición**: `Biblioteca` ♦— `Prestamo` (1 a 0..*) y `Prestamo` ♦— `Libro` (1 a 1)

La justificación detallada de cada relación, así como el análisis de cohesión, acoplamiento y principios SOLID, se encuentra en el informe PDF/Word entregado en la plataforma.

## Contenido del repositorio

```
├── diagrama_biblioteca.puml   # Código fuente del diagrama (PlantUML)
├── diagrama_biblioteca.png    # Imagen exportada del diagrama
└── README.md
```