package intefrazGraficaSistemaPOS;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.Inventario;
import controlador.PersistenciaException;
import modelo.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class VentanaPrincipalPOS extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesVenta panelDerecha;
	
	private ImagenPOS panelArriba;	
	
	private panelCentralPOS panelCentral;
	
	// Importa la lógica del programa
	
	private Inventario modelo;
	
	private Cliente cliente;

	
	// Paneles extra
	
	private JPanel panelCliente;
	
	// Labels extra
	
	private JLabel lblNombre;
	
	private JLabel lblCedula;
	
	private JLabel lblPuntos;
	
	private JLabel lblEdad;
	
	private JLabel lblSexo;
	
	private JLabel lblEstado;
	
	private JLabel lblLaboral;

	
	
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
				panelCliente= new JPanel();
				panelCliente.setLayout(new GridLayout(4,1));
				JDialog dialog = new JDialog();
				dialog.setSize(300,300);
				dialog.setLocationRelativeTo(this);
				dialog.add(panelCliente);
				dialog.setVisible(true);
				
				lblNombre = new JLabel("Nombre del cliente: " + cliente.getNombre());
				panelCliente.add(lblNombre);
				
				lblCedula= new JLabel("Número de cedula: " + cliente.getCedula());
				panelCliente.add(lblCedula);
				
				lblPuntos = new JLabel("Puntos Acumulados: " + cliente.getAcumuladoPuntos());
				panelCliente.add(lblPuntos);
				
				lblEdad = new JLabel("Edad: " + cliente.getEdad());
				panelCliente.add(lblEdad);
				
				lblSexo = new JLabel("Sexo: " + cliente.getSexo());
				panelCliente.add(lblSexo);
				
				lblEstado = new JLabel(" Estado civil: " + cliente.getEstadoCivil());
				panelCliente.add(lblEstado);
				
				lblLaboral = new JLabel("Situación Laboral: " + cliente.getEstadoCivil());
				panelCliente.add(lblLaboral);

			}
			else 
			{
				JOptionPane.showMessageDialog(this,"\nEl cliente no fue encontrado \n","Mensaje de error",JOptionPane.ERROR_MESSAGE);

			}
		}

		else if (rta.equals("No"))
		{
			String registrarse = JOptionPane.showInputDialog("¿Se quiere registrar(Si o No)");
			
			if (registrarse.equals("Si"))
			{

			}

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
			JOptionPane.showMessageDialog(this,"\nEl cliente con número de cedula " + cedula + " no fue encontrado\n","Mensaje de error",JOptionPane.ERROR_MESSAGE);
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
