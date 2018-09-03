package negocio;

import java.util.ArrayList;

import database.DataEjemplar;
import entidades.Ejemplar;
import entidades.Libro;

import java.util.ArrayList;

public class CtrlEjemplar 
{
	private DataEjemplar de;
	
	public CtrlEjemplar()
	{
		de=new DataEjemplar();
	}
	
	public ArrayList<Ejemplar> buscar(String t)
	{
		return de.buscar(t);
	}
	public Ejemplar getOne(int id)
	{
		return de.getOne(id);
	}
	/*public void add(Ejemplar e) 
	{
		de.add(e);
	}
	
	
	public void delete(Ejemplar e)
	{
		de.delete(e);
	}
	
	public void update(Ejemplar e)
	{
		de.update(e);
	}
	

	
	public ArrayList<Ejemplar> getAll()
	{
		return de.getAll();
	}*/
}
