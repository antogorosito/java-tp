package negocio;

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
	
	public void update(Prestamo p, int di)
	{
		dp.update(p,di);
	}
	
	public void delete(Prestamo p)
	{
		dp.delete(p);
	}
	
	
}
