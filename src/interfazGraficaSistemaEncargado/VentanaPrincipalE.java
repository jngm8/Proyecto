package interfazGraficaSistemaEncargado;

import java.io.IOException;

import javax.swing.JFrame;

import intefrazGraficaSistemaPOS.ImagenPOS;

import java.awt.BorderLayout;

public class VentanaPrincipalE extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesCargarInfo panelDerecha;
	
	private Imagen panelArriba;	
	
	
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
		panelDerecha = new BotonesCargarInfo();
		// Agrego el panel(que agrego,donde lo agrego)
		add(panelDerecha,BorderLayout.EAST);
		
		panelArriba = new Imagen();
		add(panelArriba,BorderLayout.NORTH);
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