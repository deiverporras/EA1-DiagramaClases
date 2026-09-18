package main;

import java.util.Scanner;
import modelo.Autor;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== REGISTRO DEL AUTOR ===");

        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        // Crear el autor con los datos ingresados
        Autor autor1 = new Autor(nombre, nacionalidad);

        // Mostrar los datos
        System.out.println("\n=== DATOS DEL AUTOR ===");
        System.out.println("Nombre: " + autor1.getNombre());
        System.out.println("Nacionalidad: " + autor1.getNacionalidad());

        scanner.close();
    }
}