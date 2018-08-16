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
	
	public void add(Usuario u) 
	{
		du.add(u);
	}
	
	public void delete(Usuario u)
	{
		du.delete(u);
	}
	
	public void update(Usuario u)
	{
		du.update(u);
	}
	
	public Usuario getOne(Usuario u)
	{
		return du.getOne(u);
	}
	
	public ArrayList<Usuario> getAll()
	{
		return du.getAll();
	}
}
