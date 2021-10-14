package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Lote 
{
	private int idLote;
	private String nombreProducto;
	private Calendar fechaDeEntrega;
	private Calendar fechaDeVencimiento;
	private double precioPagoProveedor;
	private double precioVentaPublico;
	private int codigoDeBarras;
	private boolean empacado;
	private boolean fresco;
	private boolean refrigerado;
	private boolean congelado;
	private String unidadDeMedida;
	private double peso;
	private double cantidad;
	
	public Lote(int idLote, String nombreProducto, Calendar fechaDeEntrega, Calendar fechaDeVencimiento,
			double precioPagoProveedor, double precioVentaPublico, int codigoDeBarras, boolean empacado, boolean fresco,
			boolean refrigerado, boolean congelado, String unidadDeMedida, double peso, double cantidad) {

		this.idLote = idLote;
		this.nombreProducto = nombreProducto;
		this.fechaDeEntrega = fechaDeEntrega;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.precioPagoProveedor = precioPagoProveedor;
		this.precioVentaPublico = precioVentaPublico;
		this.codigoDeBarras = codigoDeBarras;
		this.empacado = empacado;
		this.fresco = fresco;
		this.refrigerado = refrigerado;
		this.congelado = congelado;
		this.unidadDeMedida = unidadDeMedida;
		this.peso = peso;
		this.cantidad = cantidad;
		
	
		
	}
	
	
	
}
