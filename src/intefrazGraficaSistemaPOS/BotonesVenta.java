package intefrazGraficaSistemaPOS;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JOptionPane;



public class BotonesVenta extends JPanel
{
	private static final Color Azul = null;
	// Inicia los botones que va tener este panel
	private JButton btnIniciarVenta;
	private JButton btnInfoCliente;
	private JButton btnSalirApp;
	private JPanel panelBotones;

	
	public BotonesVenta()
	{
		// Define que forma deben tener los botones y su distribución
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                    ¡Seleccione una opción!"));
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(4,1,0,10));// filas,columnas,espacio columnas,espacio filas
		add(new JLabel());
		
		//Creación de los botones
		
		btnIniciarVenta = new JButton("INICIAR VENTA");
		btnIniciarVenta.setBackground(new java.awt.Color(20,186,107));
		btnIniciarVenta.setForeground(Color.BLACK);
		panelBotones.add(btnIniciarVenta);

		btnInfoCliente = new JButton("CONSULTAR INFORMACIÓN DEL CLIENTE");
		btnInfoCliente.setBackground(new java.awt.Color(20,186,107));
		btnInfoCliente.setForeground(Color.BLACK);
		panelBotones.add(btnInfoCliente);
		
		btnSalirApp = new JButton("SALIR DE LA APLICACIÓN");
		btnSalirApp.setBackground(new java.awt.Color(20,186,107));
		btnSalirApp.setForeground(Color.BLACK);
		panelBotones.add(btnSalirApp);
		
		//Se agrega el panel
		add(panelBotones);
		
		
		
		
	}
	
}
