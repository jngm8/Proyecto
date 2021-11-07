package interfazGraficaSistemaEncargado;

import java.io.IOException;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class VentanaPrincipalE extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesCargarInfo panelDerecha;
	
	
	public VentanaPrincipalE()
	{
		// Tama�o del JFrame principal
		setSize(500,500);
		// Titulo del JFrame principal
		setTitle("INVENTARIO");
		// Termina la ejecuci�n cuando se cierre el JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// La forma que va tomar el panel de BotonesCargarInfo
		setLayout(new BorderLayout());
		// Inicializa los paneles
		panelDerecha = new BotonesCargarInfo();
		// Agrego el panel(que agrego,donde lo agrego)
		add(panelDerecha,BorderLayout.EAST);
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