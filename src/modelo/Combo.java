package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Combo
{
	private String nombre;
	private long codigo;
	private int descuento;
	private HashMap<String, Integer> productosCombo;
	Calendar fechaInicio;
	Calendar fechaVencimiento;
	
	public Combo(String nombre, long codigo, int descuento, String productosCombo,
			Calendar fechaInicio, Calendar fechaVencimiento) 
	{
		this.nombre = nombre;
		this.codigo = codigo;
		this.descuento = descuento;
		this.fechaInicio = fechaInicio;
		this.fechaVencimiento = fechaVencimiento;
		HashMap<String, Integer> mapaCombos = new HashMap<String, Integer>();
		String[] partes = productosCombo.split("/");
		for (String parte: partes)
		{
			String[] campos = parte.split(":");
			String producto = campos[0];
			int cantidad = Integer.parseInt(campos[1].strip());
			mapaCombos.put(producto, cantidad);
		}
		this.productosCombo = mapaCombos;
	}

	public HashMap<String, Integer> getProductos() 
	{
		return productosCombo;
	}

	public String getNombre() {
		return nombre;
	}

	public long getCodigo() {
		return codigo;
	}

	public int getDescuento() {
		return descuento;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}
}
