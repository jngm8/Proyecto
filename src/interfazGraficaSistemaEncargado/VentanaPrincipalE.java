package interfazGraficaSistemaEncargado;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import controlador.Inventario;
import controlador.PersistenciaException;
import intefrazGraficaSistemaPOS.ImagenPOS;
import intefrazGraficaSistemaPOS.VentanaPrincipalPOS;
import modelo.Producto;

import java.awt.BorderLayout;
import java.awt.Color;

public class VentanaPrincipalE extends JFrame implements ActionListener 
{
	// Declaro el atributo de cada panel
	
	private BotonesCargarInfo panelDerecha;
	
	private Imagen panelArriba;	
	
	private panelCentralE panelCentro;
	
	private JPanel panelInfo;
	
	private JPanel panelEliminar;
	
	private JPanel panelDesempeno;
	
	private JPanel panelInfoProducto;
	
	private JPanel panelGrafica;
	
	// Importa la lógica del programa
	
	private Inventario modelo;	
	
	private  Producto producto;
	// labels de consultar informacion inventario
	
	private JLabel lblDisponible;
	
	private JLabel lblTotalLotes;
	
	private JLabel lblGondolas;
	
	private JLabel lblClientesReg;
	// Declaro botones de eliminar un lote
	
	private JButton btnPorID;
	
	private JButton btnVencido;

	
	// labels info producto
	
	private JLabel lblNombre;
	
	private JLabel lblCodigoDebarras;
	
	private JLabel lblCategoria;
	
	private JLabel lblGanancia;
	
	private JLabel lblHistorico;
	
	// Declara los labels de cosultar desemepeño financiero
	
	private JLabel lblUtilidades;
	
	private JLabel lblNomProd;
	
	
	// Declara los labels de cosultar información del producto
	
	private JLabel lblNombreProducto;


	//Constantes para que los botones reaccione distinto. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String PORID = " ELIMINAR POR ID";
	private final static String VENCIDOS= "ELIMINAR VENCIDOS";
	
	private static final long serialVersionUID = 100L;
	
	// labels desepeño financiero
	
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
		
		try {
			salirAPP();
		} catch (PersistenciaException e) {
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
		panelInfo.setLayout(new GridLayout(4,1));
		JDialog dialog = new JDialog();
		dialog.setSize(300,300);
		dialog.setLocationRelativeTo(this);
		dialog.add(panelInfo);
		dialog.setVisible(true);
		
		lblDisponible = new JLabel("Numero de productos disponibles: " + modelo.sizeProductos());
		panelInfo.add(lblDisponible);
		lblTotalLotes = new JLabel("Total de lotes disponibles: " + modelo.sizeLotes());
		panelInfo.add(lblTotalLotes);
		lblGondolas = new JLabel("Numero de góndolas creadas: " + modelo.sizeCategorias());
		panelInfo.add(lblGondolas);
		lblClientesReg = new JLabel("Numero de clientes registrados: " + modelo.sizeClientes());
		panelInfo.add(lblClientesReg);
		
		Color colores = new Color(252, 205, 148);
		panelInfo.setBackground(colores);
				
	}
	
	public void EliminarLote()
	{
		panelEliminar = new JPanel();
		panelEliminar.setLayout(new GridLayout(2,1));
		JDialog dialogo = new JDialog();
		dialogo.setSize(300,200);
		dialogo.setLocationRelativeTo(this);
		dialogo.add(panelEliminar);
		dialogo.setVisible(true);
		
		btnPorID = new JButton("ELIMINAR LOTE POR ID");
		btnPorID.setBackground(new java.awt.Color(143,171,237));
		btnPorID.setForeground(Color.BLACK);
		btnPorID.addActionListener(this);
		btnPorID.setActionCommand(PORID);
		panelEliminar.add(btnPorID);
		
		
		btnVencido = new JButton("ELIMINAR LOTES VENCIDOS");
		btnVencido.setBackground(new java.awt.Color(143,171,237));
		btnVencido.setForeground(Color.BLACK);
		btnVencido.addActionListener(this);
		btnVencido.setActionCommand(VENCIDOS);
		panelEliminar.add(btnVencido);
		
	}
	
	// Listener para el boton de elminar lotes
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(PORID)) 
		{
			int idLote = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del lote que desea eliminar"));

			JOptionPane.showMessageDialog(this,modelo.eliminarLote(idLote));

		}
		else if (comando.equals(VENCIDOS))
		{
			String formatoFecha = JOptionPane.showInputDialog("Ingrese la fecha en formato dd-MM-yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date;
			try 
			{
				date = sdf.parse(formatoFecha);
			} 
			catch (ParseException e1) 
			{
				date = new Date();
			}
			Calendar fechaVencimiento = Calendar.getInstance();
			fechaVencimiento.setTime(date);
			JOptionPane.showMessageDialog(this,modelo.eliminarLotesVencidos(fechaVencimiento));
		}

	}
		
	

	
	public void ejecutarDesempenoProducto() 
	
	{
		String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto");
		Producto prod = modelo.getProductoByName(nombreProducto);
		if (prod != null) 
		{
			panelDesempeno= new JPanel();
			panelDesempeno.setLayout(new GridLayout(4,1));
			JDialog dialog = new JDialog();
			dialog.setSize(200,250);
			dialog.setLocationRelativeTo(this);
			dialog.add(panelDesempeno);
			dialog.setVisible(true);
			
			Color colores = new Color(155, 219,184);
			panelDesempeno.setBackground(colores);
			
			lblNomProd= new JLabel("Ganancia Neta: " + prod.gananciaNeta(prod.getCantidad()));
			panelDesempeno.add(lblNomProd);
			
			lblUtilidades= new JLabel("Utilidades: " + prod.gananciaNeta(prod.getCantidad()));
			panelDesempeno.add(lblUtilidades);	
			
		}
		else 
		{
			JOptionPane.showMessageDialog(this,"No se encontro: "+ nombreProducto + ". Digite de nuevo","Mensaje de error",JOptionPane.ERROR_MESSAGE);

		}
	}
	
	public void ejecutarInformacionProducto() 
	{
		String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto");
		Producto produ = modelo.getProductoByName(nombreProducto);
		
		if (produ != null) 
		{
			panelInfoProducto= new JPanel();
			panelInfoProducto.setLayout(new GridLayout(4,1));
			JDialog dialog = new JDialog();
			dialog.setSize(450,400);
			dialog.setLocationRelativeTo(this);
			dialog.add(panelInfoProducto);
			dialog.setVisible(true);
			
			Color colores = new Color(168, 140, 234);
			panelInfoProducto.setBackground(colores);
			
			lblNombre = new JLabel("Nombre del producto: " + produ.getNombre());
			panelInfoProducto.add(lblNombre);
			lblCodigoDebarras = new JLabel("Codigo de barras: " + produ.getCodigoDeBarras());
			panelInfoProducto.add(lblCodigoDebarras);
			lblCategoria= new JLabel("La categoria es: " + produ.getCategoria());
			panelInfoProducto.add(lblCategoria);
			lblGanancia= new JLabel("La ganancia neta es: " + produ.gananciaNeta(produ.getCantidad()));
			panelInfoProducto.add(lblGanancia);
			lblHistorico= new JLabel("Historico de ventas es: " + produ.historico(produ.getCantidad()));
			panelInfoProducto.add(lblHistorico);

			
		}
		else 
		{
			JOptionPane.showMessageDialog(this,"El producto " + nombreProducto + " no fue encontrado","Mensaje de error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String modificar()
	{
		String nombreProduc = JOptionPane.showInputDialog("Ingrese el nombre del producto ");
			
		return nombreProduc;
	}
	
	public void grafica()
	
	{
		double[] xData = new double[] { 0.0, 1.0, 2.0 };
		double[] yData = new double[] { 2.0, 1.0, 0.0 };

		// Create Chart
		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);

		// Show it
		new SwingWrapper(chart).displayChart();
		
		repaint();
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
			JOptionPane.showMessageDialog(this,"Gracias por manejar el inventario, Feliz Navidad!","Mensaje de error",JOptionPane.INFORMATION_MESSAGE);
			try 
			{
				modelo.salvarInventario();
			} catch (PersistenciaException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
		
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