package negocio;

import java.util.ArrayList;

import database.DataSancion;
import entidades.Sancion;

public class CtrlSancion 
{

	private DataSancion ds;
	
		public CtrlSancion()
	{
		ds=new DataSancion();
	}
	
	public void add(Sancion s) 
	{
		ds.add(s);
	}
/*	
	public void delete(Sancion s)
	{
		ds.delete(s);
	}
	
	public void update(Sancion s)
	{
		ds.update(s);
	}
	
	public Sancion getOne(Sancion s)
	{
		return ds.getOne(s);
	}
	
	public ArrayList<Sancion> getAll()
	{
		return ds.getAll();
	}*/
}
