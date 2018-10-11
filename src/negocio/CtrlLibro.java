package negocio;

import database.DataLibro;
import entidades.Libro;

public class CtrlLibro 
{

	private DataLibro de;
	
	public CtrlLibro()
	{
		de=new DataLibro();
	}
	
	public Libro getOne(String ISBN)
	{
		return de.getOne(ISBN);
	}
	public void add(Libro l) 
	{
		de.add(l);
	}
	
}
