package intefrazGraficaSistemaPOS;

import java.io.IOException;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class VentanaPrincipalPOS extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesVenta panelDerecha;
	
	
	public VentanaPrincipalPOS()
	{
		// Tama�o del JFrame principal
		setSize(500,500);
		// Titulo del JFrame principal
		setTitle("CAJERO");
		// Termina la ejecuci�n cuando se cierre el JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// La forma que va tomar el panel de BotonesVenta
		setLayout(new BorderLayout());
		// Inicializo los paneles
		panelDerecha = new BotonesVenta();
		// Agrego el panel(que agrego,donde lo agrego)
		add(panelDerecha,BorderLayout.EAST);
	}
	
	
	
	// Main para iniciar la aplicaci�n
	public static void main (String[] args) throws IOException
	{
		VentanaPrincipalPOS ventana = new VentanaPrincipalPOS();
		// Ubica la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);
		// Hace que se vea la ventana
		ventana.setVisible(true);

	}
}
