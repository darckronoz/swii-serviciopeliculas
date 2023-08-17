package servicio;

import dominio.Movie;
import java.util.List;

public interface IServicioPeliculas {

  public List<Movie> listMovies();

  public boolean addMovie(Movie movie);

  public int searchMovie(Movie movie);
}
