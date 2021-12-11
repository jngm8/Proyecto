package interfazGraficaSistemaEncargado;

import java.awt.BorderLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class VentanaGraficas extends JFrame

{
	public VentanaGraficas()
	{
		
		setSize(700,700);
		// Titulo del JFrame principal
		setTitle("INVENTARIO");
		// Termina la ejecución cuando se cierre el JFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// La forma que va tomar el panel de BotonesCargarInfo
		setLayout(new BorderLayout());
		CategoryDataset dataset = grafico();
		
		JFreeChart chart = ChartFactory.createBarChart("Grafica comportamiento productos",
				"X",
				"Y",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false);
		
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}
	
	
	public CategoryDataset grafico()
	
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(4, "Pan Fresco", "Numero");
		dataset.addValue(2, "Coca Cola", "Numero");
		dataset.addValue(5, "Jabulani", "Numero");

		return dataset;
	}
	
	public static void main (String[] args) throws IOException, InvocationTargetException, InterruptedException
	{
		
		SwingUtilities.invokeAndWait(()->
		{
			VentanaGraficas ventana = new VentanaGraficas();
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
		}
		);
	}
}
