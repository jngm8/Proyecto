package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import modelo.Cliente;
import modelo.Combo;
import modelo.Descuento;
import modelo.Lote;
import modelo.Producto;
import modelo.ProductoEmpaquetado;
import modelo.ProductoPorPeso;
import modelo.Regalo;
import modelo.Factura;

public class Inventario 
{
	private HashMap<Long, Producto> Productos;
	private HashMap<String, Long> CodigosProductos;
	private HashMap<Integer, Lote> Lotes;	
	private HashMap<String, ArrayList<Long>> Categorias;
	private HashMap<Long, Cliente> Clientes;
	private HashMap<Long, Descuento> Descuentos;
	private HashMap<Long, Regalo> Regalos;
	private HashMap<Long, Combo> Combos;
	private HashMap<String, Long> CodigosCombos;
	private HashMap<Long, Integer> multiplicadores;
	private Factura factura;
	private int numeroFacturas;
	
	
	private static final String LOG_FILE = "./data/error.log";
	/**
     * Es el nombre de los archivos de donde se cargan y salvan los datos del inventario
     */
    private String archivoProductos = "./persistencia/Productos";
    private String archivoCodigosProductos = "./persistencia/CodigosProductos";
    private String archivoLotes = "./persistencia/Lotes";
    private String archivoCategorias = "./persistencia/Categorias";
    private String archivoClientes = "./persistencia/Clientes";
    private String archivoFacturas = "./persistencia/Facturas";
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	/*		CONSTRUCTOR
	 * 
	 * CARGAR INVENTARIO - RECUPERAR INFORMACION PERSISTENCIA
	 * 
	 */
	
	public Inventario() throws controlador.PersistenciaException
	{
		File fileProductos = new File( archivoProductos );
		File fileCodigosProductos = new File( archivoCodigosProductos );
		File fileLotes = new File( archivoLotes );
		File fileCategorias = new File( archivoCategorias );
		File fileClientes = new File( archivoClientes );
		File fileFacturas = new File( archivoFacturas);
        
		if( fileProductos.exists( ) && fileCodigosProductos.exists( ) && fileLotes.exists( ) && fileCategorias.exists( ) && fileClientes.exists( ) )
        {
            // El archivo existe: se debe recuperar de all? el estado del modelo del mundo
            try
            {
                ObjectInputStream oisProductos = new ObjectInputStream( new FileInputStream( fileProductos ) );
                Productos = ( HashMap<Long, Producto> )oisProductos.readObject( );
                oisProductos.close( );
                
                ObjectInputStream oisCodigosProductos = new ObjectInputStream( new FileInputStream( fileCodigosProductos ) );
                CodigosProductos = ( HashMap<String, Long> )oisCodigosProductos.readObject( );
                oisCodigosProductos.close( );
                
                ObjectInputStream oisLotes = new ObjectInputStream( new FileInputStream( fileLotes ) );
                Lotes = ( HashMap<Integer, Lote> )oisLotes.readObject( );
                oisLotes.close( );
                
                ObjectInputStream oisCategorias = new ObjectInputStream( new FileInputStream( fileCategorias ) );
                Categorias = ( HashMap<String,ArrayList<Long>> )oisCategorias.readObject( );
                oisCategorias.close( );
                
                ObjectInputStream oisClientes = new ObjectInputStream( new FileInputStream( fileClientes ) );
                Clientes = ( HashMap<Long, Cliente> )oisClientes.readObject( );
                oisClientes.close( );
                
                ObjectInputStream oisFacturas = new ObjectInputStream( new FileInputStream( fileFacturas ) );
                numeroFacturas = ( Integer )oisFacturas.readObject( );
                oisFacturas.close( );
                
                //Los descuentos siempre se cargan desde cero
                Descuentos = new HashMap<Long, Descuento>();
        		Regalos = new HashMap<Long, Regalo>();
        		Combos = new HashMap<Long, Combo>();
        		CodigosCombos = new HashMap<String, Long>();
        		multiplicadores = new HashMap<Long, Integer>();
            	File archivoDescuentos = new File ("./promociones/descuentos.csv");
            	cargarDescuentos(archivoDescuentos);
            	File archivoRegalos = new File ("./promociones/regalos.csv");
            	cargarRegalos(archivoRegalos);
            	File archivoCombos = new File ("./promociones/combos.csv");
            	cargarCombos(archivoCombos);
            }
            catch( Exception e )
            {
                // Se atrapan en este bloque todos los tipos de excepci?n
                registrarError( e );
                throw new PersistenciaException( "Error fatal: imposible restaurar el estado del programa (" + e.getMessage( ) + ")" );
            }
        }
        else
        {
            // El archivo no existe: es la primera vez que se ejecuta el programa
        	Productos = new HashMap<Long, Producto>();
    		CodigosProductos = new HashMap<String, Long>();
    		Lotes = new HashMap<Integer, Lote>();
    		Categorias = new HashMap<String,ArrayList<Long>>();
    		Clientes = new HashMap<Long, Cliente>();
    		numeroFacturas = 0;
    		Descuentos = new HashMap<Long, Descuento>();
    		Regalos = new HashMap<Long, Regalo>();
    		Combos = new HashMap<Long, Combo>();
    		CodigosCombos = new HashMap<String, Long>();
    		multiplicadores = new HashMap<Long, Integer>();
    		File archivoDescuentos = new File ("./promociones/descuentos.csv");
        	cargarDescuentos(archivoDescuentos);
        	File archivoRegalos = new File ("./promociones/regalos.csv");
        	cargarRegalos(archivoRegalos);
        	File archivoCombos = new File ("./promociones/combos.csv");
        	cargarCombos(archivoCombos);
        }
		
	}
	
	private void cargarDescuentos(File archivoDescuentos)
	{
	    
		FileReader fr = null;
	    BufferedReader br = null;
	    try 
	    {
	        // Apertura del fichero y creacion de BufferedReader para poder
	        // hacer una lectura comoda (disponer del metodo readLine()).
	        fr = new FileReader (archivoDescuentos);
	        br = new BufferedReader(fr);
	
	        // Lectura del fichero
	        String linea;
	        linea = br.readLine();
	        linea = br.readLine();
	        while(linea!=null) 
	        {
	        	String[] partes = linea.split(",");
	        	long codigoDeBarras = Long.parseLong(partes[0]); // Identifica el producto al cual se le va a aplicar el descuento
	            int porcentaje = Integer.parseInt(partes[1]);
	            
	            String formatoFecha = partes[2];
	            String formatoFecha2 = partes[3];
	            
	            Date date1 = sdf.parse(formatoFecha);
	            Date date2 = sdf.parse(formatoFecha2);
	            
	            Calendar fechaInicio = Calendar.getInstance();
	            Calendar fechaVencimiento = Calendar.getInstance();
	            
	            fechaInicio.setTime(date1);
	            fechaVencimiento.setTime(date2);
	            
	            agregarDescuento(codigoDeBarras, porcentaje, fechaInicio, fechaVencimiento);
	            
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
	    // Luego de cargar los descuentos en Descuentos, se itera sobre todos los productos para activarles/desactivales el descuento con el atributo descuento.
	    for(long codigoDeBarras: Productos.keySet()) 
	    {
	    	Producto producto = getProductoByCodigoDeBarras(codigoDeBarras);
	    	if(Descuentos.containsKey(codigoDeBarras)) // se verifica si el producto hace parte de los descuentos
	    	{
	    		producto.activarDescuento();
	    	}
	    	else 
	    	{
	    		producto.desactivarDescuento();	// se desactivan los productos que no figuran con descuento (sirve cuando los productos se cargan por persistencia)
	    	}
	    }
	}
	
	private void agregarDescuento(long codigoDeBarras, int porcentaje, Calendar fechaInicio,
			Calendar fechaVencimiento) 
	{
		if (fechaInicio.before(Calendar.getInstance()) && fechaVencimiento.after(Calendar.getInstance())) // Se verica que la promocion siga siendo valida
		{
			Descuento nuevoDescuento = new Descuento(codigoDeBarras, porcentaje, fechaInicio, fechaVencimiento);
			Descuentos.put(codigoDeBarras, nuevoDescuento);
		}
	}
	
	private void cargarRegalos(File archivoDescuentos)
	{
		FileReader fr = null;
	    BufferedReader br = null;
	    try 
	    {
	        // Apertura del fichero y creacion de BufferedReader para poder
	        // hacer una lectura comoda (disponer del metodo readLine()).
	        fr = new FileReader (archivoDescuentos);
	        br = new BufferedReader(fr);
	
	        // Lectura del fichero
	        String linea;
	        linea = br.readLine();
	        linea = br.readLine();
	        while(linea!=null) 
	        {
	        	String[] partes = linea.split(",");
	        	long codigoDeBarras = Long.parseLong(partes[0]); 
	            int pague = Integer.parseInt(partes[1]);
	            int lleva = Integer.parseInt(partes[2]);
	            
	            String formatoFecha = partes[3];
	            String formatoFecha2 = partes[4];
	            
	            Date date1 = sdf.parse(formatoFecha);
	            Date date2 = sdf.parse(formatoFecha2);
	            
	            Calendar fechaInicio = Calendar.getInstance();
	            Calendar fechaVencimiento = Calendar.getInstance();
	            
	            fechaInicio.setTime(date1);
	            fechaVencimiento.setTime(date2);
	            
	            agregarRegalo(codigoDeBarras, pague, lleva, fechaInicio, fechaVencimiento);
	            
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
	    // Luego de cargar los descuentos en Descuentos, se itera sobre todos los productos para activarles/desactivales el descuento con el atributo descuento.
	    for(long codigoDeBarras: Productos.keySet()) 
	    {
	    	Producto producto = getProductoByCodigoDeBarras(codigoDeBarras);
	    	if(Regalos.containsKey(codigoDeBarras)) // se verifica si el producto hace parte de los descuentos
	    	{
	    		producto.activarRegalo();
	    	}
	    	else 
	    	{
	    		producto.desactivarRegalo();	// se desactivan los productos que no figuran con descuento (sirve cuando los productos se cargan por persistencia)
	    	}
	    }
	}
	
	private void agregarRegalo(long codigoDeBarras, int pague, int lleva, Calendar fechaInicio, Calendar fechaVencimiento) 
	{
		if (fechaInicio.before(Calendar.getInstance()) && fechaVencimiento.after(Calendar.getInstance())) // Se verica que la promocion siga siendo valida
		{
			Regalo nuevoRegalo = new Regalo(codigoDeBarras, pague, lleva, fechaInicio, fechaVencimiento);
			Regalos.put(codigoDeBarras, nuevoRegalo);
		}
	}
	
	private void cargarCombos(File archivoCombos)
	{
		FileReader fr = null;
	    BufferedReader br = null;
	    try 
	    {
	        // Apertura del fichero y creacion de BufferedReader para poder
	        // hacer una lectura comoda (disponer del metodo readLine()).
	        fr = new FileReader (archivoCombos);
	        br = new BufferedReader(fr);
	
	        // Lectura del fichero
	        String linea;
	        linea = br.readLine();
	        linea = br.readLine();
	        while(linea!=null) 
	        {
	        	String[] partes = linea.split(",");
	        	String nombreCombo = partes[0];
	        	long codigo = Long.parseLong(partes[1]); 
	            int descuento = Integer.parseInt(partes[2]);
	            String productos = partes[3];
	            
	            String formatoFecha = partes[4];
	            String formatoFecha2 = partes[5];
	            
	            Date date1 = sdf.parse(formatoFecha);
	            Date date2 = sdf.parse(formatoFecha2);
	            
	            Calendar fechaInicio = Calendar.getInstance();
	            Calendar fechaVencimiento = Calendar.getInstance();
	            
	            fechaInicio.setTime(date1);
	            fechaVencimiento.setTime(date2);
	            
	            agregarCombo(nombreCombo, codigo, descuento, productos, fechaInicio, fechaVencimiento);
	            
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
	    // Luego de cargar los descuentos en Descuentos, se itera sobre todos los productos para activarles/desactivales el descuento con el atributo descuento.
	    
	}
	
	private void agregarCombo(String nombreCombo,long codigo,  int descuento, String productos, Calendar fechaInicio, Calendar fechaVencimiento) 
	{
		if (fechaInicio.before(Calendar.getInstance()) && fechaVencimiento.after(Calendar.getInstance())) // Se verica que la promocion siga siendo valida
		{
			Combo nuevoCombo = new Combo(nombreCombo, codigo, descuento, productos, fechaInicio, fechaVencimiento);
			Combos.put(codigo, nuevoCombo);
			CodigosCombos.put(nombreCombo, codigo);
		}
	}
	
	public Collection<Descuento> valoresDescuentos()
	{
		return Descuentos.values();

	}
	
	
	public Collection<Regalo> valoresRegalos()
	{
		return Regalos.values();

	}
	/* 
	 *  Metodos para cargar los lotes:
	 *  +cargarLote(): void
	 *  -verificarLote(): boolean
	 *  -agregarProductoACategoria(): void
	 *  -agregarProducto(): void
	 */
	
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
        linea = br.readLine();
        while(linea!=null) 
        {
            String[] partes = linea.split(",");
            int idLote = Integer.parseInt(partes[0]);
            String nombreProducto = partes[1];
            
            String formatoFecha = partes[2];
            String formatoFecha2 = partes[3];
            
            Date date1 = sdf.parse(formatoFecha);
            Date date2 = sdf.parse(formatoFecha2);
            
            Calendar fechaEntrega = Calendar.getInstance();
            Calendar fechaVencimiento = Calendar.getInstance();
            
            fechaEntrega.setTime(date1);
            fechaVencimiento.setTime(date2);
            
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
	
	private boolean verificarLote(int idLote) {
		
		//Si ya existe un lote con el mismo ID, no se agrega
		if (Lotes.containsKey(idLote)) {
			System.err.println("El id del lote ya ha sido ingresado");
			return false;
		}
		else{
			
			return true;
		}
	}
	
	private void agregarProductoACategoria(String categoria, long codigoDeBarras) {
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
	
	private void agregarProducto(int idLote,
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
		//buscar facil el codigo de barras a trav?s del nombre
		CodigosProductos.put(nombreProducto, codigoDeBarras);
		// Para productos empacados
		if (empacado) {
			//Si ya existe, solo actualiza la cantidad del producto
			if (Productos.containsKey(codigoDeBarras)) {
				Producto = Productos.get(codigoDeBarras);
				Producto.setPrecio(precioAlPublico);
				Producto.agregarCantidad(cantidad);
				Producto.sumarTotalProveedor(precioPagoProveedor, cantidad);
				ArrayList<Lote> lotes = Producto.getLotes();
				lotes.add(NuevoLote);
			}
			//Si no existe, se crea el producto desde cero
			else {
				ArrayList<Lote> lotes = new ArrayList<Lote>();
				lotes.add(NuevoLote);
				Producto = new ProductoEmpaquetado(nombreProducto,
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
				Producto.sumarTotalProveedor(precioPagoProveedor, cantidad);
				Productos.put(codigoDeBarras, Producto);
			}
		}
		//Productos por peso
		else {
			//Si ya existe, solo actualiza la cantidad del producto
			if (Productos.containsKey(codigoDeBarras)) {
				Producto = Productos.get(codigoDeBarras);
				Producto.setPrecio(precioAlPublico);
				Producto.agregarCantidad(cantidad);
				Producto.sumarTotalProveedor(precioPagoProveedor, cantidad);
				ArrayList<Lote> lotes = Producto.getLotes();
				lotes.add(NuevoLote);
			}
			//Si no existe, se crea el producto desde cero
			else {
				ArrayList<Lote> lotes = new ArrayList<Lote>();
				lotes.add(NuevoLote);
				Producto = new ProductoPorPeso(nombreProducto,
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
				Producto.sumarTotalProveedor(precioPagoProveedor, cantidad);
				Productos.put(codigoDeBarras, Producto);
			}
		}
		
	}
	
	
	
	private boolean intToBoolean(int intValue)
	{
		boolean boolValue = false;
		if (intValue >= 1) 
		{
            boolValue = true;
        }
		return boolValue;
	}
	
	/* 
	 *  Metodos para ver el tama?o de las estructuras de datos:
	 *  +sizeProductos(): int
	 *  +sizeLotes(): int
	 *  +sizeCategorias(): int
	 *  +sizeClientes(): int
	 */
	
	public int sizeProductos() {
		return Productos.size();
	}
	
	public int sizeLotes() {
		return Lotes.size();
	}
	
	public int sizeCategorias() {
		return Categorias.size();
	}
	
	public int sizeClientes() {
		return Clientes.size();
	}
	
	/*
	 *  Metodos relacionados con LOTE
	 *  +eliminarLote(): String
	 *  +eliminarLotesVencidos(): String
	 */
	
	public String eliminarLote(int idLote) {
		if (Lotes.containsKey(idLote)) {
			Lote Lote = Lotes.get(idLote);
			long codigoDeBarras = Lote.getCodigoDeBarras();
			Producto Producto = Productos.get(codigoDeBarras);
			Producto.quitarCantidad(Lote.getCantidad());
			Producto.borrarLote(idLote);
			Lotes.remove(idLote);
			return "El lote " + idLote + " fue eliminado exitosamente.";
		}
		else {
			return "El lote " + idLote + "no existe.";
		}
	}
	
	public String eliminarLotesVencidos(Calendar fechaVencimiento) {
		int totalEliminados = 0;
		ArrayList<Integer> idsEliminados = new ArrayList<Integer>();
		for (Lote Lote: Lotes.values()) {
			if (Lote.getFechaDeVencimiento().after(fechaVencimiento)) {
				int idLote = Lote.getIdLote();
				idsEliminados.add(idLote);
				totalEliminados += 1;
			}
		}
		for (int idLote: idsEliminados) {
			eliminarLote(idLote);
		}
		return "Se eliminaron " + totalEliminados + " lotes vencidos despues de la fecha " + sdf.format(fechaVencimiento.getTime());
	}
	
	/*
	 *  Metodos relacionados con CLIENTE
	 *  +agregarCliente(): void
	 *  +containsCliente(): boolean
	 *  +getCliente(): Cliente
	 */
	
	public void agregarCliente(Cliente Cliente) {
		Clientes.put(Cliente.getCedula(), Cliente);
	}
	
	public boolean containsCliente(long cedula) {
		 return Clientes.containsKey(cedula);
	}
	
	public Cliente getCliente(long cedula) {
		Cliente Cliente = null;
		if (Clientes.containsKey(cedula)) {
			Cliente = Clientes.get(cedula);
		}
		return Cliente;
	}
	
	/*
	 *  Metodos relacionados con PRODUCTO
	 *  +getProductoByName(): Producto
	 *  +getProductoByCodigoDeBarras(): Producto
	 *  +getCodigoProducto(): long
	 *  +verificarProducto(): boolean
	 */
	
	public Producto getProductoByName(String nombreProducto) 
	{
		Producto Producto = null;
		if (CodigosProductos.containsKey(nombreProducto)) 
		{
			long codigoDeBarras = CodigosProductos.get(nombreProducto);
			Producto = Productos.get(codigoDeBarras);
		}
		return Producto;
	}
	
	public Producto getProductoByCodigoDeBarras(long codigoDeBarras) 
	{
		Producto Producto = null;
		Producto = Productos.get(codigoDeBarras);
		return Producto;
	}
	
	public String getNombreProducto(long codigoDeBarras)
	{
		Producto prod = Productos.get(codigoDeBarras);
		
		return prod.getNombre();
	}
	
	public long getCodigoProducto(String nombreProducto) 
	{
		long codigoDeBarras = -1;
		if (CodigosProductos.containsKey(nombreProducto)) 
		{
			codigoDeBarras = CodigosProductos.get(nombreProducto);
		}
		return codigoDeBarras;
	}

	public boolean verificarProducto(long codigoDeBarras) {
		if (Productos.containsKey(codigoDeBarras)){
			return true;
		}
		return false;
	}
	
	public boolean verificarNombreProducto(String nombre) {
		if (CodigosProductos.containsKey(nombre))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Metodos de Factura
	 * @throws PersistenciaException
	 */
	
	public int getTotalFactura()
	{
		return (int) factura.getValorTotalCompra();
	}
	
	public int getPuntosCompra()
	{
		int puntos = factura.getPuntosCompra();
		
		return puntos;
	}
	
	public int getPuntosCliente(Cliente cliente)
	{
		int puntosCliente = cliente.getAcumuladoPuntos();
		
		return puntosCliente;
	}
	
	
	
	/*
	 *  Metodos para guardar el inventario - PERSISTENCIA
	 *  +salvarInventario(): void
	 *  +registrarError(): void
	 */
	
	public void salvarInventario() throws PersistenciaException{
		try
        {
            ObjectOutputStream oosProductos = new ObjectOutputStream( new FileOutputStream( archivoProductos ) );
            oosProductos.writeObject( Productos );
            oosProductos.close( );
            
            ObjectOutputStream oosCodigosProductos = new ObjectOutputStream( new FileOutputStream( archivoCodigosProductos ) );
            oosCodigosProductos.writeObject( CodigosProductos );
            oosCodigosProductos.close( );
            
            ObjectOutputStream oosLotes = new ObjectOutputStream( new FileOutputStream( archivoLotes ) );
            oosLotes.writeObject( Lotes );
            oosLotes.close( );
            
            ObjectOutputStream oosCategorias = new ObjectOutputStream( new FileOutputStream( archivoCategorias ) );
            oosCategorias.writeObject( Categorias );
            oosCategorias.close( );
            
            ObjectOutputStream oosClientes = new ObjectOutputStream( new FileOutputStream( archivoClientes ) );
            oosClientes.writeObject( Clientes );
            oosClientes.close( );
            
            ObjectOutputStream oosFacturas = new ObjectOutputStream( new FileOutputStream( archivoFacturas ) );
            oosFacturas.writeObject( numeroFacturas );
            oosFacturas.close( );
            
        }
        catch( IOException e )
        {
            registrarError( e );
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }
	}
	
	public void registrarError( Exception excepcion )
    {
        try
        {
            FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            log.println( "---------------------------------------" );
            log.println( "Inventario.java :" + new Date( ).toString( ) );
            log.println( "---------------------------------------" );
            excepcion.printStackTrace( log );
            log.close( );
            out.close( );
        }
        catch( IOException e )
        {
            excepcion.printStackTrace( );
            e.printStackTrace( );
        }
    }
	
	
	/*
	 *  Metodos para una venta
	 */

	public void iniciarVenta() {
		//	Crea una nueva instancia de la factura para empezar la venta
		factura = new Factura();
	}
	
	
	public void venderProducto(long codigoDeBarras, int cantidad) throws Exception 
	{
		
		Regalo regalo;
		if (Regalos.containsKey(codigoDeBarras))
		{
			regalo = Regalos.get(codigoDeBarras);
		}
		else 
		{
			regalo = new Regalo(0,0,0,Calendar.getInstance(), Calendar.getInstance());
		}
			
		if (Combos.containsKey(codigoDeBarras))
		{
			Combo combo = Combos.get(codigoDeBarras);
			int descuento = combo.getDescuento();
			HashMap<String, Integer> productosCombo = combo.getProductos();
			for(String nombreProducto: productosCombo.keySet())
			{
				double unidadesDisponibles = getProductoByName(nombreProducto).getCantidad();
				if(unidadesDisponibles < cantidad*productosCombo.get(nombreProducto))
				{
					throw new Exception("No hay unidades disponibles suficientes para los combos que se desean comprar");
				}
			}
			double precioCombo = 0;
			for(String nombreProducto: productosCombo.keySet())
			{
				Producto producto = getProductoByName(nombreProducto);
				precioCombo += producto.getPrecio();
				producto.vender(cantidad*productosCombo.get(nombreProducto));
			}
			String registroFactura = combo.getNombre() + "\t\t" + ((precioCombo-(precioCombo*descuento)/100)) + "x" + cantidad + "\n\t\t\t" + ((precioCombo-(precioCombo*descuento)/100))*cantidad + "\n";
			factura.agregarProducto(registroFactura, ((precioCombo-(precioCombo*descuento)/100))*cantidad);
		}
		else 
		{
			Producto producto = Productos.get(codigoDeBarras);
			if (producto.getDescuento())
			{
				
				Descuento descuento = Descuentos.get(codigoDeBarras); // Obtenemos el descuento del producto
				int porcentajeDescuento = descuento.getPorcentaje();
				double valorConDescuento = producto.getPrecio()-((producto.getPrecio()*porcentajeDescuento)/100); // precioNormal - valorDescuento
				producto.vender(cantidad);
				String registroFactura = producto.getNombre() + "\t\t" + producto.getPrecio() + "x" + cantidad + "\n\tDescuento del " + porcentajeDescuento + "% aplicado:\t" + valorConDescuento*cantidad + "\n";
				factura.agregarProducto(registroFactura, valorConDescuento*cantidad);
			}
			else if (producto.getRegalo() && cantidad >= regalo.getLleva()) // se usa elif porque las promociones no son acumulables
			{
				regalo = Regalos.get(codigoDeBarras);
				int pague = regalo.getPague();
				int lleva = regalo.getLleva();
				int numeroPromos = (int)(cantidad/lleva);	// saca el numero de promociones que caben en la cantidad que se quiere llevar
				int unidadesQuePagan = numeroPromos*pague;		// unidades que hacen parte de la promocion
				int unidadesQueLlevan = numeroPromos*lleva;
				if (unidadesQueLlevan == cantidad)		// si toda la cantidad que lleva hace parte de la promo
				{
					String registroFactura = producto.getNombre() + "\t\t" + producto.getPrecio() + "x" + cantidad + "\n\tRegalo: pague " + pague + " lleve " + lleva + "\t" + unidadesQuePagan*producto.getPrecio() + "\n";
					factura.agregarProducto(registroFactura, unidadesQuePagan*producto.getPrecio());
				}
				else
				{
					String registroFactura = producto.getNombre() + "\t\t" + producto.getPrecio() + "x" + unidadesQueLlevan + "\n\tRegalo: pague " + pague + " lleve " + lleva + "\t" + unidadesQuePagan*producto.getPrecio() + "\n";
					factura.agregarProducto(registroFactura, unidadesQuePagan*producto.getPrecio());
					int unidadesFueraPromo = cantidad - unidadesQueLlevan;
					String registroFactura2 = producto.getNombre() + "\t\t" + producto.getPrecio() + "x" + unidadesFueraPromo + "\n\t\t\t" + unidadesFueraPromo*producto.getPrecio() + "\n";
					factura.agregarProducto(registroFactura2, unidadesFueraPromo*producto.getPrecio());
				}
			}
			else
			{
				producto.vender(cantidad);
				String registroFactura = producto.getNombre() + "\t\t" + producto.getPrecio() + "x" + cantidad + "\n\t\t\t" + producto.getPrecio()*cantidad + "\n";
				factura.agregarProducto(registroFactura, producto.getPrecio()*cantidad);
			}
		}
		
	}
	
	public void terminarVenta(Cliente cliente, int puntosRedimidos) 
	{
		if (cliente != null) 
		{			
			if (puntosRedimidos > 0)
			{
				cliente.restarPuntos(puntosRedimidos);
				cliente.sumarAcumuladoPuntos(factura.getPuntosCompra());
				factura.generarFactura(cliente, numeroFacturas, puntosRedimidos);
				
				numeroFacturas += 1;
			}
			
			else
			{
				cliente.sumarAcumuladoPuntos(factura.getPuntosCompra());
				factura.generarFactura(cliente, numeroFacturas, puntosRedimidos);
				
				numeroFacturas += 1;
			}
		}
		else 
		{
			factura.generarFacturaSinCliente(numeroFacturas);
			numeroFacturas += 1;
		}		
	}

	public boolean verificarNombreCombo(String nombre) 
	{
		return CodigosCombos.containsKey(nombre);
	}

	public long getCodigoCombo(String nombre) {
		
		return CodigosCombos.get(nombre);
	}
}
	

