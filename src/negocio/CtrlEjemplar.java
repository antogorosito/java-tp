package negocio;

import java.util.ArrayList;

import database.DataEjemplar;
import entidades.*;



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

	public Ejemplar getEjemplar(int i) {
		return de.getEjemplar(i);
	}
	
	public void add(int id, Libro l) 
	{
		de.add(id,l);
	}
	
}
