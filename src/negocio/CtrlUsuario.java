package negocio;

import database.DataUsuario;
import entidades.Usuario;
import util.AppDataException;

public class CtrlUsuario
{
	private DataUsuario du;
	
	public CtrlUsuario()
	{
		du=new DataUsuario();
	}

	public Usuario getOne(String u,String c) throws AppDataException
	{
		return du.getOne(u,c);
	}
	public void add(Usuario u, int id) throws AppDataException
	{
		du.add(u,id);
	}

}
