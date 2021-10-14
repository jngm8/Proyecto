package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Inventario 
{
	private HashMap<Integer, Producto> Productos;
	private HashMap<Integer, Lote> Lotes;	
	private HashMap<String, ArrayList<Integer>> Categorias;
	
	public Inventario()
	{
		Productos = new HashMap<Integer,Producto>();
		Lotes = new HashMap<Integer, Lote>();
		Categorias = new HashMap<String,ArrayList<Integer>>();
	}
	
	public void agregarLote(int idLote, String nombreProducto, Calendar fechaDeEntrega, Calendar fechaDeVencimiento,
			double precioPagoProveedor, double precioVentaPublico, int codigoDeBarras, String categoria, boolean empacado, boolean fresco,
			boolean refrigerado, boolean congelado, String unidadDeMedida, double peso, double cantidad) {
		
		Producto Producto;
		Lote NuevoLote = new Lote(idLote, nombreProducto, fechaDeEntrega, fechaDeVencimiento, precioPagoProveedor, precioVentaPublico, codigoDeBarras, empacado, fresco, refrigerado, congelado, unidadDeMedida, peso, cantidad);
		if (Lotes.containsKey(idLote)) {
			System.err.println("El id del lote ya ha sido ingresado");
		}
		else{
			Lotes.put(idLote, NuevoLote);
		}
			
		if (empacado) {
			if (Productos.containsKey(codigoDeBarras)) {
				Producto = Productos.get(codigoDeBarras);
				Producto.setPrecio(precioVentaPublico);
				Producto.agregarCantidad(cantidad);
				ArrayList<Lote> lotes = Producto.getLotes();
				lotes.add(NuevoLote);
			}
			else {
				ArrayList<Lote> lotes = new ArrayList<Lote>();
				lotes.add(NuevoLote);
				Producto = new ProductoEmpaquetado(peso, precioVentaPublico, empacado, fresco, refrigerado, congelado, codigoDeBarras, cantidad, nombreProducto, unidadDeMedida, categoria, lotes);
				Productos.put(codigoDeBarras, Producto);
			}
		}
		else {
			if (Productos.containsKey(codigoDeBarras)) {
				Producto = Productos.get(codigoDeBarras);
			}
			else {
				ArrayList<Lote> lotes = new ArrayList<Lote>();
				lotes.add(NuevoLote);
				Producto = new ProductoPorPeso(peso, precioVentaPublico, empacado, fresco, refrigerado, congelado, codigoDeBarras, cantidad, nombreProducto, unidadDeMedida, categoria, lotes);
				Productos.put(codigoDeBarras, Producto);
			}
		}
	}
	
	
}
	

