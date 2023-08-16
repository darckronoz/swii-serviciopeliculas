package servicio;

import dominio.Pelicula;
import java.util.List;

public interface IServicioPeliculas {

  public List<Pelicula> listarPeliculas();

  public boolean agregarPelicula(Pelicula pelicula);

  public int buscarPelicula(Pelicula pelicula);
}
