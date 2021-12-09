package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Factura implements Serializable

{
	private static final long serialVersionUID = 100L;
	private String stringProductos;
	private double valorTotalCompra;
	
	
	public Factura()
	{
		valorTotalCompra = 0;
		stringProductos = "";
	}


	public double getValorTotalCompra() 
	{
		return valorTotalCompra;
	}
	
	public void agregarProducto(String venta, double valor)
	{
		stringProductos += venta;
		valorTotalCompra += valor;
	}
	
	public int getPuntosCompra() 
	{
		return (int) (valorTotalCompra/1000);
		//Cliente.sumarAcumuladoPuntos(puntos);
	}
	
	
	public void generarFactura(Cliente cliente, int numFactura) 
	{
		String Path="./facturas/Pedido-" + numFactura + ".txt";
		File Factura =new File(Path);
		try 
		{
            FileWriter fw = new FileWriter(Factura);
            BufferedWriter bw = new BufferedWriter(fw);
            String facturado = "Id: " + cliente.getCedula() + "\n" + 
 				   "Cliente: " + cliente.getNombre() + "\n" + 
 				  "-----------------------------------------------\n"+
 				   "\nPRODUCTOS:\n";
            facturado += stringProductos + "\n-----------------------------------------------\n";
            
            facturado += "\nPrecio Neto: $" + valorTotalCompra + "\n";
	 		double iva = valorTotalCompra*0.19;
	 		facturado += "IVA: $" + iva + "\n";
	 		double total = valorTotalCompra + iva;
	 		facturado += "Precio Total: $" + total;
	            bw.write(facturado);
	            bw.close();
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
        }
	}
	
	public void generarFacturaSinCliente(int numFactura) 
	{
		String Path="./facturas/Pedido-" + numFactura + ".txt";
		File Factura =new File(Path);
		try 
		{
            FileWriter fw = new FileWriter(Factura);
            BufferedWriter bw = new BufferedWriter(fw);
            String Facturado = "Id: No registrado\n" + 
 				   "Cliente: No registrado\n" + 
 				   "-----------------------------------------------\n"+
 				   "\nPRODUCTOS:\n";
 		
            Facturado += stringProductos;
            
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
}



