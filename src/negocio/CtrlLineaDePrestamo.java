package negocio;

import java.util.ArrayList;

import database.DataLineaDePrestamo;
import entidades.LineaDePrestamo;;

public class CtrlLineaDePrestamo 
{

	private DataLineaDePrestamo de;
	
	public CtrlLineaDePrestamo()
	{
		de=new DataLineaDePrestamo();
	}
	
	public void add(LineaDePrestamo lp) 
	{
		de.add(lp);
	}
	
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
	
	public ArrayList<LineaDePrestamo> getAll()
	{
		return de.getAll();
	}
}