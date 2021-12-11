package interfazGraficaSistemaEncargado;

import java.io.File;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controlador.Inventario;
import controlador.PersistenciaException;
import intefrazGraficaSistemaPOS.ImagenPOS;
import interfazGraficaSistemaEncargado.VentanaPrincipalE;
import modelo.Cliente;
import modelo.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class VentanaGrafica extends JFrame
{
	private JPanel panelGrafica;
	
	public VentanaGrafica()
	
	{
		// Tamaño del JFrame principal
		setSize(400,400);
		// Titulo del JFrame principal
		setTitle("GRAFICA DE PRODUCTOS");
		// Termina la ejecución cuando se cierre el JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelGrafica = new ImagenPOS();
		add(panelGrafica,BorderLayout.NORTH);
		
		
		
		try 
		{
			salirAPL();
		} 
		catch (PersistenciaException e) 
		{
			e.printStackTrace();
		}
		
	
	}
	
	//Salida aplicacion grande
	public void salirAPL() throws PersistenciaException
	{
		try 
		
		{
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
			{
				confirmarSalidaPrincipals();

			}
			});
		
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}

	}
	
	public void confirmarSalidaPrincipals() 
	{
		int valor = JOptionPane.showConfirmDialog(this,"Los datos han sido guardados,¿Quiere salir de la APP?","Mensaje de guardado",JOptionPane.YES_NO_CANCEL_OPTION);
		
		if ( valor == JOptionPane.YES_OPTION)
		{
			JOptionPane.showMessageDialog(this,"Gracias por venir, y Feliz Navidad!","¡FELIZ NAVIDAD!",JOptionPane.INFORMATION_MESSAGE);
		}
		System.exit(0);
	}
	
	
	// Main para iniciar la aplicación
	public static void main (String[] args) throws IOException
	{
		VentanaGrafica ventana = new VentanaGrafica();
		// Ubica la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);
		// Hace que se vea la ventana
		ventana.setVisible(true);

	}

}
