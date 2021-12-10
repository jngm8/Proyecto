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
	private static final long serialVersionUID = 100L;
	private String nombre;
	private long cedula;
	private int acumuladoPuntos;
	private int edad;
	private String sexo;
	private String estadoCivil;
	private String situacionLaboral;
	
	public Cliente(String nombre, 
				   long cedula, 
				   int edad, 
				   String sexo,
				   String estadoCivil,
				   String situacionLaboral) 
	
	{
		this.nombre = nombre;
		this.cedula = cedula;
		this.acumuladoPuntos = 0;
		this.edad = edad;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
	}
	
	/*
	 *  Getters
	 */
	
	public String getNombre() 
	{
		return nombre;
	}

	public long getCedula() 
	{
		return cedula;
	}

	public int getAcumuladoPuntos() 
	{
		return acumuladoPuntos;
	}

	public void sumarAcumuladoPuntos(int acumuladoPuntos) 
	{
		this.acumuladoPuntos += acumuladoPuntos;
	}
	
	
	public void restarPuntos(int resta)
	{
		acumuladoPuntos -= resta;
	}
	public int getEdad() 
	{
		return edad;
	}

	public String getSexo() 
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
	
	/*
	 *  Metodo para imprimir en consola
	 */
	
	public void PrintInfo()
	{
		System.out.println("Nombre: "+ nombre);
		System.out.println("Numero de cedula: " + cedula);
		System.out.println("Puntos: " + acumuladoPuntos);
		System.out.println("Con edad: " + edad);
		System.out.println("Sexo: "+ sexo);
		System.out.println("Estado civil: "+ estadoCivil );
		System.out.println("Situación Laboral: "+ situacionLaboral );
	}

	

	
}
