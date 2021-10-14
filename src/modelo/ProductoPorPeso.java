package modelo;

import java.util.ArrayList;

public class ProductoPorPeso extends Producto{
	
	public ProductoPorPeso(double peso, double precio, boolean empacado, boolean fresco, boolean refrigerado,
			boolean congelado, int codigoDeBarras, double cantidad, String nombre, String unidadDeMedida, String categoria,
			ArrayList<Lote> lotes) {
		super(peso, precio, empacado, fresco, refrigerado, congelado, codigoDeBarras, cantidad, nombre, unidadDeMedida,
				categoria, lotes);
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
