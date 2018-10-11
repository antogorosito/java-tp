package negocio;

import database.DataPoliticaSancion;
import entidades.PoliticaSancion;;

public class CtrlPoliticaSancion 
{

	private DataPoliticaSancion dps;
	
	public CtrlPoliticaSancion()
	{
		dps=new DataPoliticaSancion();
	}
	public PoliticaSancion getOne(int diasDif)
	{
		return dps.getOne(diasDif);
	}
	public PoliticaSancion getMax()
	{
		return dps.getMax();
	}
	

}
