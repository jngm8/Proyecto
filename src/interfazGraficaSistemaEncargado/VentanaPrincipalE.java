package interfazGraficaSistemaEncargado;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

import controlador.Inventario;
import controlador.PersistenciaException;
import intefrazGraficaSistemaPOS.ImagenPOS;

import java.awt.BorderLayout;
import java.awt.Color;

public class VentanaPrincipalE extends JFrame
{
	// Declaro el atributo de cada panel
	
	private BotonesCargarInfo panelDerecha;
	
	private Imagen panelArriba;	
	
	private panelCentralE panelCentro;
	
	private JPanel panelInfo;
	
	private JPanel panelEliminar;
	
	// Importa la lógica del programa
	
	private Inventario modelo;	
	
	// Declaro botones de eliminar un lote
	
	private JButton btnPorID;
	
	private JButton btnVencido;

	
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
		panelDerecha = new BotonesCargarInfo(this);
		// Agrego el panel(que agrego,donde lo agrego)
		add(panelDerecha,BorderLayout.EAST);
		
		panelArriba = new Imagen();
		add(panelArriba,BorderLayout.NORTH);
		
		panelCentro = new panelCentralE();
		add(panelCentro,BorderLayout.WEST);
		
		try 
		{
			modelo = new Inventario();
		} 
		catch (PersistenciaException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void cargarArchivo()
	{
		
		String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo .csv de los lotes ubicado en la carpeta data");
		try
		{
			File archivo = new File ("./data/" + nombreArchivo + ".csv");
			modelo.cargarLote(archivo);
		}
		catch(Exception e)
		{
			//Referencia el medio del panel,mensaje,titulo,logo del error
			JOptionPane.showMessageDialog(this,"No se encontro: "+ nombreArchivo + ". Digite de nuevo","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void InformacionInventario() 
	{

		panelInfo= new JPanel();
		add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new GridLayout(5,1));
		panelInfo.setBorder(new TitledBorder("                       Informacion del cliente "));
	
		panelInfo.add(new JLabel("Numero de productos disponibles: " + modelo.sizeProductos()));
		panelInfo.add(new JLabel("Total de lotes disponibles: " + modelo.sizeLotes()));
		panelInfo.add(new JLabel("Numero de góndolas creadas: " + modelo.sizeCategorias()));
		panelInfo.add(new JLabel("Numero de clientes registrados: " + modelo.sizeClientes()));
		
		panelInfo.setVisible(true);
		
		panelDerecha.add(panelInfo);
		
	}
	
	public void EliminarLote()
	{
		panelEliminar = new JPanel();

		JDialog dialogo = new JDialog();
		dialogo.setSize(300,300);
		dialogo.setLocationRelativeTo(this);
		dialogo.add(panelEliminar);
		dialogo.setVisible(true);
		
		btnPorID = new JButton("ELIMINAR LOTE POR ID");
		btnPorID.setBackground(new java.awt.Color(143,171,237));
		btnPorID.setForeground(Color.BLACK);
		panelEliminar.add(btnPorID);
		
		
		btnVencido = new JButton("ELIMINAR LOTES VENCIDOS");
		btnVencido.setBackground(new java.awt.Color(143,171,237));
		btnVencido.setForeground(Color.BLACK);
		panelEliminar.add(btnVencido);
		
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