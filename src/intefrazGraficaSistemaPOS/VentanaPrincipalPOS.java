package intefrazGraficaSistemaPOS;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controlador.Inventario;
import controlador.PersistenciaException;
import modelo.Cliente;

import java.awt.BorderLayout;

public class VentanaPrincipalPOS extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesVenta panelDerecha;
	
	private ImagenPOS panelArriba;	
	
	private panelCentralPOS panelCentral;
	
	// Importa la lógica del programa
	
	private Inventario modelo;
	
	public VentanaPrincipalPOS()
	
	{
		// Tamaño del JFrame principal
		setSize(700,700);
		// Titulo del JFrame principal
		setTitle("CAJERO");
		// Termina la ejecución cuando se cierre el JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// La forma que va tomar el panel de BotonesVenta
		setLayout(new BorderLayout());
		// Inicializo los paneles
		panelDerecha = new BotonesVenta(this);
		// Agrego el panel(que agrego,donde lo agrego)
		add(panelDerecha,BorderLayout.EAST);
		
		//Mismo procedimiento para ImagenPOS
		
		panelArriba = new ImagenPOS();
		add(panelArriba,BorderLayout.NORTH);
		
		panelCentral = new panelCentralPOS();
		add(panelCentral,BorderLayout.WEST);
		
		try 
		{
			modelo = new Inventario();
		} 
		catch (PersistenciaException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void Inicioventa()
	{
		
		String rta = JOptionPane.showInputDialog("¿Se encuentra registrado(Si o No)");
		
		if (rta.equals("Si"))
		{

			long registrado = Long.parseLong(JOptionPane.showInputDialog("Ingrese su numero de cedula"));
			Cliente numeroCliente = modelo.getCliente(registrado);
			
			if (numeroCliente != null) 
			{
				JOptionPane.showMessageDialog(this,"Esta es la información del cliente con el número de cedula ingresado: \n");

			}
			else 
			{
				JOptionPane.showMessageDialog(this,"\nEl cliente no fue encontrado \n");
			}
		}

		else if (rta.equals("No"))
		{
			
		}
		
		else
		{
			JOptionPane.showMessageDialog(this,"Seleccione una opción válida","Mensaje de error",JOptionPane.ERROR_MESSAGE);

		}
	}
	
	
	public void ConsultarInfoPuntos()
	
	{
		Cliente Cliente;
		
		long cedula = Long.parseLong(JOptionPane.showInputDialog("Por favor ingrese el numero de cedula "));
		Cliente = modelo.getCliente(cedula);
		if (Cliente != null) 
		{
			Cliente.PrintInfo();
		}
		else 
		{
			JOptionPane.showMessageDialog(this,"\nEl cliente con numero de cedula " + cedula + " no fue encontrado\n");
		}

	}
	
	
	// Main para iniciar la aplicación
	public static void main (String[] args) throws IOException
	{
		VentanaPrincipalPOS ventana = new VentanaPrincipalPOS();
		// Ubica la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);
		// Hace que se vea la ventana
		ventana.setVisible(true);

	}
}
