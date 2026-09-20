package modelo;

public class Libro {

    // Atributos
    private String titulo;
    private Autor autor;
    private boolean disponible;

    // Constructor
    public Libro(String titulo, Autor autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    // Métodos
    public void prestar() {
        this.disponible = false;
        System.out.println("El libro físico " + titulo + " ha sido prestado exitosamente");
    }

    public void devolver() {
        this.disponible = true;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public boolean getEstado() {
        return disponible;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}