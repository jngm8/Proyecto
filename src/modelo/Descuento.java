package modelo;

import java.util.Calendar;

public class Descuento 
{
	long codigoDeBarras;
	int porcentaje;
	Calendar fechaInicio;
	Calendar fechaVencimiento;

	public Descuento(long codigoDeBarras, int porcentaje, Calendar fechaInicio, Calendar fechaVencimiento) 
	{
		this.codigoDeBarras = codigoDeBarras;
		this.porcentaje = porcentaje;
		this.fechaInicio = fechaInicio;
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getPorcentaje() 
	{		
		return porcentaje;
	}
	
	public long getcodigoDeBarras() 
	{		
		return codigoDeBarras;
	}
	
	public Calendar getFechaDeInicio() 
	{		
		return fechaInicio;
	}
	
	public Calendar getFechaDeVencimiento() 
	{		
		return fechaVencimiento;
	}
	
	
}	
