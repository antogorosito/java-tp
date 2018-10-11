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
	
	public Socio getOne(int id)
	{
		return ds.getOne(id);
	}
	
	public void add(Socio s) 
	{
		ds.add(s);
	}
	public void update(Socio s,boolean est)
	{
		ds.update(s,est);
	}
	
	public Socio getOne(String dni)
	{
		return ds.getOne(dni);
	}
	
	public ArrayList<Socio> getAllAInhabilitar()
	{
		return ds.getAllAInhabilitar();
	}
	public ArrayList<Socio> getAllAHabilitar()
	{
		return ds.getAllAHabilitar();
	}
	public ArrayList<Socio> getAllInhabilitados()
	{
	
		return ds.getAllInhabilitados();
	}
	
}
