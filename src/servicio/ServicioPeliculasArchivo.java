package servicio;

import dominio.Movie;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasArchivo implements IServicioPeliculas{

  private final String NOMBRE_ARCHIVO = "peliculas.txt";

  public ServicioPeliculasArchivo(){
    creationFile();
  }

  
  public String creationFile(){
    File archivo = new File(NOMBRE_ARCHIVO);
    try{
      // Si ya existe el archivo, NO se vuelve a crear
      if(archivo.exists()){
        return "El archivo ya existe!";
      }
      else{
        // Si no existe, se crea vacio
        PrintWriter salida = new PrintWriter(new FileWriter(archivo));
        salida.close();
        return "Se ha creado el archivo";
      }
    } catch(Exception e){
      return ("Ocurrio un error al abrir archivo: " + e.getMessage());
    }
  }

  @Override
  public List<Movie> listMovies() {
    List<Movie> peliculas = new ArrayList<>();
    // volvemos a abrir el archivo
    File archivo = new File(NOMBRE_ARCHIVO);
    try{
      // Abrimos el archivo para lectura
      var entrada = new BufferedReader(new FileReader(archivo));
      // Leemos linea a linea el archivo
      String linea;
      linea = entrada.readLine();
      //Leemos todas las lineas
      while(linea != null){
        var pelicula = new Movie(linea);
        peliculas.add(pelicula);
        // Antes de terminar el ciclo volvemos a leer la siguiente linea
        linea = entrada.readLine();
      }
      // Cerrar el archivo
      entrada.close();
    } catch (Exception e){
      peliculas = null;
    }
    return peliculas;
  }

  @Override
  public boolean addMovie(Movie pelicula) {
    boolean anexar = false;
    var archivo = new File(NOMBRE_ARCHIVO);
    try{
      // Revisamos si existe el archivo
      anexar = archivo.exists();
      var salida = new PrintWriter(new FileWriter(archivo, anexar));
      // Agregamos la pelicula (toString)
      salida.println(pelicula);
      salida.close();
      return true;
    } catch(Exception e){
      return false;
    }
  }

  @Override
  public int searchMovie(Movie pelicula) {
    File archivo = new File(NOMBRE_ARCHIVO);
    try{
      // Abrimos el archivo para lectura linea a linea
      BufferedReader entrada = new BufferedReader(new FileReader(archivo));
      String lineaTexto;
      lineaTexto = entrada.readLine();
      int index = 1;
      String peliculaBuscar = pelicula.getNombre();
      while(lineaTexto != null){
        // Buscamos sin importar mayusculas/minusculas
        if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
          entrada.close();
          return index;
        }
        // Leemos la siguiente linea antes de la siguiente iteracion
        lineaTexto = entrada.readLine();
        index++;
      }
      entrada.close();
      return -1;
    } catch(Exception e){
      return -1;
    }
  }
}
