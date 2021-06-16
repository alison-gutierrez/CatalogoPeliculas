package peliculas.vista;

import java.util.Scanner;
import peliculas.servicio.*;

public class CatalogoPeliculasVista {

	public static void main (String[] args) {
		var opcion = -1;
		var scanner = new Scanner (System.in);
		ICatalogoPeliculas catalogo = new CatalagoPeliculasImpl();
	
		while (opcion != 0) {
			System.out.println("Elige una opcion: \n"
					+ "1. Iniciar catalog peliculas \n"
					+ "2. Agregar catalog peliculas \n"
					+ "3. Listar catalog peliculas \n"
					+ "4. Buscar catalog peliculas \n"
					+ "0. Salir catalog peliculas \n");
			opcion = Integer.parseInt(scanner.nextLine());
		
		switch(opcion) {
		case 1:
			catalogo.iniciarCatalogoPeliculas();
			break;
		case 2:
			System.out.println("Introduce el nombre de la pelicula");
			var nombrePelicula = scanner.nextLine();
			catalogo.agregarPelicula(nombrePelicula);
			break;
			
		case 3:
			catalogo.listarPeliculas();
			break;
		case 4:
			System.out.println("Introduce el nombre de la pelicula a buscar");
			var buscar = scanner.nextLine();
			catalogo.buscarPeliculas(buscar);
			break;
		case 0:
			System.out.println("Hasta pronto");
			break;
		}
		}
	
	}

}
