package negocio;

import java.util.ArrayList;

import database.DataEjemplar;
import entidades.*;
import util.AppDataException;



public class CtrlEjemplar 
{
	private DataEjemplar de;
	
	public CtrlEjemplar()
	{
		de=new DataEjemplar();
	}
	
	public ArrayList<Ejemplar> buscar(String t) throws AppDataException 
	{
		return de.buscar(t);
	}
	public Ejemplar getOne(int id) throws AppDataException 
	{
		return de.getOne(id);
	}
	public Ejemplar getEjemplar(int i) throws AppDataException 
	{
		return de.getEjemplar(i);
	}
	
	public void add(int id, Libro l) 
	{
		de.add(id,l);
	}
	
}
