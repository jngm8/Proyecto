package intefrazGraficaSistemaPOS;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class panelCentralPOS extends JPanel

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private JLabel lblTitulo;

	
	
	private JLabel lblMercado;

	
	public panelCentralPOS()
	{
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("                      BIENVENIDOS AL SUPERMERCADO "));		
		lblMercado = new JLabel();
		ImageIcon imagen = new ImageIcon("./data/mercado.jpg");
		lblMercado.setIcon(imagen);
		add(lblMercado);
	}
}
