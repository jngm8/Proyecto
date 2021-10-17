package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class Producto 
{	
	//ATRIBUTOS
	protected double peso;
	protected double precio;
	protected double cantidad;
	
	private boolean empacado;
	private boolean fresco;
	private boolean refrigerado;
	private boolean congelado;
	
	private long codigoDeBarras;
	
	
	
	private String nombre;
	protected String unidadDeMedida;
	private String categoria;
	
	private ArrayList<Lote> lotes;
	
	//Constructor
	
	
	public Producto(String nombreProducto,
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
					ArrayList<Lote> lotes) {
		this.nombre = nombreProducto;
		this.precio = precioAlPublico;
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
	}

	public String getNombre() {
		return nombre;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public String getCategoria() {
		return categoria;
	}

	public ArrayList<Lote> getLotes() {
		return lotes;
	}
	
	public void printInfo() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Codigo de barras: " + codigoDeBarras);
		System.out.println("Categoria: " + categoria);
		mostrarInfo();
		System.out.println("Empacado: " + empacado);
		System.out.println("Fresco: " + fresco);
		System.out.println("Refrigerado: " + refrigerado);
		System.out.println("Congelado: " + congelado);
	}
	
	
	//METODOS ABSTRACTOS
	abstract public double getPrecioPorUnidadDeMedida();
	abstract public void mostrarInfo();
	
	
	
}
