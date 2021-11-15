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
				registrarCliente();
			}
			
			else
			{
				JOptionPane.showMessageDialog(this,"Puede hacer la compra sin acumular puntos","Venta Anonima",JOptionPane.CANCEL_OPTION);
				
				//Continua el proceso de venta para agregar un producto de forma anonima
				
			}

		}
		
		else
		{
			JOptionPane.showMessageDialog(this,"Seleccione una opción válida","Mensaje de error",JOptionPane.ERROR_MESSAGE);

		}
	}
	
	// Metodo para regitrar a un cliente si asi lo decide
	
	private Cliente registrarCliente() {
		Cliente Cliente;
		long cedula = Long.parseLong(JOptionPane.showInputDialog("Ingrese el numero de cedula del cliente"));
		if (!modelo.containsCliente(cedula)) 
		{
			String nombre = JOptionPane.showInputDialog("Ingrese su nombre");
			int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente"));
			String sexo = JOptionPane.showInputDialog("Ingrese el sexo del cliente al nacer");
			String estadoCivil = JOptionPane.showInputDialog("Ingrese el estado civil del cliente");
			String situacionLaboral = JOptionPane.showInputDialog("Ingrese la situacion laboral del cliente");
			Cliente = new Cliente(nombre,cedula, edad, sexo, estadoCivil, situacionLaboral);
			modelo.agregarCliente(Cliente);
			return Cliente;
		}
		else 
		{
			JOptionPane.showMessageDialog(this,"El número de cedula ya esta agregado","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public void ConsultarInfoPuntos()
	
	{
		Cliente Cliente;
		
		long cedula = Long.parseLong(JOptionPane.showInputDialog("Por favor ingrese el numero de cedula "));
		Cliente = modelo.getCliente(cedula);
		if (Cliente != null) 
		{
			panelDerecha.refrescar(Cliente);
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
