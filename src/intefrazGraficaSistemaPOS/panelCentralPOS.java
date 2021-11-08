package intefrazGraficaSistemaPOS;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class panelCentralPOS extends JPanel

{
	
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
