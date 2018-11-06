package negocio;

import database.DataLibro;
import entidades.Libro;
import util.AppDataException;

public class CtrlLibro 
{

	private DataLibro de;
	
	public CtrlLibro()
	{
		de=new DataLibro();
	}
	
	public Libro getOne(String ISBN) throws AppDataException
	{
		return de.getOne(ISBN);
	}
	public void add(Libro l) throws AppDataException
	{
		de.add(l);
	}
	
}
