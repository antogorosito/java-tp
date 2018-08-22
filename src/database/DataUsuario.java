package database;

import entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;

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
		String queryString= "SELECT * FROM usuarios WHERE nombreUsuario=?";
		System.out.println("prueba3");
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(queryString);
			System.out.println("prueba2");
			stmt.setString(1,u);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) 	
			{
				l=new Usuario();
				l.setNombreUsuario(rs.getString("nombreUsuario"));
				l.setContraseña(rs.getString("contraseña"));
				System.out.println("id: " +l.getNombreUsuario());
				System.out.println("titulo" + l.getContraseña());
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			
				try {
					
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
