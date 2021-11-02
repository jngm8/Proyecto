package interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import controlador.Inventario;
import controlador.PersistenciaException;
import modelo.Producto;

public class SistemaEncargado implements Serializable

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	Inventario Inventario;
	
	public void ejecutarSistemaEncargado() throws PersistenciaException{
		
		Inventario = new Inventario();
		System.out.println("======== BIENVENIDO AL SISTEMA ENCARGADO DEL INVENTARIO ========");
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nOPCIONES\n");
				System.out.println("\t1. Cargar un lote");
				System.out.println("\t2. Consultar información general");
				System.out.println("\t3. Eliminar un lote");
				System.out.println("\t4. Consultar desempeño financiero de un producto");
				System.out.println("\t5. Mostrar informacion de un producto");
				System.out.println("\t6. Salir de la aplicación");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada < 6 && opcion_seleccionada > 0){
					ejecutarOpcion(opcion_seleccionada);
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
	
	public void ejecutarOpcion(int opcionSeleccionada)
	{
		if (opcionSeleccionada == 1)
			ejecutarCargarLote();
		else if (opcionSeleccionada == 2)
			ejecutarInformacionGeneral();
		else if (opcionSeleccionada == 3)
			ejecutarEliminarUnLote();
		else if (opcionSeleccionada == 4)
			ejecutarDesempenoProducto();
		else if (opcionSeleccionada == 5)
			ejecutarInformacionProducto();
	}
	
	private void ejecutarCargarLote() {
		String nombreArchivo = input("Ingrese el nombre del archivo .csv de lotes ubicado en la carpeta data");
		File archivo = new File ("./data/" + nombreArchivo + ".csv");
		System.out.println("Cargando la informacion del lote en el inventario...");
		Inventario.cargarLote(archivo);
	}
	
	private void ejecutarInformacionGeneral() {
		System.out.println("INFORMACION DEL INVENTARIO\n");
		System.out.println("Numero de productos disponibles: " + Inventario.sizeProductos());
		System.out.println("Total de lotes disponibles: " + Inventario.sizeLotes());
		System.out.println("Numero de góndolas creadas: " + Inventario.sizeCategorias());
		System.out.println("Numero de clientes registrados: " + Inventario.sizeClientes());
	}
	
	private void ejecutarEliminarUnLote(){
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nOPCIONES\n");
				System.out.println("\t1. Eliminar un lote por IdLote");
				System.out.println("\t2. Eliminar lotes vencidos");
				System.out.println("\t3. Regresar");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1){
					int idLote = Integer.parseInt(input("Ingrese el id del lote que desea eliminar"));
					System.out.println(Inventario.eliminarLote(idLote));
					continuar = false;
				}
				else if (opcion_seleccionada == 2){
					String formatoFecha = input("Ingrese la fecha en formato dd-MM-yyyy");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Date date;
					try {
						date = sdf.parse(formatoFecha);
					} catch (ParseException e) {
						date = new Date();
					}
					Calendar fechaVencimiento = Calendar.getInstance();
					fechaVencimiento.setTime(date);
					System.out.println(Inventario.eliminarLotesVencidos(fechaVencimiento));
					continuar = false;
				}
				else if (opcion_seleccionada == 3){
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
	
	private void ejecutarDesempenoProducto() {
		String nombreProducto = input("Ingrese el nombre del producto");
		Producto Producto = Inventario.getProducto(nombreProducto);
		if (Producto != null) {
			Producto.desempenoFinanciero();
		}
		else {
			System.out.println("El producto " + nombreProducto + " no fue encontrado");
		}
	}
	
	private void ejecutarInformacionProducto() {
		String nombreProducto = input("Ingrese el nombre del producto");
		Producto Producto = Inventario.getProducto(nombreProducto);
		if (Producto != null) {
			Producto.printInfo();
		}
		else {
			System.out.println("El producto " + nombreProducto + " no fue encontrado");
		}
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

	public static void main(String[] args) throws PersistenciaException
	 {
		SistemaEncargado consola = new SistemaEncargado();
		consola.ejecutarSistemaEncargado();
		
	 }
}
