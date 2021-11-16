package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.Inventario;
import controlador.PersistenciaException;
import modelo.Cliente;
import modelo.Producto;

public class SistemaPOS
{
	Inventario Inventario;
	
	public void ejecutarSistemaPos() throws PersistenciaException{
		
		Inventario = new Inventario();
		System.out.println("======== BIENVENIDO AL SISTEMA POS ========");
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\nOPCIONES\n");
				System.out.println("\t1. Iniciar Venta");
				System.out.println("\t2. Consultar informacion de un cliente");
				System.out.println("\t3. Salir de la aplicación");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1){
					iniciarVenta();
				}
				else if (opcion_seleccionada == 2){
					consultarPuntos();
				}
				else if (opcion_seleccionada == 3){
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
	private void consultarPuntos() {
		Cliente Cliente;
		try {
			long cedula = Long.parseLong(input("Por favor ingrese el numero de cedula del cliente"));
			Cliente = Inventario.getCliente(cedula);
			if (Cliente != null) {
				Cliente.PrintInfo();
			}
			else {
				System.out.println("\nEl cliente con numero de cedula " + cedula + " no fue encontrado\n");
			}
			
		}
		catch (NumberFormatException e){
			System.out.println("\nDebe seleccionar uno de los números de las opciones.\n");
		}
		
	}
	public void iniciarVenta() {
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\n¿El cliente se encuentra registrado en el sistema de puntos?\n");
				System.out.println("\t1. Si");
				System.out.println("\t2. No");
				System.out.println("\t3. Volver al menú principal");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor ingrese el numero de una opción"));
				if (opcion_seleccionada == 1){
					ventaClienteRegistrado();
					continuar = false;
				}
				else if (opcion_seleccionada == 2)
				{
					ventaClienteNoRegistrado();
					continuar = false;
				}
				else if (opcion_seleccionada == 3)
				{
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
	
	private void ventaClienteRegistrado() 
	{
		long cedula = Long.parseLong(input("Por favor ingrese el número de cédula"));
		Cliente Cliente = Inventario.getCliente(cedula);
		if (Cliente != null) 
		{
			System.out.println("Esta es la información del cliente con el número de cedula ingresado: \n");
			Cliente.PrintInfo();
			venta(Cliente);
		}
		else {
			System.out.println("\nEl cliente no fue encontrado \n");
		}
	}
	
	private void ventaClienteNoRegistrado() {
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("\n¿El cliente desea registrarse en el sistema de puntos?\n");
				System.out.println("\t1. Si");
				System.out.println("\t2. No");
				System.out.println("\t3. Volver al menú principal");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor ingrese el numero de una opción"));
				if (opcion_seleccionada == 1){
					Cliente Cliente = registrarCliente();
					if (Cliente != null) {
						
						venta(Cliente);
						continuar = false;
					}
					else {
						System.out.println("\nEl cliente no pudo ser registrado\n");
					}
				}
				else if (opcion_seleccionada == 2){
					
					venta(null);
				}
				else if (opcion_seleccionada == 3){
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
	
	private void venta(Cliente Cliente) {
		Inventario.iniciarVenta();
		boolean continuar = true;
		while (continuar) {
			try {
				System.out.println("VENTA");
				System.out.println("\t1. Agregar un producto");
				System.out.println("\t2. Terminar venta");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Por favor ingrese el numero de una opción"));
				if (opcion_seleccionada == 1){
					long codigoDeBarras = Long.parseLong(input("Ingrese el codigo de barras del producto"));
					boolean existeProducto = Inventario.verificarProducto(codigoDeBarras);
					if (existeProducto) {
						Producto Producto = Inventario.getProductoByCodigoDeBarras(codigoDeBarras);
						System.out.println("Producto: " + Producto.getNombre());
						if (Producto.getPeso() == 1.0) {
							System.out.println("Precio: " + Producto.getPrecio() + "/" + Producto.getUnidadDeMedida());
							System.out.print("Ingrese cuantos " + Producto.getUnidadDeMedida() + " desea del producto (disponibles: " + Producto.getCantidad() + Producto.getUnidadDeMedida() + ")");
						}
						else {
							System.out.println("Precio: " + Producto.getPrecio());
							System.out.print("Ingrese cuantas unidades desea del producto (disponibles: " + (int)Producto.getCantidad() + ")");
						}
						int cantidad = Integer.parseInt(input(""));
						if (cantidad > 0 && cantidad <= Producto.getCantidad()) {
							Inventario.venderProducto(codigoDeBarras, cantidad);
						}
						else {
							System.out.println("La cantidad ingresada no es válida");
						}
					}
					else {
						System.out.println("El producto con codigo de barras " + codigoDeBarras + " no fue encontrado");
					}
				}
				else if (opcion_seleccionada == 2){
					Inventario.terminarVenta(Cliente);
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
	private Cliente registrarCliente() {
		Cliente Cliente;
		long cedula = Long.parseLong(input("Ingrese el numero de cedula del cliente"));
		if (!Inventario.containsCliente(cedula)) 
		{
			String nombre = input("Ingrese su nombre");
			int edad = Integer.parseInt(input("Ingrese la edad del cliente"));
			String sexo = input("Ingrese el sexo del cliente al nacer");
			String estadoCivil = input("Ingrese el estado civil del cliente");
			String situacionLaboral = input("Ingrese la situacion laboral del cliente");
			Cliente = new Cliente(nombre,cedula, edad, sexo, estadoCivil, situacionLaboral);
			Inventario.agregarCliente(Cliente);
			return Cliente;
		}
		else {
			System.out.println("\nEl numero de cedula ingresado ya está agregado\n");
			return null;
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
	
	/**
     * Ejecuta la aplicación
     * @param args son parámetros de ejecución de la aplicación. No se usan en este programa
     */
	public static void main(String[] args)
	 {
		SistemaPOS consola = new SistemaPOS();
		try {
			consola.ejecutarSistemaPos();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		
	 }
}
