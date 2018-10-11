package negocio;

import database.DataPoliticaPrestamo;
import entidades.PoliticaPrestamo;;

public class CtrlPoliticaPrestamo 
{

	private DataPoliticaPrestamo dpp;
	
	public CtrlPoliticaPrestamo()
	{
		dpp=new DataPoliticaPrestamo();
	}
	public PoliticaPrestamo getOne()
	{
		return dpp.getOne();
	}

}
