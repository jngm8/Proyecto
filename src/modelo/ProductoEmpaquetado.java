package modelo;

import java.util.ArrayList;

public class ProductoEmpaquetado extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;

	public ProductoEmpaquetado(String nombreProducto,
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
							   ArrayList<Lote> lotes) {
			
		super(nombreProducto,
			  precioPagoProveedor,
			  precioAlPublico,
			  codigoDeBarras,
			  categoria,
			  empacado,
			  fresco,
			  refrigerado,
			  congelado,
			  unidadDeMedida,
			  peso,
			  cantidad,
			  lotes);
	}

	@Override
	public double getPrecioPorUnidadDeMedida() {
		return precio/peso;
	}

	@Override
	public void mostrarInfo() {
		System.out.println("Precio por unidad: " + precio);
		System.out.println("Peso por unidad: " + peso);
		System.out.println("Cantidad Disponible: " + cantidad + " unidades");
	}
	
}
