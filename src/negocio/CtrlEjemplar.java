package negocio;

import java.util.ArrayList;

import database.DataEjemplar;
import entidades.*;

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
	public boolean existe(int id, Socio s)
	{
		return de.existe(id,s);
	}
	public boolean existeDevolucion(int id)
	{
		return de.existeDevolucion(id);
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
