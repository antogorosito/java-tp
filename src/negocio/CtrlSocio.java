package negocio;

import java.util.ArrayList;

import database.DataSocio;
import entidades.Socio;

public class CtrlSocio
{
	private DataSocio ds;
	
	public CtrlSocio()
	{
		ds=new DataSocio();
	}
	
	public void add(Socio s) 
	{
		ds.add(s);
	}
	
	public void delete(Socio s)
	{
		ds.delete(s);
	}
	
	public void update(Socio s)
	{
		ds.update(s);
	}
	
	/*public Socio getOne(Socio s)
	{
		return ds.getOne(s);
	}
	
	public ArrayList<Socio> getAll()
	{
		return ds.getAll();
	}*/
}
