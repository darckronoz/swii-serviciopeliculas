package dominio;

import java.util.Objects;

public class Movie {
  private String nombre;

  public Movie(String nombre){
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Movie pelicula = (Movie) o;

    return Objects.equals(nombre, pelicula.nombre);
  }

  @Override
  public int hashCode() {
    return nombre != null ? nombre.hashCode() : 0;
  }

  @Override
  public String toString() {
    return this.nombre;
  }

}
