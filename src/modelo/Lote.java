package modelo;

import java.util.Calendar;


public class Lote 
{
	private int idLote;
	private String nombreProducto;
	private Calendar fechaDeEntrega;
	private Calendar fechaDeVencimiento;
	private double precioPagoProveedor;
	private double precioVentaPublico;
	private long codigoDeBarras;
	private String categoria;
	private boolean empacado;
	private boolean fresco;
	private boolean refrigerado;
	private boolean congelado;
	private String unidadDeMedida;
	private double peso;
	private double cantidad;
	
	public Lote(int idLote,
				String nombreProducto,
				Calendar fechaDeEntrega,
				Calendar fechaDeVencimiento,
				double precioPagoProveedor, 
				double precioVentaPublico,
				long codigoDeBarras,
				String categoria,
				boolean empacado,
				boolean fresco,
				boolean refrigerado,
				boolean congelado,
				String unidadDeMedida,
				double peso,
				double cantidad) {

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

	public int getIdLote() {
		return idLote;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public Calendar getFechaDeEntrega() {
		return fechaDeEntrega;
	}

	public Calendar getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public double getPrecioPagoProveedor() {
		return precioPagoProveedor;
	}

	public double getPrecioVentaPublico() {
		return precioVentaPublico;
	}

	public long getCodigoDeBarras() {
		return codigoDeBarras;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public boolean isEmpacado() {
		return empacado;
	}

	public boolean isFresco() {
		return fresco;
	}

	public boolean isRefrigerado() {
		return refrigerado;
	}

	public boolean isCongelado() {
		return congelado;
	}

	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public double getPeso() {
		return peso;
	}

	public double getCantidad() {
		return cantidad;
	}
	
	
	
}
