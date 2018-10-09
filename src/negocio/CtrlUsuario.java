package negocio;

import java.util.ArrayList;

import database.DataUsuario;
import entidades.Usuario;

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
	
	
	/*public ArrayList<Usuario> getAll()
	{
		return du.getAll();
	}
	
	public void delete(Usuario u)
	{
		du.delete(u);
	}
	
	public void update(Usuario u)
	{
		du.update(u);
	}*/
}
