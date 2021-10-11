package modelo;

public class ProductoPorPeso extends Producto{

	public ProductoPorPeso() {
		
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
