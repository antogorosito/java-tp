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
		String queryString= "SELECT * FROM usuarios WHERE nombreUsuario=? and contrase�a=?";
	
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(queryString);
			stmt.setString(1,u);
			stmt.setString(2,c);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) 	
			{
				l=new Usuario();
				l.setNombreUsuario(rs.getString("nombreUsuario"));
				l.setContrase�a(rs.getString("contrase�a"));
				
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
