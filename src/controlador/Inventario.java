package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Lote;
import modelo.Producto;
import modelo.ProductoEmpaquetado;
import modelo.ProductoPorPeso;


public class Inventario 
{
	private HashMap<Long, Producto> Productos;
	private HashMap<String, Long> codigosProductos;
	private HashMap<Integer, Lote> Lotes;	
	private HashMap<String, ArrayList<Long>> Categorias;
	
	public Inventario()
	{
		Productos = new HashMap<Long, Producto>();
		codigosProductos = new HashMap<String, Long>();
		Lotes = new HashMap<Integer, Lote>();
		Categorias = new HashMap<String,ArrayList<Long>>();
	}
	
	public void cargarLote(File archivoMenu)
	{
	    
		FileReader fr = null;
	    BufferedReader br = null;
	    try 
	    {
        // Apertura del fichero y creacion de BufferedReader para poder
        // hacer una lectura comoda (disponer del metodo readLine()).
        fr = new FileReader (archivoMenu);
        br = new BufferedReader(fr);

        // Lectura del fichero
        String linea;
        linea = br.readLine();
        String[] encabezados = linea.split(",");
        System.out.println(encabezados);
        linea = br.readLine();
        while(linea!=null) 
        {
            String[] partes = linea.split(",");
            int idLote = Integer.parseInt(partes[0]);
            String nombreProducto = partes[1];
            
            String[] formatoFecha = partes[2].split("-");
            int anio = Integer.parseInt(formatoFecha[2]);
            int mes = Integer.parseInt(formatoFecha[1]);
            int dia = Integer.parseInt(formatoFecha[0]);
            Calendar fechaEntrega = new GregorianCalendar(anio, mes, dia);
            
            String[] formatoFecha2 = partes[3].split("-");
            int anio2 = Integer.parseInt(formatoFecha2[2]);
            int mes2 = Integer.parseInt(formatoFecha2[1]);
            int dia2 = Integer.parseInt(formatoFecha2[0]);
            Calendar fechaVencimiento = new GregorianCalendar(anio2, mes2, dia2);
            
            double precioPagoProveedor = Double.parseDouble(partes[4]);
            double precioAlPublico = Double.parseDouble(partes[5]);
            long codigoDeBarras = Long.parseLong(partes[6]);
            String categoria = partes[7];
            boolean empacado = intToBoolean(Integer.parseInt(partes[8]));
            boolean fresco = intToBoolean(Integer.parseInt(partes[9]));
            boolean refrigerado = intToBoolean(Integer.parseInt(partes[10]));
            boolean congelado = intToBoolean(Integer.parseInt(partes[11]));
            String unidadDeMedida = partes[12];
            double peso = Double.parseDouble(partes[13]);
            double cantidad = Double.parseDouble(partes[14]);
            
            //Crear lote en el inventario
            if (verificarLote(idLote)){
            	Lote NuevoLote = new Lote(idLote,
					  					  nombreProducto,
					  					  fechaEntrega,
					  					  fechaVencimiento,
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
					  					  cantidad);
            	Lotes.put(idLote, NuevoLote);
            	agregarProductoACategoria(categoria, codigoDeBarras);
            	agregarProducto(idLote,
		  					  	nombreProducto,
		  					  	fechaEntrega,
		  					  	fechaVencimiento,
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
		  					  	NuevoLote);
            }
            linea = br.readLine();
        }
	    }	
	    catch(Exception e)
	    {
         e.printStackTrace();
	    }
	    finally
	    {
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try
         {                    
            if( null != fr )
            {   
               fr.close();     
            }                  
         }
         catch (Exception e2)
         { 
            e2.printStackTrace();
         }
	     }
	}
	
	public boolean verificarLote(int idLote) {
		
		//Si ya existe un lote con el mismo ID, no se agrega
		if (Lotes.containsKey(idLote)) {
			System.err.println("El id del lote ya ha sido ingresado");
			return false;
		}
		else{
			
			return true;
		}
	}
	
	public void agregarProductoACategoria(String categoria, long codigoDeBarras) {
		if (Categorias.containsKey(categoria)) {
			ArrayList<Long> productosCategoria = Categorias.get(categoria);
			productosCategoria.add(codigoDeBarras);
		}
		else {
			ArrayList<Long> productosCategoria = new ArrayList<Long>();
			productosCategoria.add(codigoDeBarras);
			Categorias.put(categoria, productosCategoria);
		}
	}
	
	public void agregarProducto(int idLote,
								String nombreProducto,
								Calendar fechaEntrega,
								Calendar fechaVencimiento,
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
								Lote NuevoLote) {
		
		Producto Producto;
		//Se agrega el producto al Map para luego poder
		//buscar facil el codigo de barras a través del nombre
		codigosProductos.put(nombreProducto, codigoDeBarras);
		// Para productos empacados
		if (empacado) {
			//Si ya existe, solo actualiza la cantidad del producto
			if (Productos.containsKey(codigoDeBarras)) {
				Producto = Productos.get(codigoDeBarras);
				Producto.setPrecio(precioAlPublico);
				Producto.agregarCantidad(cantidad);
				ArrayList<Lote> lotes = Producto.getLotes();
				lotes.add(NuevoLote);
			}
			//Si no existe, se crea el producto desde cero
			else {
				ArrayList<Lote> lotes = new ArrayList<Lote>();
				lotes.add(NuevoLote);
				Producto = new ProductoEmpaquetado(nombreProducto,
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
				Productos.put(codigoDeBarras, Producto);
			}
		}
		//Productos por peso
		else {
			//Si ya existe, solo actualiza la cantidad del producto
			if (Productos.containsKey(codigoDeBarras)) {
				Producto = Productos.get(codigoDeBarras);
			}
			//Si no existe, se crea el producto desde cero
			else {
				ArrayList<Lote> lotes = new ArrayList<Lote>();
				lotes.add(NuevoLote);
				Producto = new ProductoPorPeso(nombreProducto,
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
				Productos.put(codigoDeBarras, Producto);
			}
		}
		
	}
	
	private boolean intToBoolean(int intValue) {
		boolean boolValue = false;
		if (intValue >= 1) {
            boolValue = true;
        }
		return boolValue;
	}
	
	private Calendar createDate(int year, int month, int day) {
		return new GregorianCalendar();
	}
	
}
	

