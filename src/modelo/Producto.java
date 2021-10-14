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
	
	private int codigoDeBarras;
	
	
	
	private String nombre;
	protected String unidadDeMedida;
	private String categoria;
	
	private ArrayList<Lote> lotes;
	
	//Constructor
	
	
	public Producto(double peso, double precio, boolean empacado, boolean fresco, boolean refrigerado,
			boolean congelado, int codigoDeBarras, double cantidad, String nombre, String unidadDeMedida, String categoria,
			ArrayList<Lote> lotes) {
		this.peso = peso;
		this.precio = precio;
		this.empacado = empacado;
		this.fresco = fresco;
		this.refrigerado = refrigerado;
		this.congelado = congelado;
		this.codigoDeBarras = codigoDeBarras;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.unidadDeMedida = unidadDeMedida;
		this.categoria = categoria;
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

	public int getCodigoDeBarras() {
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
