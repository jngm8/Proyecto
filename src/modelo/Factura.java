package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Factura implements Serializable

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	
	private ArrayList<Object[]> ProductosVendidos;
	private double valorTotalCompra;
	
	
	public Factura()
	{
		valorTotalCompra = 0;
		ProductosVendidos = new ArrayList<Object[]>();
	}


	public double getValorTotalCompra() 
	{
		return valorTotalCompra;
	}

	
	public void agregarProducto(Producto Producto, int cantidad) {
		Object[] datos = new Object[5];
		if (Producto.getPeso() == 1.0) {
			datos[0] = true;
			datos[4] = Producto.getUnidadDeMedida();
		}
		else {
			datos[0] = false;
			datos[4] = "";
		}
		datos[1] = Producto.getNombre();
		datos[2] = Producto.getPrecio();
		datos[3] = cantidad;
		
		valorTotalCompra += Producto.getPrecio()*cantidad;
		
		ProductosVendidos.add(datos);
	}

	public void generarFactura(Cliente Cliente, int numFactura) {
		String Path="./facturas/Pedido-" + numFactura + ".txt";
		File Factura =new File(Path);
		try 
		{
            FileWriter fw = new FileWriter(Factura);
            BufferedWriter bw = new BufferedWriter(fw);
            String Facturado = "Id: " + Cliente.getCedula() + "\n" + 
 				   "Cliente: " + Cliente.getNombre() + "\n" + 
 				   "\nProductos:\n";
 		
 		for (Object[] datos: ProductosVendidos)
 		{
 			if ((boolean) datos[0]) {
 				Facturado += (String) datos[1] + "\t\t" + (double) datos[2]*(int)datos[3] + "\n";
 				Facturado += "\t\t(" + (int) datos[3] + (String) datos[4] +  " x " + (double) datos[2] + ")" + "\n";
 			}
 			else {
 				if ((int) datos[3] > 1) {
 					Facturado += (String) datos[1] + "\t\t" + (double) datos[2] + " x" + (int) datos[3] + "\n";
 	 				Facturado += "\t" + (double) datos[2]*(int) datos[3] + "\n";
 				}
 				else {
 					Facturado += (String) datos[1] + "\t" + (double) datos[2] + "\n";
 				}
 			}
 		}
 		
 		Facturado += "\n\nPrecio Neto: $" + valorTotalCompra + "\n";
 		double iva = valorTotalCompra*0.19;
 		Facturado += "IVA: $" + iva + "\n";
 		double total = valorTotalCompra + iva;
 		Facturado += "Precio Total: $" + total;
            bw.write(Facturado);
            bw.close();
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	
	public void generarFacturaSinCliente(int numFactura) {
		String Path="./facturas/Pedido-" + numFactura + ".txt";
		File Factura =new File(Path);
		try 
		{
            FileWriter fw = new FileWriter(Factura);
            BufferedWriter bw = new BufferedWriter(fw);
            String Facturado = "Id: No registrado\n" + 
 				   "Cliente: No registrado\n" + 
 				   "\nProductos:\n";
 		
 		for (Object[] datos: ProductosVendidos)
 		{
 			if ((boolean) datos[0]) {
 				Facturado += (String) datos[1] + "\t\t" + (double) datos[2]*(int)datos[3] + "\n";
 				Facturado += "\t\t(" + (int) datos[3] + (String) datos[4] +  " x " + (double) datos[2] + ")" + "\n";
 			}
 			else {
 				if ((int) datos[3] > 1) {
 					Facturado += (String) datos[1] + "\t\t" + (double) datos[2] + " x" + (int) datos[3] + "\n";
 	 				Facturado += "\t\t" + (double) datos[2]*(int) datos[3] + "\n";
 				}
 				else {
 					Facturado += (String) datos[1] + "\t" + (double) datos[2] + "\n";
 				}
 			}
 		}
 		
 		Facturado += "\n\nPrecio Neto: $" + valorTotalCompra + "\n";
 		double iva = valorTotalCompra*0.19;
 		Facturado += "IVA: $" + iva + "\n";
 		double total = valorTotalCompra + iva;
 		Facturado += "Precio Total: $" + total;
            bw.write(Facturado);
            bw.close();
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}


	public void sumarPuntos(Cliente Cliente) {
		int puntos = (int) (valorTotalCompra/1000);
		Cliente.sumarAcumuladoPuntos(puntos);
		
	}
	
}



