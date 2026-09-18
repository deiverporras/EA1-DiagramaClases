package modelo;

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