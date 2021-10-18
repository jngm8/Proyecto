package modelo;

import java.util.ArrayList;

public class ProductoPorPeso extends Producto{
	
	public ProductoPorPeso(String nombreProducto,
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
		return precio;
	}

	@Override
	public void mostrarInfo() {
		System.out.println("Precio por "+ unidadDeMedida + ": "+ precio);
		System.out.println("Cantidad Disponible: " + cantidad + " " + unidadDeMedida);
		
	}

}
