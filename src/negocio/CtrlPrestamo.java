package negocio;

import java.util.ArrayList;

import database.DataPrestamo;
import entidades.Prestamo;

public class CtrlPrestamo 
{

	private DataPrestamo dp;
	
	public CtrlPrestamo()
	{
		dp=new DataPrestamo();
	}
	
	public void add(Prestamo p) 
	{
		dp.add(p);
	}
	
	public void delete(Prestamo p)
	{
		dp.delete(p);
	}
	
	public void update(Prestamo p)
	{
		dp.update(p);
	}
	
	public Prestamo getOne(Prestamo p)
	{
		return dp.getOne(p);
	}
	
	public ArrayList<Prestamo> getAll()
	{
		return dp.getAll();
	}
}
