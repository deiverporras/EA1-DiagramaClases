package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


//Clase Prestamo
public class Prestamo {

    private static final int DIAS_PRESTAMO = 15;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Libro libro;

    public Prestamo(Libro libro, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = null;
    }

     //Calcula los días restantes antes de que el préstamo venza,
     //tomando como plazo estándar DIAS_PRESTAMO días desde la fecha
     //de préstamo.
    public int calcularDiasRestantes() {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), getFechaLimite());
        return (int) dias;
    }

    public LocalDate getFechaLimite() {
        return fechaPrestamo.plusDays(DIAS_PRESTAMO);
    }

    // Registra la devolución del préstamo: fija la fecha de devolución
     //y delega en el libro asociado su cambio de estado a disponible.
    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
        this.libro.devolver();
        System.out.println("Devolución registrada el " + fechaDevolucion + ".");
    }


     //Indica si el préstamo está vencido respecto a la fecha actual.
    public boolean estaVencido() {
        return calcularDiasRestantes() < 0 && fechaDevolucion == null;
    }

    // Getters
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    @Override // Sobreescritura del método toString para mostrar información del préstamo
    public String toString() {
        return "Préstamo de " + libro.getTitulo() + " (desde " + fechaPrestamo + ")";
    }
    
    // Setters
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
    this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
    this.fechaDevolucion = fechaDevolucion;
    }

    public void setLibro(Libro libro) {
    this.libro = libro;
    }
}
