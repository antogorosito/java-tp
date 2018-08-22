package negocio;

import java.util.ArrayList;

import database.DataEjemplar;
import entidades.Ejemplar;
import java.util.ArrayList;

public class CtrlEjemplar 
{
	private DataEjemplar de;
	
	public CtrlEjemplar()
	{
		de=new DataEjemplar();
	}
	
	public void add(Ejemplar e) 
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
	
	/*public Ejemplar getOne(Ejemplar e)
	{
		return de.getOne(e);
	}
	
	public ArrayList<Ejemplar> getAll()
	{
		return de.getAll();
	}*/
}
