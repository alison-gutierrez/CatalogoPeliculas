package peliculas.datos;

import java.io.*;
import java.util.*;

import peliculas.domain.Pelicula;
import peliculas.excepciones.AccesoDatosEx;
import peliculas.excepciones.EscrituraDatosEx;
import peliculas.excepciones.LecturaDatosEx;

public class AccesoDatosImpl implements IAccesoDatos{

	@Override
	public boolean existe(String nombreRecurso) throws AccesoDatosEx {
		File archivo = new File(nombreRecurso);
		return archivo.exists();
	}

	@Override
	public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
		var archivo = new File (nombreRecurso);
		List <Pelicula> peliculas = new ArrayList<>();
		try {
			var entrada = new BufferedReader (new FileReader (archivo));
			String linea = null;
			linea = entrada.readLine();
			while (linea != null) {
				var pelicula = new Pelicula (linea);
				peliculas.add(pelicula);
				linea = entrada.readLine();
			}
			entrada.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("excepcion al listar peliculas"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("excepcion al listar peliculas"+e.getMessage());
		}
	
		return peliculas;
	}

	@Override
	public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
		var archivo = new File (nombreRecurso);
		try {
			var salida = new PrintWriter (new FileWriter (archivo, anexar));
			salida.println(pelicula.toString());
			salida.close();
			System.out.println("Se ha escrito informacion en el archivo");
		} catch (IOException e) {
			e.printStackTrace();
			throw new EscrituraDatosEx("excepcion al listar peliculas"+e.getMessage());
		}
	}

	@Override
	public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
		var archivo = new File (nombreRecurso);
		String resultado = null;
		try {
			var entrada = new BufferedReader (new FileReader (archivo));
			String linea = null;
			linea = entrada.readLine();
			var indice =1;
			while (linea != null) 
			{				
				if(buscar != null && buscar.equalsIgnoreCase(linea)) 
					{
					resultado = "Pelicula " + linea + "encontrada en el indice " + indice;
					break;
					}
				linea = entrada.readLine();
				indice++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("excepcion al listar peliculas"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new LecturaDatosEx("excepcion al buscar peliculas"+e.getMessage());
		} 
		return resultado;
	
	}

	@Override
	public void crear(String nombreRecurso) throws AccesoDatosEx {
		var archivo = new File (nombreRecurso);
		try {
			var salida = new PrintWriter (new FileWriter (archivo));
			String linea = null;
			salida.close();
			System.out.println("Se ha crado el archivo");
		} catch (IOException e) {
			e.printStackTrace();
			throw new AccesoDatosEx("excepcion al crear peliculas"+e.getMessage());
		} 
	}

	@Override
	public void borrar(String nombreRecurso) throws AccesoDatosEx {
		var archivo = new File(nombreRecurso);
		if (archivo.exists()){
			archivo.delete();
			System.out.println("Se ha borrado el archivo");
		}
		
	}
	

}
