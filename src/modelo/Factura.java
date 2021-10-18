package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Factura implements Serializable

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private HashMap<String,String> resumenCompra;
	private double valorTotalCompra;
	private double puntosAcumulados;
	
	
	
	public Factura()
	{
		resumenCompra = new HashMap<String,String>();
	}


	public double getValorTotalCompra() 
	{
		return valorTotalCompra;
	}

	public double getPuntosAcumulados() 
	{
		return puntosAcumulados;
	}
	
	public void mostar() {
		
		System.out.println("El valor total de la compra es: "+valorTotalCompra+"\nLos puntos acumulados son: " +puntosAcumulados+"");
	}


	
	
	
	
	
}



