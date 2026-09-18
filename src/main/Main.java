package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;
import modelo.Autor;
import modelo.Libro;
import modelo.LibroDigital;
import modelo.Prestamo;
import servicio.Biblioteca;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Path archivoHistorial = Path.of("historial_biblioteca.txt");
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        boolean repetirFormulario;

        do {
            repetirFormulario = ejecutarFormulario(teclado, archivoHistorial, biblioteca);
        } while (repetirFormulario);

        teclado.close();
        System.out.println("Historial guardado en: " + archivoHistorial.toAbsolutePath());
    }

    private static boolean ejecutarFormulario(Scanner teclado, Path archivoHistorial, Biblioteca biblioteca) {
        guardarEnHistorial(archivoHistorial, "\n=== NUEVO FORMULARIO: " + LocalDateTime.now() + " ===");

        System.out.println("\n=== DATOS DEL AUTOR ===");
        String nombreAutor = preguntar(teclado, archivoHistorial, "Nombre del autor: ");
        String nacionalidadAutor = preguntar(teclado, archivoHistorial, "Nacionalidad del autor: ");
        Autor autor = new Autor(nombreAutor, nacionalidadAutor);

        System.out.println("\n=== DATOS DEL LIBRO ===");
        String persona = preguntar(teclado, archivoHistorial, "Nombre de quien pide el libro: ");
        String titulo = preguntar(teclado, archivoHistorial, "Título del libro: ");
        String tipoLibro = preguntar(teclado, archivoHistorial,
                "¿El libro es físico o digital? (fisico/digital): ");
        boolean esDigital = tipoLibro.equalsIgnoreCase("digital");
        String nombreArchivo = "";
        String formato = "Físico";
        double tamanoMB = 0;

        if (esDigital) {
            nombreArchivo = preguntar(teclado, archivoHistorial,
                    "Nombre del archivo Word en la carpeta Libros (ej. MiLibro.docx): ");
            formato = preguntar(teclado, archivoHistorial, "Formato del archivo (ej. PDF, EPUB): ");
            tamanoMB = Double.parseDouble(
                    preguntar(teclado, archivoHistorial, "Tamaño en MB: "));
        }

        Libro miLibro = biblioteca.buscarLibro(titulo);
        if (miLibro == null) {
            if (esDigital) {
                miLibro = new LibroDigital(titulo, autor, formato, tamanoMB);
            } else {
                miLibro = new Libro(titulo, autor);
            }
            biblioteca.agregarLibro(miLibro);
        } else {
            System.out.println("El libro ya está registrado en el catálogo.");
        }

        System.out.println("\n=== INFORMACIÓN REGISTRADA ===");
        System.out.println("Biblioteca: " + biblioteca.getNombreBiblioteca());
        System.out.println("Título: " + miLibro.getTitulo());
        System.out.println("Tipo de entrega: " + (esDigital ? "Digital" : "Física"));
        System.out.println(
                "Autor: " + miLibro.getAutor().getNombre() + " (" + miLibro.getAutor().getNacionalidad() + ")");
        System.out.println("Estado inicial: " + miLibro.getEstado());

            String respuestaPrestamo = preguntar(teclado, archivoHistorial,
                    "\n" + persona + ", ¿deseas pedir prestado este libro? (si/no): ");

        if (respuestaPrestamo.equalsIgnoreCase("si") || respuestaPrestamo.equalsIgnoreCase("sí")) {
            System.out.println("\n=== REGISTRANDO PRÉSTAMO ===");
            Libro libroEncontrado = biblioteca.buscarLibro(titulo);
            Prestamo prestamo = biblioteca.registrarPrestamo(libroEncontrado);
                if (prestamo == null) {
                    System.out.println("No se puede prestar: el libro ya está prestado a otra persona.");
                    guardarEnHistorial(archivoHistorial,
                            "Resultado: préstamo rechazado porque el libro está prestado.");
                } else {
                    System.out.println(prestamo);
                    System.out.println("Estado del libro tras prestar: " + miLibro.getEstado());
                    System.out.println("Días restantes: " + prestamo.calcularDiasRestantes());
                    System.out.println("Puedes devolverlo hasta el: " + prestamo.getFechaLimite());
                    System.out.println("¿Está vencido?: " + prestamo.estaVencido());

                    if (esDigital) {
                        ofrecerArchivoDigital(teclado, archivoHistorial, miLibro, nombreArchivo);
                    } else {
                        System.out.println("Entrega física disponible en la biblioteca.");
                        guardarEnHistorial(archivoHistorial, "Resultado: entrega física preparada.");
                    }

                    String respuestaDevolucion = preguntar(teclado, archivoHistorial,
                            "¿Quieres devolverlo ahora? (si/no): ");

                    if (respuestaDevolucion.equalsIgnoreCase("si")
                            || respuestaDevolucion.equalsIgnoreCase("sí")) {
                        System.out.println("\n=== REGISTRANDO DEVOLUCIÓN ===");
                        boolean devolucionFueraDePlazo = prestamo.estaVencido();
                        prestamo.registrarDevolucion();
                        System.out.println("Estado del libro tras devolver: " + miLibro.getEstado());
                        if (devolucionFueraDePlazo) {
                            guardarEnHistorial(archivoHistorial, "Resultado: devolución realizada fuera de plazo.");
                        } else {
                            guardarEnHistorial(archivoHistorial, "Resultado: devolución realizada dentro de plazo.");
                        }
                } else {
                        System.out.println("El préstamo sigue activo hasta el " + prestamo.getFechaLimite() + ".");
                }
            }
            System.out.println("Préstamos gestionados: " + biblioteca.getPrestamos().size());
        } else {
            System.out.println("No se ha registrado ningún préstamo.");
        }

        String respuestaNuevoFormulario = preguntar(teclado, archivoHistorial,
                "\n¿Deseas hacer otro formulario? (si/no): ");
        return respuestaNuevoFormulario.equalsIgnoreCase("si")
                || respuestaNuevoFormulario.equalsIgnoreCase("sí");
    }

        private static void ofrecerArchivoDigital(Scanner teclado, Path archivoHistorial, Libro libro,
            String nombreArchivo) {
        if (!(libro instanceof LibroDigital libroDigital)) {
            return;
        }

        Path carpetaLibros = Path.of("Libros");
        try {
            Path archivo = libroDigital.buscarArchivo(carpetaLibros, nombreArchivo);
            if (archivo == null) {
                System.out.println("No se encontró un Word con el nombre del libro en la carpeta Libros.");
                guardarEnHistorial(archivoHistorial, "Resultado: archivo Word no encontrado.");
                return;
            }

            String respuestaArchivo = preguntar(teclado, archivoHistorial,
                    "¿Quieres descargar el Word o recibir un enlace? (descargar/enlace/no): ");
                if (respuestaArchivo.equalsIgnoreCase("descargar")
                    || respuestaArchivo.equalsIgnoreCase("si")
                    || respuestaArchivo.equalsIgnoreCase("sí")) {
                Path destino = libroDigital.descargarArchivo(archivo, Path.of("Descargas"));
                System.out.println("Libro descargado en: " + destino.toAbsolutePath());
                guardarEnHistorial(archivoHistorial, "Resultado: archivo descargado.");
            } else if (respuestaArchivo.equalsIgnoreCase("enlace")) {
                System.out.println("Enlace del libro: " + archivo.toAbsolutePath().toUri());
                guardarEnHistorial(archivoHistorial, "Resultado: enlace generado: " + archivo.toAbsolutePath());
            } else {
                System.out.println("No se ha solicitado el archivo digital.");
            }
        } catch (IOException error) {
            System.out.println("No se pudo acceder al archivo digital: " + error.getMessage());
            guardarEnHistorial(archivoHistorial, "Resultado: error al acceder al archivo Word.");
        }
    }

    private static String preguntar(Scanner teclado, Path archivoHistorial, String pregunta) {
        System.out.print(pregunta);
        String respuesta = teclado.nextLine();
        guardarEnHistorial(archivoHistorial, "Pregunta: " + pregunta.trim());
        guardarEnHistorial(archivoHistorial, "Respuesta: " + respuesta);
        return respuesta;
    }

    private static void guardarEnHistorial(Path archivoHistorial, String texto) {
        try {
            Files.writeString(archivoHistorial, texto + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException error) {
            System.out.println("No se pudo guardar el historial: " + error.getMessage());
        }
    }
}