package negocio;

import java.util.ArrayList;

import database.DataSancion;
import entidades.*;

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
	public Sancion getOne(LineaDePrestamo l)
	{
		return ds.getOne(l);
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
	
	
	public ArrayList<Sancion> getAll()
	{
		return ds.getAll();
	}*/
}
