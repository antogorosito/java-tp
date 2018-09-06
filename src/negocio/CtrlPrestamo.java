package negocio;

import java.util.ArrayList;

import database.DataPrestamo;
import entidades.*;

public class CtrlPrestamo 
{

	private DataPrestamo dp;
	
	public CtrlPrestamo()
	{
		dp=new DataPrestamo();
	}
	public int obtenerCantidad(int id)
	{
		return dp.obtenerCantidad(id);
	}
	public void add(Prestamo p) 
	{
		dp.add(p);
	}
	public Prestamo getOne(Prestamo p)
	{
		return dp.getOne(p);
	}
	public Prestamo getOne(Socio s)
	{
		return dp.getOne(s);
	}
	public Prestamo getOne(int id)
	{
		return dp.getOne(id);
	}
	public void update(Prestamo p, int di)
	{
		dp.update(p,di);
	}
	/*
	public void delete(Prestamo p)
	{
		dp.delete(p);
	}
	
	
	
	public Prestamo getOne(Prestamo p)
	{
		return dp.getOne(p);
	}
	
	public ArrayList<Prestamo> getAll()
	{
		return dp.getAll();
	}*/
}
