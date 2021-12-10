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
import javax.swing.JTextField;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import controlador.Inventario;
import controlador.PersistenciaException;
import interfazGraficaSistemaEncargado.VentanaPrincipalE;
import modelo.Cliente;
import modelo.Producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
	
	// Importa la l�gica del programa
	
	private Inventario modelo;
	
	private Cliente cliente;
	
	private Producto producto;
	
	private VentanaPrincipalE ventanaE;
	
	// Paneles extra
	
	private JPanel panelCliente;
	
	private JPanel panelTerminarVenta;
	
	private JPanel panelGrafica;
	
	
	// Labels terminar venta
	
	private JLabel lblValorCompra;
	
	private JLabel lblPuntosAcumulados;
	
	private JLabel lblClientes;
	// Botone venta incluye todo
	
	private JPanel panelVenta;
	
	private JButton btnAgregarProducto;
	
	private JButton btnTerminarVenta;
	
	private JButton btnDescuentos;
	
	private JButton btnRegalos;
	
	private JButton btnCombos;
	
	private JButton btnMultiplicados;
	
	// Bot�n de puntos
	
	private JButton btnRedimirPuntos;
	
	private JButton btnTerminar;
	
	// Graficos con JFreeChart
	
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	// Iconos
	
	Icon coca;
	
	Icon PanFresco;
	
	Icon cerdo;
	
	Icon jabulani;
	
	Icon imagen;
	
	//Constantes para que los de venta. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String AGREGAR = "AGREGAR";
	private final static String DESCUENTOS = "DESCUENTOS";
	private final static String REGALOS = "REGALOS";
	private final static String COMBOS = "COMBOS";
	private final static String MULTIPLICADOS = "MULTIPLICADOS";
	private final static String SIGUIENTE = "SIGUIENTE";
	private final static String TERMINARV = "TERMINARV";
	private final static String TERMINAR= "TERMINARVENTA";
	
	
	public VentanaPrincipalPOS()
	
	{
		// Tama�o del JFrame principal
		setSize(700,700);
		// Titulo del JFrame principal
		setTitle("CAJERO");
		// Termina la ejecuci�n cuando se cierre el JFrame
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
		
		int rta = JOptionPane.showConfirmDialog(this,"�Se encuentra registrado(Si o No)","Bienvenido",JOptionPane.YES_NO_OPTION);
		
		if (rta == JOptionPane.YES_OPTION)
		{

			long registrado = Long.parseLong(JOptionPane.showInputDialog("Ingrese su numero de cedula"));
			
			cliente = modelo.getCliente(registrado);
			
			if (cliente != null) 
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
			int registrarse = JOptionPane.showConfirmDialog(this,"�Se encuentra registrado(Si o No)","Bienvenido",JOptionPane.YES_NO_OPTION);
			
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
			JOptionPane.showMessageDialog(this,"Seleccione una opci�n v�lida","Mensaje de error",JOptionPane.ERROR_MESSAGE);

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
			JOptionPane.showMessageDialog(this,"El n�mero de cedula ya esta agregado","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public void ConsultarInfoPuntos() throws IOException
	
	{
		Cliente Cliente;
		
		long cedula = Long.parseLong(JOptionPane.showInputDialog("Por favor ingrese el numero de cedula "));
		Cliente = modelo.getCliente(cedula);
		if (Cliente != null) 
		{
			panelDerecha.refrescar(Cliente,modelo);
		}
		

		else 
		{
			JOptionPane.showMessageDialog(this,"\nEl cliente con n�mero de cedula " + cedula + " no fue encontrado\n","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void venta(Cliente cliente) 
	{
		modelo.iniciarVenta();

		panelVenta= new JPanel();
		panelVenta.setLayout(new GridLayout(2,3));
		JDialog dialogo = new JDialog();
		dialogo.setSize(700,600);
		dialogo.setLocationRelativeTo(this);
		dialogo.add(panelVenta);
		dialogo.setVisible(true);
		
		btnAgregarProducto = new JButton();
		btnAgregarProducto.setBounds(0,0,275,275);
		ImageIcon agregar = new ImageIcon("./data/chulo.png");
		btnAgregarProducto.setIcon(new ImageIcon(agregar.getImage().getScaledInstance(btnAgregarProducto.getWidth(),btnAgregarProducto.getHeight(), Image.SCALE_DEFAULT)));
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setActionCommand(AGREGAR);
		btnAgregarProducto.setFont(new Font("cooper black",3,20));
		panelVenta.add(btnAgregarProducto);
		
		btnDescuentos = new JButton();
		btnDescuentos.setBounds(0,0,300,300);
		ImageIcon descuento = new ImageIcon("./data/descuentos.png");
		btnDescuentos.setIcon(new ImageIcon(descuento.getImage().getScaledInstance(btnDescuentos.getWidth(),btnDescuentos.getHeight(), Image.SCALE_DEFAULT)));
		btnDescuentos.addActionListener(this);
		btnDescuentos.setActionCommand(DESCUENTOS);
		panelVenta.add(btnDescuentos);
		
		btnRegalos= new JButton();
		btnRegalos.setBounds(0,0,300,300);
		ImageIcon regalo = new ImageIcon("./data/regalos.png");
		btnRegalos.setIcon(new ImageIcon(regalo.getImage().getScaledInstance(btnRegalos.getWidth(),btnRegalos.getHeight(), Image.SCALE_DEFAULT)));
		btnRegalos.addActionListener(this);
		btnRegalos.setActionCommand(REGALOS);
		panelVenta.add(btnRegalos);
		
		btnCombos = new JButton();
		btnCombos.setBounds(0,0,300,300);
		ImageIcon combo = new ImageIcon("./data/combo.jpg");
		btnCombos.setIcon(new ImageIcon(combo.getImage().getScaledInstance(btnCombos.getWidth(),btnCombos.getHeight(), Image.SCALE_DEFAULT)));
		btnCombos.addActionListener(this);
		btnCombos.setActionCommand(COMBOS);
		panelVenta.add(btnCombos);
		
		btnMultiplicados= new JButton("PUNTOS MULTIPLICADOS");
		btnMultiplicados.setBounds(0,0,300,300);
		ImageIcon puntos = new ImageIcon("./data/puntos.png");
		btnMultiplicados.setIcon(new ImageIcon(puntos.getImage().getScaledInstance(btnMultiplicados.getWidth(),btnMultiplicados.getHeight(), Image.SCALE_DEFAULT)));
		btnMultiplicados.addActionListener(this);
		btnMultiplicados.setActionCommand(MULTIPLICADOS);
		panelVenta.add(btnMultiplicados);
		
		btnTerminarVenta = new JButton("TERMINAR VENTA");
		btnTerminarVenta.setBackground(new java.awt.Color(143,171,237));
		btnTerminarVenta.setForeground(Color.BLACK);
		btnTerminarVenta.addActionListener(this);
		btnTerminarVenta.setActionCommand(TERMINAR);
		btnTerminarVenta.setFont(new Font("cooper black",3,15));
		panelVenta.add(btnTerminarVenta);
				
	
	}
	
	// Listener para los botones de venta
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		coca = new ImageIcon("./data/coca.jpg");
		PanFresco = new ImageIcon("./data/pan.jpg");
		cerdo = new ImageIcon("./data/cerdo.jpg");
		jabulani = new ImageIcon("./data/jabulani.png");
		
		if (comando.equals(AGREGAR)) 
		{
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");
			long codigoDeBarras = modelo.getCodigoProducto(nombre);
			boolean existeProducto = modelo.verificarNombreProducto(nombre);
			if (existeProducto) 
			{
				Producto Producto = modelo.getProductoByName(nombre);
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
				}
				else 
				{
					JOptionPane.showMessageDialog(this,"Precio: " + Producto.getPrecio());
				}
				
				int cantidad = 	Integer.parseInt(JOptionPane.showInputDialog(this,"Ingrese cuantas unidades desea del producto (disponibles: " + (int)Producto.getCantidad() + ")"));

				if (cantidad > 0 && cantidad < Producto.getCantidad()) 
				{
					modelo.venderProducto(codigoDeBarras, cantidad);//se hace con el codigo de barras
				}
				else 
				{
					JOptionPane.showMessageDialog(this,"La cantidad ingresada no es v�lida","Mensaje de error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(this,"El producto con codigo de barras " + codigoDeBarras + " no fue encontrado","Mensaje de error",JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (comando.equals(DESCUENTOS))
			
		{
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto");

			Producto Producto = modelo.getProductoByName(nombre);

			JOptionPane.showMessageDialog(this,"Esta es la promoci�n para el producto: "+Producto.getNombre()+Producto.descuentos(),"�DESCUENTOS!",JOptionPane.INFORMATION_MESSAGE);
			
			JOptionPane.showMessageDialog(this,"Este descuento es valido desde"+"9/12/21"+"hasta"+"13/12/21","�DESCUENTOS!",JOptionPane.INFORMATION_MESSAGE);

		}
		
		
		else if (comando.equals(REGALOS))
			
		{
			JOptionPane.showMessageDialog(this,"COMO","�REGALOS!",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		else if (comando.equals(COMBOS))
			
		{
			JOptionPane.showMessageDialog(this," ESTAS","�COMBOS!",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		else if (comando.equals(MULTIPLICADOS))
			
		{
			JOptionPane.showMessageDialog(this,"TE","�PUNTOS MULTIPLICADOS!",JOptionPane.INFORMATION_MESSAGE);
		}
		
		else if (comando.equals(TERMINAR))
			
		{
			panelTerminarVenta= new JPanel();
			panelTerminarVenta.setLayout(new GridLayout(4,1));
			JDialog dialogVenta = new JDialog();
			dialogVenta.setSize(300,250);
			dialogVenta.setLocationRelativeTo(this);
			dialogVenta.add(panelTerminarVenta);
			dialogVenta.setVisible(true);
			
			Color colores = new Color(174, 255, 115);
			panelTerminarVenta.setBackground(colores);
			
			
			
			if (cliente != null)
			{
				lblValorCompra= new JLabel("Valor total de la compra: " + modelo.getTotalFactura());
				panelTerminarVenta.add(lblValorCompra);
				lblClientes = new JLabel("Los puntos acumulados por el cliente son: " + modelo.getPuntosCliente(cliente));
				panelTerminarVenta.add(lblClientes);
								
				if (modelo.getPuntosCliente(cliente) > 0)
				{
					
					btnRedimirPuntos = new JButton("Siguiente");
					btnRedimirPuntos.setBackground(new java.awt.Color(143,171,237));
					btnRedimirPuntos.setForeground(Color.BLACK);
					btnRedimirPuntos.addActionListener(this);
					btnRedimirPuntos.setActionCommand(SIGUIENTE);
					btnRedimirPuntos.setFont(new Font("cooper black",3,20));
					panelTerminarVenta.add(btnRedimirPuntos,BorderLayout.SOUTH);
				}
				
				else
				{
					btnTerminar = new JButton("TERMINAR VENTA");
					btnTerminar.setBackground(new java.awt.Color(143,171,237));
					btnTerminar.setForeground(Color.BLACK);
					btnTerminar.addActionListener(this);
					btnTerminar.setActionCommand(TERMINARV);
					btnTerminar.setFont(new Font("cooper black",3,20));
					panelTerminarVenta.add(btnTerminar,BorderLayout.SOUTH);
					modelo.terminarVenta(cliente,0);

				}
				
			}
			
			else
				
			{

				lblValorCompra= new JLabel("Valor total de la compra: " + modelo.getTotalFactura());
				panelTerminarVenta.add(lblValorCompra);
				
				btnTerminar = new JButton("TERMINAR VENTA");
				btnTerminar.setBackground(new java.awt.Color(143,171,237));
				btnTerminar.setForeground(Color.BLACK);
				btnTerminar.addActionListener(this);
				btnTerminar.setActionCommand(TERMINARV);
				btnTerminar.setFont(new Font("cooper black",3,20));
				panelTerminarVenta.add(btnTerminar,BorderLayout.SOUTH);
			}

		}
		
		if (comando.equals(SIGUIENTE))
		{
			int rta = JOptionPane.showConfirmDialog(this,"QUIERE REDIMIR LOS PUNTOS ACUMULADOS","REDIMIR PUNTOS",JOptionPane.YES_NO_CANCEL_OPTION);
			
			if ( rta == JOptionPane.YES_OPTION)
			{
				int valor = Integer.parseInt(JOptionPane.showInputDialog(this,"Cuanto puntos quiere redimir"));
				
				cliente.restarPuntos(valor);
				
				modelo.terminarVenta(cliente,valor);
				
				this.setVisible(true);

			}
			
		}
			
		else if (comando.equals(TERMINARV))
		{
			this.setVisible(true);
			modelo.terminarVenta(cliente,0);
						
		}
		
		

	}
	
	//Salida aplicacion grande
	public void salirAPP() throws PersistenciaException
	{
		try 
		
		{
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
			{
				confirmarSalidaPrincipal();

			}
			});
		
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}

	}
	
	public void confirmarSalidaPrincipal() 
	{
		int valor = JOptionPane.showConfirmDialog(this,"Los datos han sido guardados,�Quiere salir de la APP?","Mensaje de guardado",JOptionPane.YES_NO_CANCEL_OPTION);
		
		if ( valor == JOptionPane.YES_OPTION)
		{
			JOptionPane.showMessageDialog(this,"Gracias por venir, pronto regreso al supermercado y Feliz Navidad!","�FELIZ NAVIDAD!",JOptionPane.INFORMATION_MESSAGE);
			try {
				modelo.salvarInventario();
			} catch (PersistenciaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		
	}
	
	
	// Main para iniciar la aplicaci�n
	public static void main (String[] args) throws IOException
	{
		VentanaPrincipalPOS ventana = new VentanaPrincipalPOS();
		// Ubica la ventana en el centro de la pantalla
		ventana.setLocationRelativeTo(null);
		// Hace que se vea la ventana
		ventana.setVisible(true);

	}


}
