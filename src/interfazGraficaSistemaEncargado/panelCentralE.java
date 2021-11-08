package interfazGraficaSistemaEncargado;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class panelCentralE extends JPanel

{
	
	private JLabel lblMercado;

	
	public panelCentralE()
	{
		setLayout(new BorderLayout());
		
		lblMercado = new JLabel();
		ImageIcon imagen = new ImageIcon("./data/superEncargado.jpg");
		lblMercado.setIcon(imagen);
		add(lblMercado);
			
	}
}
