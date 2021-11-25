package interfazGraficaSistemaEncargado;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controlador.Inventario;
import controlador.PersistenciaException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;


public class BotonesCargarInfo  extends JPanel implements ActionListener
{
	// Inicia los botones que va tener este panel
	private JButton btnCargarLote;
	private JButton btnConsultarInformaci�nInventario;
	private JButton btnEliminarLote;
	private JButton btnDesempe�oProducto;
	private JButton btnInfoProducto;
	private JButton btnModificar;
	private JButton btnSalir;
	private JPanel panelBotonesE;
	
	private Inventario modelo;	

	
	//Constantes para que los botones reaccione distinto. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String CARGAR = "CARGAR LOTE";
	private final static String CONSULTAR = "CONSULTAR INVENTARIO";
	private final static String ELIMINAR = "ELIMINAR LOTE";
	private final static String DESEMPENO = "DESEMPE�O PRODUCTOS";
	private final static String INFOPROD = "INFORMACION DE UN PRODUCTO";
	private final static String MODIFICAR = "MODIFICAR IMAGENES";

	/// Atributo ventana pricipal
	
	private VentanaPrincipalE principal;
	
	public BotonesCargarInfo(VentanaPrincipalE principalp)
	
	{
		
		principal = principalp;
		
		
		// Define que forma deben tener los botones y su distribuci�n
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                          �Seleccione una opci�n!"));
		panelBotonesE = new JPanel();
		panelBotonesE.setLayout(new GridLayout(7,1,0,7));// filas,columnas,espacio columnas,espacio filas
		add(new JLabel());
		
		//Creaci�n de los botones
		
		btnCargarLote = new JButton("CARGAR UN LOTE");
		btnCargarLote.setBackground(new java.awt.Color(143,171,237));
		btnCargarLote.setForeground(Color.BLACK);
		btnCargarLote.addActionListener(this);
		btnCargarLote.setActionCommand(CARGAR);
		panelBotonesE.add(btnCargarLote);

		btnConsultarInformaci�nInventario = new JButton("CONSULTAR INFORMACI�N DEL INVENTARIO");
		btnConsultarInformaci�nInventario.setBackground(new java.awt.Color(143,171,237));
		btnConsultarInformaci�nInventario.setForeground(Color.BLACK);
		btnConsultarInformaci�nInventario.addActionListener(this);
		btnConsultarInformaci�nInventario.setActionCommand(CONSULTAR);
		panelBotonesE.add(btnConsultarInformaci�nInventario);
		
		btnEliminarLote = new JButton("ELIMINAR UN LOTE");
		btnEliminarLote.setBackground(new java.awt.Color(143,171,237));
		btnEliminarLote.setForeground(Color.BLACK);
		btnEliminarLote.addActionListener(this);
		btnEliminarLote.setActionCommand(ELIMINAR);
		panelBotonesE.add(btnEliminarLote);

		btnDesempe�oProducto = new JButton("CONSULTAR DESEMPE�O DEL PRODUCTO");
		btnDesempe�oProducto.setBackground(new java.awt.Color(143,171,237));
		btnDesempe�oProducto.setForeground(Color.BLACK);
		btnDesempe�oProducto.addActionListener(this);
		btnDesempe�oProducto.setActionCommand(DESEMPENO);
		panelBotonesE.add(btnDesempe�oProducto);
		
		btnInfoProducto = new JButton("CONSULTAR INFORMACI�N DE UN PRODUCTO");
		btnInfoProducto.setBackground(new java.awt.Color(143,171,237));
		btnInfoProducto.setForeground(Color.BLACK);
		btnInfoProducto.addActionListener(this);
		btnInfoProducto.setActionCommand(INFOPROD);
		panelBotonesE.add(btnInfoProducto);
		
		btnModificar = new JButton("MODIFICAR IMAGEN PRODUCTO");
		btnModificar.setBackground(new java.awt.Color(143,171,237));
		btnModificar.setForeground(Color.BLACK);
		btnModificar.addActionListener(this);
		btnModificar.setActionCommand(MODIFICAR);
		panelBotonesE.add(btnModificar);
		

		Color colores = new Color(252, 248, 171);
		panelBotonesE.setBackground(colores);
		//Se agrega el panel
		add(panelBotonesE);
		
		//Fondo del panel
		Color color = new Color(252, 248, 171);
		setBackground(color);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(CARGAR)) 
		{
			
			principal.cargarArchivo();
			
		}
		else if (comando.equals(CONSULTAR))
		{
			principal.InformacionInventario();

		}
		
		else if (comando.equals(ELIMINAR))
		{
			principal.EliminarLote();

		}
		else if (comando.equals(DESEMPENO))
		{
			principal.ejecutarDesempenoProducto();

		}
		
		else if (comando.equals(INFOPROD))
		{
			principal.ejecutarInformacionProducto();
		}
		
		else if (comando.equals(MODIFICAR))
		{
			principal.modificar();
		}
		

		
	}
	

}


