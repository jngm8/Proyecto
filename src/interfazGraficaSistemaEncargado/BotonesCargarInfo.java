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
	private JButton btnConsultarInformaci�nInventario;
	private JButton btnEliminarLote;
	private JButton btnDesempe�oProducto;
	private JPanel panelBotonesE;

	
	public BotonesCargarInfo()
	{
		// Define que forma deben tener los botones y su distribuci�n
		setLayout(new GridLayout(3,1));
		setBorder(new TitledBorder("                          �Seleccione una opci�n!"));
		panelBotonesE = new JPanel();
		panelBotonesE.setLayout(new GridLayout(4,1,0,20));// filas,columnas,espacio columnas,espacio filas
		add(new JLabel());
		
		//Creaci�n de los botones
		
		btnCargarLote = new JButton("CARGAR UN LOTE");
		btnCargarLote.setBackground(new java.awt.Color(31,140,214));
		btnCargarLote.setForeground(Color.BLACK);
		panelBotonesE.add(btnCargarLote);

		btnConsultarInformaci�nInventario = new JButton("CONSULTAR INFORMACI�N DEL INVENTARIO");
		btnConsultarInformaci�nInventario.setBackground(new java.awt.Color(31,140,214));
		btnConsultarInformaci�nInventario.setForeground(Color.BLACK);
		panelBotonesE.add(btnConsultarInformaci�nInventario);
		
		btnEliminarLote = new JButton("ELIMINAR UN LOTE");
		btnEliminarLote.setBackground(new java.awt.Color(31,140,214));
		btnEliminarLote.setForeground(Color.BLACK);
		panelBotonesE.add(btnEliminarLote);

		btnDesempe�oProducto = new JButton("CONSULTAR DESEMPE�O DEL PRODUCTO");
		btnDesempe�oProducto.setBackground(new java.awt.Color(31,140,214));
		btnDesempe�oProducto.setForeground(Color.BLACK);
		panelBotonesE.add(btnDesempe�oProducto);
		
		//Se agrega el panel
		add(panelBotonesE);
		
	}
	
}


