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
	
/*	public void delete(Socio s)
	{
		ds.delete(s);
	}
	
	public void update(Socio s)
	{
		ds.update(s);
	}*/
	
	public boolean getOne(String dni)
	{
		return ds.getOne(dni);
	}/*
	
	public ArrayList<Socio> getAll()
	{
		return ds.getAll();
	}*/
}
