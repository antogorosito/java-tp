package negocio;

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
/*	
	public void delete(LineaDePrestamo lp)
	{
		de.delete(lp);
	}
	
	public void update(LineaDePrestamo lp)
	{
		de.update(lp);
	}
	
	public LineaDePrestamo getOne(LineaDePrestamo lp)
	{
		return de.getOne(lp);
	}
	
	*/
}
