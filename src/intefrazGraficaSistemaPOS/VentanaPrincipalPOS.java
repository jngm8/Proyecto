package intefrazGraficaSistemaPOS;

import java.io.File;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.Inventario;
import controlador.PersistenciaException;
import interfazGraficaSistemaEncargado.VentanaPrincipalE;
import modelo.Cliente;
import modelo.Producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipalPOS extends JFrame implements ActionListener
{
	// Declaro el atributo de cada panel
	
	private BotonesVenta panelDerecha;
	
	private ImagenPOS panelArriba;	
	
	private panelCentralPOS panelCentral;
	
	// Importa la lógica del programa
	
	private Inventario modelo;
	
	private Cliente cliente;
	
	private VentanaPrincipalE ventanaE;
	
	// Paneles extra
	
	private JPanel panelCliente;
	
	
	// Botone venta incluye todo
	
	private JPanel panelVenta;
	
	private JButton btnAgregarProducto;
	
	private JButton btnTerminarVenta;
	
	// Iconos
	
	Icon coca;
	
	Icon PanFresco;
	
	Icon cerdo;
	
	Icon jabulani;
	
	Icon imagen;
	
	//Constantes para que los de venta. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String AGREGAR = "AGREGAR";
	private final static String TERMINAR= "TERMINARVENTA";
	
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
		
		try {
			salirAPP();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}
	
	public void Inicioventa()
	{
		
		int rta = JOptionPane.showConfirmDialog(this,"¿Se encuentra registrado(Si o No)","Bienvenido",JOptionPane.YES_NO_OPTION);
		
		if (rta == JOptionPane.YES_OPTION)
		{

			long registrado = Long.parseLong(JOptionPane.showInputDialog("Ingrese su numero de cedula"));
			Cliente numeroCliente = modelo.getCliente(registrado);
			
			if (numeroCliente != null) 
			{	
				venta(cliente);	
			}
			else 
			{
				JOptionPane.showMessageDialog(this,"El cliente no fue encontrado","Mensaje de error",JOptionPane.ERROR_MESSAGE);

			}
		}

		else if (rta == JOptionPane.NO_OPTION)
		{
			int registrarse = JOptionPane.showConfirmDialog(this,"¿Se encuentra registrado(Si o No)","Bienvenido",JOptionPane.YES_NO_OPTION);
			
			if (registrarse == JOptionPane.YES_OPTION)
			{
				registrarCliente();
			}
			
			else if (registrarse == JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(this,"Puede hacer la compra sin acumular puntos","Venta Anonima",JOptionPane.CANCEL_OPTION);
				
				//Continua el proceso de venta para agregar un producto de forma anonima
				venta(cliente);	

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
	
	private void venta(Cliente cliente) 
	{
		modelo.iniciarVenta();

		panelVenta= new JPanel();
		panelVenta.setLayout(new GridLayout(2,1));
		JDialog dialogo = new JDialog();
		dialogo.setSize(300,200);
		dialogo.setLocationRelativeTo(this);
		dialogo.add(panelVenta);
		dialogo.setVisible(true);
		
		btnAgregarProducto = new JButton("AGREGAR UN PRODUCTO");
		btnAgregarProducto.setBackground(new java.awt.Color(143,171,237));
		btnAgregarProducto.setForeground(Color.BLACK);
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setActionCommand(AGREGAR);
		panelVenta.add(btnAgregarProducto);
		
		
		btnTerminarVenta = new JButton("TERMINAR VENTA");
		btnTerminarVenta.setBackground(new java.awt.Color(143,171,237));
		btnTerminarVenta.setForeground(Color.BLACK);
		btnTerminarVenta.addActionListener(this);
		btnTerminarVenta.setActionCommand(TERMINAR);
		panelVenta.add(btnTerminarVenta);
				
	
	}
	
	// Listener para los botones de venta
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		long codigoDeBarras = Long.parseLong(JOptionPane.showInputDialog("Ingrese el codigo de barras del producto"));
		boolean existeProducto = modelo.verificarProducto(codigoDeBarras);
		Producto Producto = modelo.getProductoByCodigoDeBarras(codigoDeBarras);
		coca = new ImageIcon("./data/coca.jpg");
		PanFresco = new ImageIcon("./data/pan.jpg");
		cerdo = new ImageIcon("./data/cerdo.jpg");
		jabulani = new ImageIcon("./data/jabulani.png");
		
		if (comando.equals(AGREGAR)) 
		{
			if (existeProducto) 
			{
				if (Producto.getNombre().equals("Coca Cola"))
				{
					JOptionPane.showMessageDialog(this,"Producto: " + Producto.getNombre(),"PRODUCTO COCA COLA",JOptionPane.INFORMATION_MESSAGE,coca);
				}
				else if (Producto.getNombre().equals("Pan Fresco"))
				{
					JOptionPane.showMessageDialog(this,"Producto: " + Producto.getNombre(),"PRODUCTO PAN FRESCO",JOptionPane.INFORMATION_MESSAGE,PanFresco);
				}
				else if (Producto.getNombre().equals("Carne de cerdo"))
				{
					JOptionPane.showMessageDialog(this,"Producto: " + Producto.getNombre(),"PRODUCTO CARNE DE CERDO",JOptionPane.INFORMATION_MESSAGE,cerdo);
				}
				
				else if (Producto.getNombre().equals("Balones Jabulani"))
				{
					JOptionPane.showMessageDialog(this,"Producto: " + Producto.getNombre(),"PRODUCTO BALONES JABULANI",JOptionPane.INFORMATION_MESSAGE,jabulani);
				}
				
				else
				{
					JOptionPane.showMessageDialog(this,"Producto: " + Producto.getNombre(),"NO EXISTE",JOptionPane.ERROR_MESSAGE);
				}
				
				if (Producto.getPeso() == 1.0) 
				{
					JOptionPane.showMessageDialog(this,"Precio: " + Producto.getPrecio() + "/" + Producto.getUnidadDeMedida());
					JOptionPane.showInputDialog(this,"Ingrese cuantos " + Producto.getUnidadDeMedida() + " desea del producto (disponibles: " + Producto.getCantidad() + Producto.getUnidadDeMedida() + ")");
				}
				else 
				{
					JOptionPane.showMessageDialog(this,"Precio: " + Producto.getPrecio());
					JOptionPane.showInputDialog(this,"Ingrese cuantas unidades desea del producto (disponibles: " + (int)Producto.getCantidad() + ")");
				}
				int cantidad = Integer.parseInt(" ");
				if (cantidad > 0 && cantidad < Producto.getCantidad()) 
				{
					modelo.venderProducto(codigoDeBarras, cantidad);
				}
				else 
				{
					JOptionPane.showMessageDialog(this,"La cantidad ingresada no es válida","Mensaje de error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(this,"El producto con codigo de barras " + codigoDeBarras + " no fue encontrado","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			}
		}

		
		else if (comando.equals(TERMINAR))
		
		{
			modelo.terminarVenta(cliente);
			JOptionPane.showMessageDialog(this,"Gracias por venir, feliz dia","Hasta luego",JOptionPane.INFORMATION_MESSAGE);

		}
		else
		{
			JOptionPane.showMessageDialog(this,"\nPor favor seleccione una opción válida.\n");
		}
			
	}

	public void salirAPP() throws PersistenciaException
	{
		try 
		
		{
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
			{
				confirmarSalida();

			}
			});
		
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}

	}
	
	public void confirmarSalida() 
	{
		int valor = JOptionPane.showConfirmDialog(this,"Los datos han sido guardados,¿Quiere salir de la APP?","Mensaje de guardado",JOptionPane.YES_NO_CANCEL_OPTION);
		
		if ( valor == JOptionPane.YES_OPTION)
		{
			JOptionPane.showMessageDialog(this,"Gracias venir, pronto regreso al supermercado!","Mensaje de Salida",JOptionPane.INFORMATION_MESSAGE);
			try {
				modelo.salvarInventario();
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
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
