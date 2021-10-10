package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Producto 
{

	private double precio;
	private double precioPorUnidadDeMedida;
	private int codigoDeBarras;
	private int fechaDeVencimiento;
	private float peso;
	
	public Producto(double precio, double precioPorUnidadDeMedida, int codigoDeBarras, int fechaDeVencimiento,
			float peso) {
		super();
		this.precio = precio;
		this.precioPorUnidadDeMedida = precioPorUnidadDeMedida;
		this.codigoDeBarras = codigoDeBarras;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.peso = peso;
		
	
	}


	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecioPorUnidadDeMedida() {
		return precioPorUnidadDeMedida;
	}

	public void setPrecioPorUnidadDeMedida(double precioPorUnidadDeMedida) {
		this.precioPorUnidadDeMedida = precioPorUnidadDeMedida;
	}

	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public int getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(int fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	}
