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

	public Usuario getOne(String u,String c) 
	{
		return du.getOne(u,c);
	}
	public void add(Usuario u, int id) 
	{
		du.add(u,id);
	}

}
