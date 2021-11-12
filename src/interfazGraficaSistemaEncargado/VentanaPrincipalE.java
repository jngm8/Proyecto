package interfazGraficaSistemaEncargado;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlador.Inventario;
import controlador.PersistenciaException;
import intefrazGraficaSistemaPOS.ImagenPOS;

import java.awt.BorderLayout;

public class VentanaPrincipalE extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesCargarInfo panelDerecha;
	
	private Imagen panelArriba;	
	
	private panelCentralE panelCentro;
	
	// Importa la lógica del programa
	
	private Inventario modelo;
	
	
	public VentanaPrincipalE()
	{
		// Tamaño del JFrame principal
		setSize(700,700);
		// Titulo del JFrame principal
		setTitle("INVENTARIO");
		// Termina la ejecución cuando se cierre el JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// La forma que va tomar el panel de BotonesCargarInfo
		setLayout(new BorderLayout());
		// Inicializa los paneles
		panelDerecha = new BotonesCargarInfo(this);
		// Agrego el panel(que agrego,donde lo agrego)
		add(panelDerecha,BorderLayout.EAST);
		
		panelArriba = new Imagen();
		add(panelArriba,BorderLayout.NORTH);
		
		panelCentro = new panelCentralE();
		add(panelCentro,BorderLayout.WEST);
		
		try 
		{
			modelo = new Inventario();
		} 
		catch (PersistenciaException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void cargarArchivo()
	{
		
		String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo .csv de los lotes ubicado en la carpeta data");
		try
		{
			File archivo = new File ("./data/" + nombreArchivo + ".csv");
			modelo.cargarLote(archivo);
		}
		catch(Exception e)
		{
			//Referencia el medio del panel,mensaje,titulo,logo del error
			JOptionPane.showMessageDialog(this,"No se encontro: "+ nombreArchivo + ". Digite de nuevo","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}
	// Main para iniciar la aplicación
	public static void main (String[] args) throws IOException
	{
		VentanaPrincipalE ventana = new VentanaPrincipalE();
		// Ubica la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);
		// Hace que se vea la ventana
		ventana.setVisible(true);

	}
}