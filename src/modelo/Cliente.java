package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cliente 
{
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
	
	
}
