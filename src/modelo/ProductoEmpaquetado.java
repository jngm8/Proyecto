package modelo;

import java.util.ArrayList;

public class ProductoEmpaquetado extends Producto {

	

	public ProductoEmpaquetado(double peso, double precio, boolean empacado, boolean fresco, boolean refrigerado,
			boolean congelado, int codigoDeBarras, double cantidad, String nombre, String unidadDeMedida, String categoria,
			ArrayList<Lote> lotes) {
		super(peso, precio, empacado, fresco, refrigerado, congelado, codigoDeBarras, cantidad, nombre, unidadDeMedida,
				categoria, lotes);
	}

	@Override
	public double getPrecioPorUnidadDeMedida() {
		return precio/peso;
	}

	@Override
	public void mostrarInfo() {
		System.out.println("Precio por unidad: "+ precio);
		System.out.println("Peso por unidad: "+ precio);
		System.out.println("Cantidad Disponible: " + cantidad + " unidades");
	}
	
}
