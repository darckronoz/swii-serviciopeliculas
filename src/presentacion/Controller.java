package presentacion;

import dominio.Pelicula;
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
      int position = servicioPeliculasArchivo.buscarPelicula(new Pelicula(vista.getMovieName()));
      if(position >= 0) {
        vista.showMoviePosition(position);
      }else {
        vista.showError("pelicula no encontrada."); 
      }
    } catch (Exception e) {
      vista.showError(e.getMessage());
    }
  }

  private void listMovies() {
    try {
      List<Pelicula> lista = servicioPeliculasArchivo.listarPeliculas();
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
  }

  private void addMovie() {
    Pelicula pelicula = new Pelicula(vista.getMovieName());
    try {
      if(servicioPeliculasArchivo.agregarPelicula(pelicula)) {
          vista.showSuccesfull("agregar pelicula");
      }else {
        vista.showError("error al agregar prelicula");
      }
    } catch (Exception e) {
      vista.showError(e.getMessage());
    }
  }
}
