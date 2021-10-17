package interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.Inventario;

public class SistemaEncargado 

{
	Inventario Inventario;
	
	public void ejecutarSistemaEncargado() {
		
		Inventario = new Inventario();
		System.out.println("======== BIENVENIDO AL SISTEMA ENCARGADO DEL INVENTARIO ========");
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nOPCIONES\n");
				System.out.println("\t1. Cargar un lote");
				System.out.println("\t2. Consultar información general");
				System.out.println("\t3. Eliminar un lote");
				System.out.println("\t4. Salir de la aplicación");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada < 2 && opcion_seleccionada > 0){
					ejecutarOpcion(opcion_seleccionada);
				}
				else if (opcion_seleccionada == 6){
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else{
					System.out.println("\nPor favor seleccione una opción válida.\n");
				}
			}
			catch (NumberFormatException e){
				System.out.println("\nDebe seleccionar uno de los números de las opciones.\\n");
			}
		}
		
	}
	
	public void ejecutarOpcion(int opcionSeleccionada)
	{
		if (opcionSeleccionada == 1)
			ejecutarCargarLote();
		else if (opcionSeleccionada == 2)
			;
	}
	
	private void ejecutarCargarLote() {
		String nombreArchivo = input("Ingrese el nombre del archivo .csv de lotes ubicado en la carpeta data");
		File archivo = new File ("./data/" + nombreArchivo);
		System.out.println("Cargando la informacion del lote en el inventario...");
		Inventario.cargarLote(archivo);
	}
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) 
	 {
		SistemaEncargado consola = new SistemaEncargado();
		consola.ejecutarSistemaEncargado();
	 }
}
