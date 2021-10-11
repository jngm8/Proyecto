package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;

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
            Calendar fechaEntrega = xd;
            String[] formatoFecha2 = partes[3].split("-");
            Calendar fechaVencimiento = xd;
            
            double precioPagoProveedor = Double.parseDouble(partes[4]);
            double precioAlPublico = Double.parseDouble(partes[5]);
            boolean empacado = intToBoolean(Integer.parseInt(partes[8]));
            
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
	
	
}
