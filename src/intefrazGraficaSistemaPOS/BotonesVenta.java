package intefrazGraficaSistemaPOS;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class BotonesVenta extends JPanel
{
	// Inicia los botones que va tener este panel
	private JButton btnIniciarVenta;
	private JButton btnInfoCliente;
	private JButton btnSalirApp;
	private JPanel panelBotones;

	
	public BotonesVenta()
	{
		// Define que forma deben tener los botones y su distribuci�n
		setLayout(new GridLayout(3,1));
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(5,1,0,20));// filas,columnas,espacio columnas,espacio filas
		
		//Creaci�n de los botones
		
		btnIniciarVenta = new JButton("INICIAR VENTA");
		panelBotones.add(btnIniciarVenta);

		btnInfoCliente = new JButton("CONSULTAR INFORMACI�N DEL CLIENTE");
		panelBotones.add(btnInfoCliente);
		
		btnSalirApp = new JButton("SALIR DE LA APLICACI�N");
		panelBotones.add(btnSalirApp);
		
		//Se agrega el panel
		add(panelBotones);
		
		
		
		
	}
	
}
