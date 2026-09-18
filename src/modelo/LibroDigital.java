package modelo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;

public class LibroDigital extends Libro {

    // Atributos propios
    private String formatoArchivo;
    private double tamanoMB;

    // Constructor
    public LibroDigital(String titulo, Autor autor, String formatoArchivo, double tamanoMB) {
        super(titulo, autor);
        this.formatoArchivo = formatoArchivo;
        this.tamanoMB = tamanoMB;
    }

    // Metodo propio
    public void descargar() {
        System.out.println("Descargando el libro " + titulo + " (" + tamanoMB + "MB...");
    }

    public Path buscarArchivo(Path carpetaLibros) throws IOException {
        return buscarArchivo(carpetaLibros, titulo);
    }

    public Path buscarArchivo(Path carpetaLibros, String nombreArchivo) throws IOException {
        if (!Files.isDirectory(carpetaLibros)) {
            return null;
        }

        String nombreBuscado = normalizar(nombreSinExtension(Path.of(nombreArchivo)));
        try (DirectoryStream<Path> archivos = Files.newDirectoryStream(carpetaLibros)) {
            for (Path archivo : archivos) {
                if (Files.isRegularFile(archivo)
                        && esDocumentoWord(archivo)
                        && normalizar(nombreSinExtension(archivo)).equals(nombreBuscado)) {
                    return archivo;
                }
            }
        }
        return null;
    }

    public Path descargarArchivo(Path archivo, Path carpetaDescargas) throws IOException {
        Files.createDirectories(carpetaDescargas);
        Path destino = carpetaDescargas.resolve(archivo.getFileName());
        return Files.copy(archivo, destino, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    private boolean esDocumentoWord(Path archivo) {
        String nombre = archivo.getFileName().toString().toLowerCase();
        return nombre.endsWith(".doc") || nombre.endsWith(".docx");
    }

    private String nombreSinExtension(Path archivo) {
        String nombre = archivo.getFileName().toString();
        int punto = nombre.lastIndexOf('.');
        return punto > 0 ? nombre.substring(0, punto) : nombre;
    }

    private String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .trim()
                .toLowerCase();
    }

    // Sobreescritura del metodo prestar
    @Override
    public void prestar() {
        super.prestar();
        System.out.println("El libro digital " + titulo + " ha sido prestado exitosamente");
    }

    // Getter
    public String getFormatoArchivo() {
        return formatoArchivo;
    }

    public double getTamanoMB() {
        return tamanoMB;
    }
}