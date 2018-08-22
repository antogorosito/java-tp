package negocio;

import java.util.ArrayList;

import database.DataPoliticaPrestamo;
import entidades.PoliticaPrestamo;;

public class CtrlPoliticaPrestamo 
{

	private DataPoliticaPrestamo dpp;
	
	public CtrlPoliticaPrestamo()
	{
		dpp=new DataPoliticaPrestamo();
	}
	
	public void add(PoliticaPrestamo pp) 
	{
		dpp.add(pp);
	}
	
	public void delete(PoliticaPrestamo pp)
	{
		dpp.delete(pp);
	}
	
	public void update(PoliticaPrestamo pp)
	{
		dpp.update(pp);
	}
	
/*	public PoliticaPrestamo getOne(PoliticaPrestamo pp)
	{
		return dpp.getOne(pp);
	}
	
	public ArrayList<PoliticaPrestamo> getAll()
	{
		return dpp.getAll();
	}*/
}
