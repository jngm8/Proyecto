package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cliente implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
	private long cedula;
	private int acumuladoPuntos;
	private int edad;
	private char sexo;
	private String estadoCivil;
	private String situacionLaboral;
	
	
	
	
	public Cliente(long cedula, 
				   int acumuladoPuntos,
				   int edad, char sexo,
				   String estadoCivil,
				   String situacionLaboral) {
		this.cedula = cedula;
		this.acumuladoPuntos = acumuladoPuntos;
		this.edad = edad;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
		
	}

	public long getCedula() 
	{
		return cedula;
	}

	public int getAcumuladoPuntos() 
	{
		return acumuladoPuntos;
	}

	public void setAcumuladoPuntos(int acumuladoPuntos) 
	{
		this.acumuladoPuntos = acumuladoPuntos;
	}

	public int getEdad() 
	{
		return edad;
	}

	public char getSexo() 
	{
		return sexo;
	}

	public String getEstadoCivil() 
	{
		return estadoCivil;
	}

	public String getSituacionLaboral() 
	{
		return situacionLaboral;
	}

	
}
