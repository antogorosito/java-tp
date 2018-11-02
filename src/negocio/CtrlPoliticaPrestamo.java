package negocio;

import database.DataPoliticaPrestamo;
import entidades.PoliticaPrestamo;
import util.AppDataException;;

public class CtrlPoliticaPrestamo 
{

	private DataPoliticaPrestamo dpp;
	
	public CtrlPoliticaPrestamo()
	{
		dpp=new DataPoliticaPrestamo();
	}
	public PoliticaPrestamo getOne() throws AppDataException
	{
		return dpp.getOne();
	}

}
