package negocio;

import java.text.ParseException;
import java.util.ArrayList;

import database.*;
import entidades.*;

public class CtrlLineaDePrestamo 
{

	private DataLineaDePrestamo de;
	
	public CtrlLineaDePrestamo()
	{
		de=new DataLineaDePrestamo();
	}
	
	public boolean getOne(int socio, int ejem)
	{
		return de.getOne(socio, ejem);
	}
	public void add(LineaDePrestamo lp) 
	{
		de.add(lp);
	}
	public boolean buscarLinea(LineaDePrestamo lp)
	{
		return de.buscarLinea(lp);
	}
	public ArrayList<LineaDePrestamo> getAll(Prestamo p)
	{
		return de.getAll(p);
	}
	public int minimoDias(Prestamo p)
	{
		return de.minimoDias(p);
	}
	public LineaDePrestamo getOne(LineaDePrestamo lp)
	{
		return de.getOne(lp);
	}
	public LineaDePrestamo getOne(int id)
	{
		return de.getOne(id);
	}
	public void update(LineaDePrestamo lp,Sancion s) throws ParseException
	{
		de.update(lp,s);
	}
	public void update(LineaDePrestamo lp) throws ParseException
	{
		de.update(lp);
	}
	
	public void delete(LineaDePrestamo lp)
	{
		de.delete(lp);
	}
	
	/*
	
	public LineaDePrestamo getOne(LineaDePrestamo lp)
	{
		return de.getOne(lp);
	}
	
	*/
}
