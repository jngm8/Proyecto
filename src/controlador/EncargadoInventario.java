package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.Producto;
import modelo.ProductoEmpaquetado;

public class EncargadoInventario {

	private void cargarLote(File archivoMenu)
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
        String[] encabezados = linea.split(";");
        System.out.println(encabezados);
        while(linea!=null) 
        {
            String[] partes = linea.split(";");
            int idLote = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            
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
            boolean empacado = intToBoolean(Integer.parseInt(partes[8]));
            boolean fresco = intToBoolean(Integer.parseInt(partes[9]));
            boolean refrigerado = intToBoolean(Integer.parseInt(partes[10]));
            boolean congelado = intToBoolean(Integer.parseInt(partes[11]));
            String unidadDeMedida = partes[12];
            double peso = Double.parseDouble(partes[13]);
            double cantidad = Double.parseDouble(partes[14]);
            //Crear lote en el inventario
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
