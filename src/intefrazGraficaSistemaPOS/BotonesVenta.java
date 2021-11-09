package intefrazGraficaSistemaPOS;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.Color;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JOptionPane;



public class BotonesVenta extends JPanel implements ActionListener
{
	// Inicia los botones que va tener este panel
	private JButton btnIniciarVenta;
	private JButton btnInfoCliente;
	private JButton btnSalirApp;
	private JPanel panelBotones;
	
	//Constantes para que los botones reaccione distinto. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String VENTA = "INICIAR VENTA";
	private final static String INFO = "INFO CLIENTE";
	private final static String SALIR = "SALIR DEL APP";

	
	// Constructor
	public BotonesVenta()
	
	{
		// Define que forma deben tener los botones y su distribución
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                    ¡Seleccione una opción!"));
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(3,1,0,10));// filas,columnas,espacio columnas,espacio filas
		add(new JLabel());
		
		//Creación de los botones,fondos,listener para que el botón reaccione,command para que reaccione distinto a los demás,agregar el botón,agregar al panel
		
		btnIniciarVenta = new JButton("INICIAR VENTA");
		btnIniciarVenta.setBackground(new java.awt.Color(143,171,237));
		btnIniciarVenta.setForeground(Color.BLACK);
		btnIniciarVenta.addActionListener(this);
		btnIniciarVenta.setActionCommand(VENTA);
		panelBotones.add(btnIniciarVenta);

		btnInfoCliente = new JButton("CONSULTAR INFORMACIÓN DEL CLIENTE");
		btnInfoCliente.setBackground(new java.awt.Color(143,171,237));
		btnInfoCliente.setForeground(Color.BLACK);
		btnInfoCliente.addActionListener(this);
		btnInfoCliente.setActionCommand(INFO);
		panelBotones.add(btnInfoCliente);
		
		btnSalirApp = new JButton("SALIR DE LA APLICACIÓN");
		btnSalirApp.setBackground(new java.awt.Color(143,171,237));
		btnSalirApp.setForeground(Color.BLACK);
		btnSalirApp.addActionListener(this);
		btnSalirApp.setActionCommand(SALIR);
		panelBotones.add(btnSalirApp);
		
		Color Verdee = new Color(223, 143, 252);
		panelBotones.setBackground(Verdee);

		
		//Se agrega el panel
		add(panelBotones);
		
		// agregar fondo al panel 
		Color Verde = new Color(223, 143, 252);
		setBackground(Verde);

		
		
		
		
	}
	
	// Metodos del action listener para que cuando se presione el botón salga un mensaje
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(VENTA)) 
		{
			JOptionPane.showMessageDialog(this, "La venta se inicia");

		}
		else if (comando.equals(INFO))
		{
			JOptionPane.showMessageDialog(this, "Informacion del cliente");
		}
		
		else if (comando.equals(SALIR))
		{
			JOptionPane.showMessageDialog(this, "Saliendo de la App");
		}
		
		
		
		
		
	}
	
}
