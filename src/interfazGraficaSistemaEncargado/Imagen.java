package interfazGraficaSistemaEncargado;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Imagen extends JPanel
{
	private JLabel lblImagen;
	
	public Imagen()
	{
		lblImagen = new JLabel();
		ImageIcon imagen = new ImageIcon("./data/INVENTARIO.jpg");
		lblImagen.setIcon(imagen);
		add(lblImagen);
		
	}
}