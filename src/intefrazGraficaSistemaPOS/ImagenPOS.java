package intefrazGraficaSistemaPOS;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagenPOS extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblImagen;
	
	public ImagenPOS()
	{
		lblImagen = new JLabel();
		ImageIcon imagen = new ImageIcon("./data/cajero.jpg");
		lblImagen.setIcon(imagen);
		add(lblImagen);
		
	}
}
