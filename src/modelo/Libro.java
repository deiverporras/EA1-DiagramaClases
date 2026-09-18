package modelo;

// Atributos
public abstract class Libro {
    protected String titulo;
    protected boolean disponible;
    protected Autor autor;

    // Constructor
    public Libro(String titulo, Autor autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    // Metodos para cambiar el estado Disponible
    public void prestar() {
        this.disponible = false;
    }

    public void devolver() {
        this.disponible = true;
    }

    // Getter
    public boolean getEstado() {
        return disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }
}
