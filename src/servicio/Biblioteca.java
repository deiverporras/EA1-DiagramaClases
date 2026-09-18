package servicio;

import modelo.Libro;
import modelo.Prestamo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


 //Clase Biblioteca (paquete servicio)

public class Biblioteca {

    private String nombreBiblioteca;
    private List<Libro> libros;
    private List<Prestamo> prestamos;

    public Biblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
        this.libros = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    // Sobrecarga (1/2): agrega un único ejemplar de un libro al catálogo.
    // Mismo nombre de método, distinta lista de parámetros.
    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado al catálogo: " + libro.getTitulo());
    }
    // Sobrecarga (2/2): agrega el mismo libro repetido "cantidadCopias"
    // veces, simulando múltiples ejemplares físicos del mismo título.
    // Mismo nombre de método, distinta lista de parámetros.
    public void agregarLibro(Libro libro, int cantidadCopias) {
        for (int i = 0; i < cantidadCopias; i++) {
            libros.add(libro);
        }
        System.out.println(cantidadCopias + " copias de \"" + libro.getTitulo()
                + "\" agregadas al catálogo.");
    }

    // Busca el primer libro del catálogo cuyo título coincida
    // (ignorando mayúsculas/minúsculas).
    public Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

     //Registra un nuevo préstamo para el libro indicado, siempre que
     //esté disponible, y lo agrega a la lista de préstamos gestionados
     // por la biblioteca (composición).
    public Prestamo registrarPrestamo(Libro libro) {
        if (!libro.getEstado()) {
            return null;
        }
        libro.prestar();
        Prestamo prestamo = new Prestamo(libro, LocalDate.now());
        prestamos.add(prestamo);
        return prestamo;
    }

    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}
