package control;

import dominio.Movie;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasArchivo;
import vista.VistaPeliculas;
import java.util.List;

public class Controller {
  
  private IServicioPeliculas servicioPeliculasArchivo;
  private VistaPeliculas vista;

  public Controller() {
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
    vista.showBye();
    System.exit(0);
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
      default:
      vista.showError("opcion no encontrada");
      break;
    }
  }

  private void findMovie() {
    try {
      int position = servicioPeliculasArchivo.searchMovie(new Movie(vista.getMovieName()));
      if(position >= 0) {
        vista.showMoviePosition(position);
      }else {
        vista.showError("pelicula no encontrada."); 
      }
    } catch (Exception e) {
      vista.showError(e.getMessage());
    }
    init();
  }

  private void listMovies() {
    try {
      List<Movie> lista = servicioPeliculasArchivo.listMovies();
      if(lista == null) {
        vista.showError("Error al abrir el archivo");
      }
      if(lista.size() > 0){
        vista.showMovies(lista);
      }else {
        vista.showError("Lista vacía");
      }
    } catch (Exception e) {
      vista.showError(e.getMessage());
    }
    init();
  }

  private void addMovie() {
    Movie pelicula = new Movie(vista.getMovieName());
    try {
      if(servicioPeliculasArchivo.addMovie(pelicula)) {
          vista.showSuccesfull("agregar pelicula");
      }else {
        vista.showError("error al agregar prelicula");
      }
    } catch (Exception e) {
      vista.showError(e.getMessage());
    }
    init();
  }
}
