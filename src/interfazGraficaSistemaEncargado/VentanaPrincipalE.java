package interfazGraficaSistemaEncargado;

import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

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
	
	// Importa la l�gica del programa
	
	private Inventario modelo;	
	
	public VentanaPrincipalE()
	{
		// Tama�o del JFrame principal
		setSize(700,700);
		// Titulo del JFrame principal
		setTitle("INVENTARIO");
		// Termina la ejecuci�n cuando se cierre el JFrame
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
	
	
	public void InformacionInventario() 
	{
		JDialog dialogoInventario = new JDialog();
		dialogoInventario.setSize(400,300);
		dialogoInventario.setLocationRelativeTo(this);
	
		dialogoInventario.setTitle("INFORMACION DEL INVENTARIO");
		dialogoInventario.add(new JLabel("Numero de productos disponibles: " + modelo.sizeProductos()));
		dialogoInventario.add(new JLabel("Total de lotes disponibles: " + modelo.sizeLotes()));
		dialogoInventario.add(new JLabel("Numero de g�ndolas creadas: " + modelo.sizeCategorias()));
		dialogoInventario.add(new JLabel("Numero de clientes registrados: " + modelo.sizeClientes()));
		
		dialogoInventario.setVisible(true);
		
	}
	
	
	// Main para iniciar la aplicaci�n
	public static void main (String[] args) throws IOException
	{
		VentanaPrincipalE ventana = new VentanaPrincipalE();
		// Ubica la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);
		// Hace que se vea la ventana
		ventana.setVisible(true);

	}
}