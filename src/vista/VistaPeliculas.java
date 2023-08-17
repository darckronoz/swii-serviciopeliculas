package vista;

import java.util.List;
import java.util.Scanner;

public class VistaPeliculas {
    private Scanner consola;

    public VistaPeliculas() {
        this.consola = new Scanner(System.in);
    }

    public void showMenu(){
    System.out.print("""
            *** Catalogo de Peliculas ***
            1. Agregar pelicula
            2. Listar peliculas
            3. Buscar pelicula
            4. Salir
            Elige una opcion:
            """);
    }

    public int selectOption() {
        return consola.nextInt();
    }

    public void showError(String error) {
        System.out.println("ha ocurrido un error: " + error);
    }

    public String getMovieName() {
        System.out.println("Ingrese nombre de la pelicula: ");
        return consola.next();
    }

    public void showSuccesfull(String action) {
        System.out.println("La acción se realizó correctamente: " + action);
    }

    public void showMovies(List<Object> lista) {
        for (Object movie : lista) {
            System.out.println(movie.toString());
        }
    }
}


