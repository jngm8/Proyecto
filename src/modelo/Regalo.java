package modelo;

import java.util.Calendar;

public class Regalo 
{
	long codigoDeBarras;
	int pague;
	int lleva;
	Calendar fechaInicio;
	Calendar fechaVencimiento;
	public Regalo(long codigoDeBarras, int pague, int lleva, Calendar fechaInicio, Calendar fechaVencimiento)
	{
		this.codigoDeBarras = codigoDeBarras;
		this.pague = pague;
		this.lleva = lleva;
		this.fechaInicio = fechaInicio;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public int getPague() 
	{
		return pague;
	}
	
	public int getLleva() 
	{
		return lleva;
	}
	
	public Calendar getFechaDeInicio() 
	{		
		return fechaInicio;
	}
	
	public Calendar getFechaDeVencimiento() 
	{		
		return fechaVencimiento;
	}
	
	public long getcodigoDeBarras() 
	{		
		return codigoDeBarras;
	}
	
}
