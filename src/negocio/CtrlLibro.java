package negocio;

import java.util.ArrayList;

import database.DataLibro;
import entidades.Libro;

public class CtrlLibro 
{

	private DataLibro de;
	
	public CtrlLibro()
	{
		de=new DataLibro();
	}
	
	/*
	

	
	public void delete(Libro l)
	{
		de.delete(l);
	}
	
	public void update(Libro l)
	{
		de.update(l);
	}
	*/
	public Libro getOne(String ISBN)
	{
		return de.getOne(ISBN);
	}
	public void add(Libro l) 
	{
		de.add(l);
	}
	
	/*public ArrayList<Libro> getAll()
	{
		return de.getAll();
	}*/
}
