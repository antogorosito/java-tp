package database;

import entidades.*;

import java.sql.*;


public class DataUsuario
{
	
	public Usuario getOne(String u, String c) 
	{
		Usuario l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM usuarios WHERE nombreUsuario=? and clave=?");
			stmt.setString(1,u);
			stmt.setString(2,c);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					l=new Usuario();
					l.setNombreUsuario(rs.getString("nombreUsuario"));
					l.setClave(rs.getString("clave"));
					l.setTipo(rs.getInt("tipo"));
					Socio s=new Socio();
					s.setIdSocio(rs.getInt("idSocio"));
					l.setSocio(s);
				}
			}
		} 
		catch (SQLException e)
		{
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
	public void add(Usuario u,int id)
	{
		PreparedStatement stmt=null;
		try
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("INSERT  INTO usuarios(nombreUsuario,clave,tipo,idSocio) VALUES (?,?,?,?)");
			stmt.setString(1, u.getNombreUsuario());
			stmt.setString(2,u.getClave());
			stmt.setInt(3, u.getTipo());
			stmt.setInt(4, id);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{	
				stmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	
	
	
}
