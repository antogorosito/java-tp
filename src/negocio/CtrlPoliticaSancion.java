package negocio;

import java.util.ArrayList;

import database.DataPoliticaSancion;
import entidades.PoliticaSancion;;

public class CtrlPoliticaSancion 
{

	private DataPoliticaSancion dps;
	
	public CtrlPoliticaSancion()
	{
		dps=new DataPoliticaSancion();
	}
	
	public void add(PoliticaSancion ps) 
	{
		dps.add(ps);
	}
	
	public void delete(PoliticaSancion ps)
	{
		dps.delete(ps);
	}
	
	public void update(PoliticaSancion ps)
	{
		dps.update(ps);
	}
	
	public PoliticaSancion getOne(PoliticaSancion ps)
	{
		return dps.getOne(ps);
	}
	
	public ArrayList<PoliticaSancion> getAll()
	{
		return dps.getAll();
	}
}