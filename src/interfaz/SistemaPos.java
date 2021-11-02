package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.Inventario;
import controlador.PersistenciaException;
import modelo.Cliente;

public class SistemaPos
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	Inventario Inventario;
	
	public void ejecutarSistemaPos() throws PersistenciaException{
		
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
				if (opcion_seleccionada == 1){
					iniciarVenta();
				}
				else if (opcion_seleccionada == 2){
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
			Inventario.iniciarVenta(Cliente);
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
						Inventario.iniciarVenta();
						venta(Cliente);
						continuar = false;
					}
					else {
						System.out.println("\nEl cliente no pudo ser registrado\n");
					}
				}
				else if (opcion_seleccionada == 2){
					Inventario.iniciarVenta();
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
						Inventario.venderProducto(codigoDeBarras);
					}
					else {
						System.out.println("El producto con codigo de barras " + codigoDeBarras + " no fue encontrado");
					}
				}
				else if (opcion_seleccionada == 2){
					Inventario.terminarVenta(Cliente);
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
	public static void main(String[] args) throws PersistenciaException
	 {
		SistemaPos consola = new SistemaPos();
		consola.ejecutarSistemaPos();
		
	 }
}
