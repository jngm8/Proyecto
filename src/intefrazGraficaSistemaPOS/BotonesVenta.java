package intefrazGraficaSistemaPOS;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class BotonesVenta extends JPanel
{
	// Inicia los botones que va tener este panel
	private JButton btnIniciarVenta;
	private JButton btnInfoCliente;
	private JPanel panelBotones;

	
	public BotonesVenta()
	{
		// Define que forma deben tener los botones y su distribuci�n
		setLayout(new GridLayout(2,1));
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(2,1,0,50));// filas,columnas,espacio columnas,espacio filas
		
		//Creaci�n de los botones
		
		btnIniciarVenta = new JButton("INICIAR VENTA");
		panelBotones.add(btnIniciarVenta);

		btnInfoCliente = new JButton("CONSULTAR INFORMACI�N DEL CLIENTE");
		panelBotones.add(btnInfoCliente);
		
		//Se agrega el panel
		add(panelBotones);
		
		
		
		
	}
	
}
