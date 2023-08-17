package presentacion;

import dominio.Pelicula;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasArchivo;
import servicio.ServicioPeliculasLista;
import vista.VistaPeliculas;

import java.util.Scanner;

public class Controller {
  
  private IServicioPeliculas servicioPeliculasLista;
  private IServicioPeliculas servicioPeliculasArchivo;
  private VistaPeliculas vista;
  private boolean salir;

  public Controller() {
    this.servicioPeliculasLista = new ServicioPeliculasLista();
    this.servicioPeliculasArchivo = new ServicioPeliculasArchivo();
    this.vista = new VistaPeliculas();
  }

  public void init() {
    vista.showMenu();
    int selectOption = vista.selectOption();
    while(selectOption != 4){
        try{
          executeOption(selectOption);
        } catch(Exception e){
          vista.showError(e.getMessage());
        }
    }
  }

  private void executeOption(int option) {
    switch(option) {
      case 1:
      addMovie();
      break;
      case 2:
      listMovies();
      break;
      case 3:
      findMovie();
      break;
    }
  }

  private void findMovie() {

  }

  private void listMovies() {
  }

  private void addMovie() {
    Pelicula pelicula = new Pelicula(vista.getMovieName());
  }

  private static boolean ejecutarOpciones(Scanner consola,
                                          IServicioPeliculas servicioPeliculas){
    var opcion = Integer.parseInt(consola.nextLine());
    var salir = false;
    switch (opcion){
      case 1 -> {
        System.out.println("Introduce el nombre de la pelicula: ");
        var nombrePelicula = consola.nextLine();
        String agregar = (servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula)))?"Se agrego correctamene la pelicula":"La pelicula se agrego correctamente";
        System.out.printl(agregar);
      }
      case 2 -> servicioPeliculas.listarPeliculas();
      case 3 -> {
        System.out.println("Introduce la pelicula a buscar: ");
        var buscar = consola.nextLine();
        servicioPeliculas.buscarPelicula(new Pelicula(buscar));
      }
      case 4 -> {
        System.out.println("Hasta pronto!");
        salir = true;
      }
      default -> System.out.println("Opcion no reconocida: " + opcion);
    }
    return salir;
  }
}
