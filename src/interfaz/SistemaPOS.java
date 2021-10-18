package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.Inventario;
import controlador.PersistenciaException;

public class SistemaPos
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	Inventario Inventario;
	
	public void ejecutarSistemaEncargado() throws PersistenciaException{
		
		Inventario = new Inventario();
		System.out.println("======== BIENVENIDO AL SISTEMA POS ========");
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nOPCIONES\n");
				System.out.println("\t1. Iniciar Venta");
				System.out.println("\t2. Salir de la aplicación");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 2){
					iniciarVenta();
				}
				else if (opcion_seleccionada == 6){
					Inventario.salvarInventario();
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else{
					System.out.println("\nPor favor seleccione una opción válida.\n");
				}
			}
			catch (NumberFormatException e){
				System.out.println("\nDebe seleccionar uno de los números de las opciones.\n");
			}
		}
	}
	public void iniciarVenta() {
		
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
}
