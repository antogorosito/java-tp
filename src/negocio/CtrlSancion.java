package negocio;

import database.DataSancion;
import entidades.*;
import util.AppDataException;

public class CtrlSancion 
{

	private DataSancion ds;
	
		public CtrlSancion()
	{
		ds=new DataSancion();
	}
	
	public void add(Sancion s) throws AppDataException
	{
		ds.add(s);
	}
	public Sancion getOne(LineaDePrestamo l)
	{
		return ds.getOne(l);
	}
	

}
