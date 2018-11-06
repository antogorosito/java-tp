package negocio;

import java.sql.Date;

import database.DataPrestamo;
import entidades.*;
import util.AppDataException;

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
	public void add(Prestamo p) throws AppDataException
	{
		dp.add(p);
	}
	
	public void update(Prestamo p, int di,Date sDate) throws AppDataException
	{
		dp.update(p,di,sDate); 
	}
	
	public void delete(Prestamo p) throws AppDataException
	{
		dp.delete(p);
	}
	public Prestamo getOne(int id)
	{
		return dp.getOne(id);
	}
	
}
