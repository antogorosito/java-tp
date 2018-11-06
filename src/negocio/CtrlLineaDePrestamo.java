package negocio;

import java.text.ParseException;
import java.util.ArrayList;

import database.*;
import entidades.*;
import util.AppDataException;

public class CtrlLineaDePrestamo 
{

	private DataLineaDePrestamo de;
	
	public CtrlLineaDePrestamo()
	{
		de=new DataLineaDePrestamo();
	}
	
	public LineaDePrestamo getOne(Socio socio,int ejem) throws AppDataException
	{
		return de.getOne(socio, ejem);
	}
	public void add(LineaDePrestamo lp) throws AppDataException
	{
		de.add(lp);
	}
	public LineaDePrestamo existe(int id, Socio s) throws AppDataException
	{
		return de.existe(id,s);
	}
	public LineaDePrestamo buscarLinea(LineaDePrestamo lp) throws AppDataException
	{
		return de.buscarLinea(lp);
	}
	public ArrayList<LineaDePrestamo> getAll(Prestamo p) throws AppDataException
	{
		return de.getAll(p);
	}
	public int minimoDias(Prestamo p)
	{
		return de.minimoDias(p);
	}

	public LineaDePrestamo getOne(int id) throws AppDataException
	{
		return de.getOne(id);
	}
	public void update(LineaDePrestamo lp,Sancion s) throws AppDataException,ParseException
	{
		de.update(lp,s);
	}
	public void update(LineaDePrestamo lp) throws AppDataException,ParseException
	{
		de.update(lp);
	}
	
	public void delete(LineaDePrestamo lp)
	{
		de.delete(lp);
	}
	
	public ArrayList<LineaDePrestamo> getAll(int id) throws AppDataException
	{
		return de.getAll(id);
	}
	
	public LineaDePrestamo obtener(int id)
	{
		return de.obtener(id);
	}
	public ArrayList<LineaDePrestamo> getAllPendiente() throws AppDataException
	{
		return de.getAllPendiente();
	}
	
}
