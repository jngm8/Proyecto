package interfazGraficaSistemaEncargado;

import javax.swing.JButton;
import javax.swing.JPanel;
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
		setLayout(new GridLayout(4,1));
		panelBotonesE = new JPanel();
		panelBotonesE.setLayout(new GridLayout(4,1,0,20));// filas,columnas,espacio columnas,espacio filas
		
		//Creaci�n de los botones
		
		btnCargarLote = new JButton("CARGAR UN LOTE");
		panelBotonesE.add(btnCargarLote);

		btnConsultarInformaci�nInventario = new JButton("CONSULTAR INFORMACI�N DEL INVENTARIO");
		panelBotonesE.add(btnConsultarInformaci�nInventario);
		
		btnEliminarLote = new JButton("ELIMINAR UN LOTE");
		panelBotonesE.add(btnEliminarLote);

		btnDesempe�oProducto = new JButton("CONSULTAR DESEMPE�O DEL PRODUCTO");
		panelBotonesE.add(btnDesempe�oProducto);
		
		//Se agrega el panel
		add(panelBotonesE);
		
	}
	
}


