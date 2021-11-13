package intefrazGraficaSistemaPOS;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import interfazGraficaSistemaEncargado.VentanaPrincipalE;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
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
	private JPanel panelClientes;
	
	// Inicia los botones del información del cliente
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblCedula;
	private JTextField txtCedula;
	private JLabel lblPuntos;
	private JTextField txtPuntos;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JLabel lblSexo;
	private JTextField txtSexo;
	
	//Constantes para que los botones reaccione distinto. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String VENTA = "INICIAR VENTA";
	private final static String INFO = "INFO CLIENTE";
	private final static String SALIR = "SALIR DEL APP";
	
	/// Atributo ventana pricipal
	
	private VentanaPrincipalPOS principal;

	
	// Constructor
	public BotonesVenta(VentanaPrincipalPOS principalp)
	
	{
		
		principal = principalp;
		
		// Define que forma deben tener los botones y su distribución
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                    ¡Seleccione una opción!"));
		panelBotones = new JPanel();
		add(panelBotones, BorderLayout.NORTH);
		panelBotones.setLayout(new GridLayout(3,1,0,10));// filas,columnas,espacio columnas,espacio filas


		
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
		
		//Se agrega el panel
		add(panelBotones);
		
		// Se crea el panel de abajo del información del cliente
		panelClientes= new JPanel();
		add(panelClientes, BorderLayout.SOUTH);
		panelClientes.setLayout(new GridLayout(5,2));
		panelClientes.setBorder(new TitledBorder("                       Informacion del cliente "));

		
		lblNombre = new JLabel("Nombre del cliente");
		panelClientes.add(lblNombre);
		txtNombre= new JTextField(" ");
		txtNombre.setEditable(false);
		panelClientes.add(txtNombre);

		lblCedula = new JLabel("Número de cedula");
		panelClientes.add(lblCedula);
		txtCedula= new JTextField(" ");
		txtCedula.setEditable(false);
		panelClientes.add(txtCedula);
		
		lblPuntos = new JLabel("Número de cedula");
		panelClientes.add(lblPuntos);
		txtPuntos= new JTextField(" ");
		txtPuntos.setEditable(false);
		panelClientes.add(txtPuntos);
		
		lblEdad = new JLabel("Edad");
		panelClientes.add(lblEdad);
		txtEdad= new JTextField(" ");
		txtEdad.setEditable(false);
		panelClientes.add(txtEdad);
		
		lblSexo = new JLabel("Sexo");
		panelClientes.add(lblSexo);
		txtSexo= new JTextField(" ");
		txtSexo.setEditable(false);
		panelClientes.add(txtSexo);



		
		
		
		
	}
	
	// Metodos del action listener para que cuando se presione el botón salga un mensaje
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(VENTA)) 
		{
			principal.Inicioventa();
		}
		else if (comando.equals(INFO))
		{
			principal.ConsultarInfoPuntos();
		}
		else if (comando.equals(SALIR))
		{
			JOptionPane.showMessageDialog(this, "Saliendo de la App");
		}
		
		
		
		
		
	}
	
}
