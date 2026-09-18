package main;

import java.util.Scanner;
import modelo.Autor;
import modelo.Libro;
import modelo.LibroDigital;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // 1. Lectura de datos del Autor
        System.out.println("=== DATOS DEL AUTOR ===");
        System.out.print("Nombre del autor: ");
        String nombreAutor = teclado.nextLine();

        System.out.print("Nacionalidad del autor: ");
        String nacionalidadAutor = teclado.nextLine();

        Autor autor = new Autor(nombreAutor, nacionalidadAutor);

        // 2. Lectura de datos del Libro
        System.out.println("\n=== DATOS DEL LIBRO DIGITAL ===");
        System.out.print("Título del libro: ");
        String titulo = teclado.nextLine();

        System.out.print("Formato del archivo (ej. PDF, EPUB): ");
        String formato = teclado.nextLine();

        System.out.print("Tamaño en MB: ");
        double tamanoMB = teclado.nextDouble();

        // Polimorfismo: La referencia es de tipo 'Libro', pero el objeto es
        // 'LibroDigital'
        Libro miLibro = new LibroDigital(titulo, autor, formato, tamanoMB);

        // 3. Muestra de resultados
        System.out.println("\n=== INFORMACIÓN REGISTRADA ===");
        System.out.println("Título: " + miLibro.getTitulo());
        System.out.println(
                "Autor: " + miLibro.getAutor().getNombre() + " (" + miLibro.getAutor().getNacionalidad() + ")");
        System.out.println("Estado inicial: " + miLibro.getEstado());

        // 4. Prueba de métodos de Libro
        System.out.println("\n=== PROBANDO ESTADOS ===");
        miLibro.prestar();
        System.out.println("Estado tras prestar: " + miLibro.getEstado());

        miLibro.devolver();
        System.out.println("Estado tras devolver: " + miLibro.getEstado());

        teclado.close();
    }
}