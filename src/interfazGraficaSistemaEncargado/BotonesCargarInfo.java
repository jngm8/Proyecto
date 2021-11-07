package interfazGraficaSistemaEncargado;

import javax.swing.JButton;
import javax.swing.JPanel;
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
		setLayout(new GridLayout(4,1));
		panelBotonesE = new JPanel();
		panelBotonesE.setLayout(new GridLayout(4,1,0,20));// filas,columnas,espacio columnas,espacio filas
		
		//Creación de los botones
		
		btnCargarLote = new JButton("CARGAR UN LOTE");
		panelBotonesE.add(btnCargarLote);

		btnConsultarInformaciónInventario = new JButton("CONSULTAR INFORMACIÓN DEL INVENTARIO");
		panelBotonesE.add(btnConsultarInformaciónInventario);
		
		btnEliminarLote = new JButton("ELIMINAR UN LOTE");
		panelBotonesE.add(btnEliminarLote);

		btnDesempeñoProducto = new JButton("CONSULTAR DESEMPEÑO DEL PRODUCTO");
		panelBotonesE.add(btnDesempeñoProducto);
		
		//Se agrega el panel
		add(panelBotonesE);
		
	}
	
}


