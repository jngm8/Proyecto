package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cliente 
{
	private boolean inscritoSistemaPuntos;
	private ArrayList<Integer> acumuladoPuntos;
	private int edad;
	private char sexo;
	private String estadoCivil;
	private String situacionLaboral;
	private int cedula;
	
	
	
	public Cliente(boolean inscritoSistemaPuntos, ArrayList<Integer> acumuladoPuntos, int edad, char sexo,
			String estadoCivil, String situacionLaboral, int cedula) {
	
		this.inscritoSistemaPuntos = inscritoSistemaPuntos;
		this.acumuladoPuntos = acumuladoPuntos;
		this.edad = edad;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.situacionLaboral = situacionLaboral;
		this.cedula = cedula;
	}

	
}
