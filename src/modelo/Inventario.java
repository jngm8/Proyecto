package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Inventario 
{
	private HashMap<Integer, Producto> Productos;
	
	private HashMap<String, ArrayList<Integer>> Categorias;
	
	public Inventario()
	{
		Productos = new HashMap<Integer,Producto>();
		Categorias = new HashMap<String,ArrayList<Integer>>();
	}
	
	

}
