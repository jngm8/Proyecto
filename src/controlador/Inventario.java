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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Cliente;
import modelo.Lote;
import modelo.Producto;
import modelo.ProductoEmpaquetado;
import modelo.ProductoPorPeso;


public class Inventario 
{
	private HashMap<Long, Producto> Productos;
	private HashMap<String, Long> CodigosProductos;
	private HashMap<Integer, Lote> Lotes;	
	private HashMap<String, ArrayList<Long>> Categorias;
	private HashMap<Long, Cliente> Clientes;
	
	
	private static final String LOG_FILE = "./data/error.log";
	/**
     * Es el nombre del archivo de donde se cargan y salvan los discos
     */
    private String archivoProductos = "./persistencia/Productos";
    private String archivoCodigosProductos = "./persistencia/CodigosProductos";
    private String archivoLotes = "./persistencia/Lotes";
    private String archivoCategorias = "./persistencia/Categorias";
    private String archivoClientes = "./persistencia/Clientes";
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public Inventario() throws controlador.PersistenciaException
	{
		File fileProductos = new File( archivoProductos );
		File fileCodigosProductos = new File( archivoCodigosProductos );
		File fileLotes = new File( archivoLotes );
		File fileCategorias = new File( archivoCategorias );
		File fileClientes = new File( archivoClientes );
        if( fileProductos.exists( ) && fileCodigosProductos.exists( ) && fileLotes.exists( ) && fileCategorias.exists( ) && fileClientes.exists( ) )
        {
            // El archivo existe: se debe recuperar de all� el estado del modelo del mundo
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
                
            }
            catch( Exception e )
            {
                // Se atrapan en este bloque todos los tipos de excepci�n
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
        }
		
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
		//buscar facil el codigo de barras a trav�s del nombre
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
	
	public void agregarCliente(Cliente Cliente) {
		Clientes.put(Cliente.getCedula(), Cliente);
	}
	
	public boolean containsCliente(long cedula) {
		 return Clientes.containsKey(cedula);
	}
	
	public Producto getProducto(String nombreProducto) {
		Producto Producto = null;
		if (CodigosProductos.containsKey(nombreProducto)) {
			long codigoDeBarras = CodigosProductos.get(nombreProducto);
			Producto = Productos.get(codigoDeBarras);
		}
		return Producto;
	}
	
	public Cliente getCliente(long cedula) {
		Cliente Cliente = null;
		if (Clientes.containsKey(cedula)) {
			Cliente = Clientes.get(cedula);
		}
		return Cliente;
	}
	
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
	
	private boolean intToBoolean(int intValue) {
		boolean boolValue = false;
		if (intValue >= 1) {
            boolValue = true;
        }
		return boolValue;
	}
}
	
