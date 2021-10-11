package modelo;

public class ProductoEmpaquetado extends Producto {

	public ProductoEmpaquetado() {
		
	}

	@Override
	public double getPrecioPorUnidadDeMedida() {
		return precio/peso;
	}

	@Override
	public void mostrarInfo() {
		System.out.println("Precio por unidad: "+ precio);
		System.out.println("Cantidad Disponible: " + cantidad + " unidades");
	}
	
}
