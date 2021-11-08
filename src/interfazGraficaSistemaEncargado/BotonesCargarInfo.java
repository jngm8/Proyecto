package interfazGraficaSistemaEncargado;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;


public class BotonesCargarInfo  extends JPanel
{
	// Inicia los botones que va tener este panel
	private JButton btnCargarLote;
	private JButton btnConsultarInformaciónInventario;
	private JButton btnEliminarLote;
	private JButton btnDesempeñoProducto;
	private JPanel panelBotonesE;

	
	public BotonesCargarInfo()
	{
		// Define que forma deben tener los botones y su distribución
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                          ¡Seleccione una opción!"));
		panelBotonesE = new JPanel();
		panelBotonesE.setLayout(new GridLayout(4,1,0,20));// filas,columnas,espacio columnas,espacio filas
		add(new JLabel());
		
		//Creación de los botones
		
		btnCargarLote = new JButton("CARGAR UN LOTE");
		btnCargarLote.setBackground(new java.awt.Color(31,140,214));
		btnCargarLote.setForeground(Color.BLACK);
		panelBotonesE.add(btnCargarLote);

		btnConsultarInformaciónInventario = new JButton("CONSULTAR INFORMACIÓN DEL INVENTARIO");
		btnConsultarInformaciónInventario.setBackground(new java.awt.Color(31,140,214));
		btnConsultarInformaciónInventario.setForeground(Color.BLACK);
		panelBotonesE.add(btnConsultarInformaciónInventario);
		
		btnEliminarLote = new JButton("ELIMINAR UN LOTE");
		btnEliminarLote.setBackground(new java.awt.Color(31,140,214));
		btnEliminarLote.setForeground(Color.BLACK);
		panelBotonesE.add(btnEliminarLote);

		btnDesempeñoProducto = new JButton("CONSULTAR DESEMPEÑO DEL PRODUCTO");
		btnDesempeñoProducto.setBackground(new java.awt.Color(31,140,214));
		btnDesempeñoProducto.setForeground(Color.BLACK);
		panelBotonesE.add(btnDesempeñoProducto);
		
		//Se agrega el panel
		add(panelBotonesE);
		
	}
	
}


