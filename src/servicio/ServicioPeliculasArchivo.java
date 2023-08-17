package servicio;

import dominio.Pelicula;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasArchivo implements IServicioPeliculas{

  private final String NOMBRE_ARCHIVO = "peliculas.txt";

  public ServicioPeliculasArchivo(){
    creaciónArchivo();
  }

  
  public String creaciónArchivo(){
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
  public List<Pelicula> listarPeliculas() {
    List<Pelicula> peliculas = new ArrayList<>();
    // volvemos a abrir el archivo
    File archivo = new File(NOMBRE_ARCHIVO);
    try{
      System.out.println("Listado de Peliculas");
      // Abrimos el archivo para lectura
      var entrada = new BufferedReader(new FileReader(archivo));
      // Leemos linea a linea el archivo
      String linea;
      linea = entrada.readLine();
      //Leemos todas las lineas
      while(linea != null){
        var pelicula = new Pelicula(linea);
        peliculas.add(pelicula);
        // Antes de terminar el ciclo volvemos a leer la siguiente linea
        linea = entrada.readLine();
      }
      // Cerrar el archivo
      entrada.close();
    } catch (Exception e){
      System.out.println("Ocurrio un error a leer el archivo: " + e.getMessage());
    }
    return peliculas;
  }

  @Override
  public boolean agregarPelicula(Pelicula pelicula) {
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
  public int buscarPelicula(Pelicula pelicula) {
    var archivo = new File(NOMBRE_ARCHIVO);
    try{
      // Abrimos el archivo para lectura linea a linea
      BufferedReader entrada = new BufferedReader(new FileReader(archivo));
      String lineaTexto;
      lineaTexto = entrada.readLine();
      int indice = 1;
      var peliculaBuscar = pelicula.getNombre();
      while(lineaTexto != null){
        // Buscamos sin importar mayusculas/minusculas
        if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
          entrada.close();
          break;
        }
        // Leemos la siguiente linea antes de la siguiente iteracion
        lineaTexto = entrada.readLine();
        indice++;
      }
      return indice;
    } catch(Exception e){
      System.out.println("Ocurrio un error al buscar en el archivo: "
              + e.getMessage());
              return -1;
    }
  }
}
