package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public abstract class Producto implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	//ATRIBUTOS
	protected double peso;
	protected double precio;
	private double gananciaNeta = 0;
	private double historicoVendidos = 0;
	private double historicoCantidad;	// Total de productos recibidos en toda la historia
	private double totalPagadoAlProveedor;	// Suma total del precio pagado al proveedor por el producto
	protected double cantidad;
	
	private boolean empacado;
	private boolean fresco;
	private boolean refrigerado;
	private boolean congelado;
	private String imagen = "";
	
	private long codigoDeBarras;
		
	
	
	private String nombre;
	protected String unidadDeMedida;
	private String categoria;
	
	private ArrayList<Lote> lotes;
	
	//Constructor
	
	
	public Producto(String nombreProducto,
					double precioPagoProveedor,
					double precioAlPublico,
					long codigoDeBarras,
					String categoria,
					boolean empacado, 
					boolean fresco,
					boolean refrigerado,
					boolean congelado,
					String unidadDeMedida,
					double peso,
					double cantidad,
					ArrayList<Lote> lotes) 
	{
		nombre = nombreProducto;
		precio = precioAlPublico;
		historicoCantidad = cantidad;
		this.codigoDeBarras = codigoDeBarras;
		this.categoria = categoria;
		this.empacado = empacado;
		this.fresco = fresco;
		this.refrigerado = refrigerado;
		this.congelado = congelado;
		this.unidadDeMedida = unidadDeMedida;
		this.peso = peso;
		this.cantidad = cantidad;
		this.lotes = lotes;
	}

	//METODOS NO ABSTRACTOS - IMPLEMENTADOS
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public void vender(double cantidad) 
	{
		if (cantidad <= this.cantidad) 
		{
			gananciaNeta += cantidad*precio;
			historicoVendidos += cantidad;
			this.cantidad -= cantidad;
		}
	}
	public double gananciaNeta(double cantidad)
	{
		double ganancia = 0;
		
		ganancia += cantidad*precio;
				
		this.cantidad -= cantidad;
				
		return ganancia;
	}
	
	public int historico(double cantidad)
	{
		
		int historico = 0;
		historico += cantidad;
		return historico;
	}
	
	
	public void sumarTotalProveedor(double precioPagoProveedor, double cantidad) 
	{
		totalPagadoAlProveedor += precioPagoProveedor*cantidad;
	}
	
	public double utilidades(double precioPagoProveedor, double cantidad)
	{
		totalPagadoAlProveedor += precioPagoProveedor*cantidad;
		
		double resultado = gananciaNeta - totalPagadoAlProveedor;
		
		return resultado;
	}
	
	public double gananciaPromedio(double cantidad,double precioPagoProveedor, double cantidades)
	{
		double resultado = 0;
		
		resultado = utilidades(precioPagoProveedor, cantidades)/cantidad;
		
		return resultado;
	}
	
	public boolean isFresco() {
		return fresco;
	}
	
	public boolean isEmpacado() {
		return empacado;
	}
	

	public boolean isRefrigerado() {
		return refrigerado;
	}

	public boolean isCongelado() {
		return congelado;
	}

	public long getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void agregarCantidad(double cantidad) {
		this.cantidad += cantidad;
		historicoCantidad += cantidad;
	}
	
	public void quitarCantidad(double cantidad) {
		this.cantidad -= cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	
	public double getPeso() {
		return peso;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public ArrayList<Lote> getLotes() {
		return lotes;
	}
	
	public void borrarLote(int idLote) {
		boolean encontrado = false;
		int index = 0;
		while (!encontrado) {
			Lote Lote = lotes.get(index);
			if (Lote.getIdLote() == idLote) {
				lotes.remove(index);
				encontrado = true;
			}
			index += 1;
		}
	}
	
	public double descuentos()
	{
		
		double descuento = 0;
		
		{
			descuento += (getPrecio()*40)/100;
		}
		
		return descuento;
	}
	
	public void desempenoFinanciero() 
	{
		System.out.println("Ganancia Neta: " + gananciaNeta);
		double utilidades = gananciaNeta - totalPagadoAlProveedor;
		System.out.println("Utilidades: " + utilidades);
		System.out.println("Ganancia promedio por producto vendido: " + (utilidades/cantidad));
	}
	
	public void printInfo() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Codigo de barras: " + codigoDeBarras);
		System.out.println("Categoria: " + categoria);
		System.out.println("Ganancia neta: " + gananciaNeta);
		System.out.println("Numero de ventas: " + historicoVendidos);
		System.out.println("Total de productos recibidos: " + historicoCantidad);
		System.out.println("Total pagado al proveedor: " + totalPagadoAlProveedor);
		mostrarInfo();
		System.out.println("Numero de lotes: " + lotes.size());
		if (lotes.size() > 0) {
			Lote Lote = lotes.get(0);
			System.out.println("-Informacion del primer lote-");
			System.out.println("Id Lote: " + Lote.getIdLote());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("Fecha de vencimiento: " + sdf.format(Lote.getFechaDeVencimiento().getTime()));
		}
		System.out.println("Empacado: " + empacado);
		System.out.println("Fresco: " + fresco);
		System.out.println("Refrigerado: " + refrigerado);
		System.out.println("Congelado: " + congelado);
	}
	
	//METODOS ABSTRACTOS
	abstract public double getPrecioPorUnidadDeMedida();
	abstract public void mostrarInfo();
	
	
	
}
