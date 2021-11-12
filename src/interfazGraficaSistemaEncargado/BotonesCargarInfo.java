package interfazGraficaSistemaEncargado;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;


public class BotonesCargarInfo  extends JPanel implements ActionListener
{
	// Inicia los botones que va tener este panel
	private JButton btnCargarLote;
	private JButton btnConsultarInformaciónInventario;
	private JButton btnEliminarLote;
	private JButton btnDesempeñoProducto;
	private JPanel panelBotonesE;
	
	//Constantes para que los botones reaccione distinto. Final(Siempre va tener ese valor) Static(Pertenece a la clase no al objeto)
	
	private final static String CARGAR = "CARGAR LOTE";
	private final static String CONSULTAR = "CONSULTAR INVENTARIO";
	private final static String ELIMINAR = "ELIMINAR LOTE";
	private final static String DESEMPENO = "DESEMPEÑO PRODUCTOS";


	/// Atributo ventana pricipal
	
	private VentanaPrincipalE principal;
	
	public BotonesCargarInfo(VentanaPrincipalE principalp)
	
	{
		
		principal = principalp;
		
		
		// Define que forma deben tener los botones y su distribución
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                          ¡Seleccione una opción!"));
		panelBotonesE = new JPanel();
		panelBotonesE.setLayout(new GridLayout(4,1,0,20));// filas,columnas,espacio columnas,espacio filas
		add(new JLabel());
		
		//Creación de los botones
		
		btnCargarLote = new JButton("CARGAR UN LOTE");
		btnCargarLote.setBackground(new java.awt.Color(143,171,237));
		btnCargarLote.setForeground(Color.BLACK);
		btnCargarLote.addActionListener(this);
		btnCargarLote.setActionCommand(CARGAR);
		panelBotonesE.add(btnCargarLote);

		btnConsultarInformaciónInventario = new JButton("CONSULTAR INFORMACIÓN DEL INVENTARIO");
		btnConsultarInformaciónInventario.setBackground(new java.awt.Color(143,171,237));
		btnConsultarInformaciónInventario.setForeground(Color.BLACK);
		btnConsultarInformaciónInventario.addActionListener(this);
		btnConsultarInformaciónInventario.setActionCommand(CONSULTAR);
		panelBotonesE.add(btnConsultarInformaciónInventario);
		
		btnEliminarLote = new JButton("ELIMINAR UN LOTE");
		btnEliminarLote.setBackground(new java.awt.Color(143,171,237));
		btnEliminarLote.setForeground(Color.BLACK);
		btnEliminarLote.addActionListener(this);
		btnEliminarLote.setActionCommand(ELIMINAR);
		panelBotonesE.add(btnEliminarLote);

		btnDesempeñoProducto = new JButton("CONSULTAR DESEMPEÑO DEL PRODUCTO");
		btnDesempeñoProducto.setBackground(new java.awt.Color(143,171,237));
		btnDesempeñoProducto.setForeground(Color.BLACK);
		btnDesempeñoProducto.addActionListener(this);
		btnDesempeñoProducto.setActionCommand(DESEMPENO);
		panelBotonesE.add(btnDesempeñoProducto);
		
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
			JOptionPane.showMessageDialog(this, "CONSULTAR INFORMACIÓN DEL INVENTARIO");
		}
		
		else if (comando.equals(ELIMINAR))
		{
			JOptionPane.showMessageDialog(this, "ELIMINAR UN LOTE");
		}
		else if (comando.equals(DESEMPENO))
		{
			JOptionPane.showMessageDialog(this, "DESEMEPEÑO FINANCIERO DE UN PRODUCTO");
		}
		
	}
	
}


