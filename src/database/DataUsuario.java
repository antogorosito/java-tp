package database;

import entidades.Usuario;

import java.sql.*;


public class DataUsuario
{
public void add(Usuario u){}
	
	public void delete(Usuario u) {}
	
	public void update(Usuario u) {}
	
	public Usuario getOne(String u, String c) 
	{
		Usuario l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM usuarios WHERE nombreUsuario=? and contraseña=?");
			stmt.setString(1,u);
			stmt.setString(2,c);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					l=new Usuario();
					l.setNombreUsuario(rs.getString("nombreUsuario"));
					l.setContraseña(rs.getString("contraseña"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try 
			{	
				stmt.close();
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return l;
	
	}
	
	//public ArrayList<Usuario> getAll() {}
}
