package database;

import entidades.*;
import util.AppDataException;

import java.sql.*;


public class DataUsuario
{	
	public Usuario getOne(String u, String c)  throws AppDataException
	{
		Usuario l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM usuarios left join socios on socios.idSocio=usuarios.idSocio WHERE nombreUsuario=? and clave=?");
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
					s.setApellido(rs.getString("apellido"));
					s.setDni(rs.getString("dni"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setEmail(rs.getString("email"));
					s.setEstado(rs.getBoolean("estado"));
					s.setIdSocio(rs.getInt("idSocio"));
					s.setNombre(rs.getString("nombre"));
					s.setTelefono(rs.getString("telefono"));	
					l.setSocio(s);
				}
			} 
			if(l==null)
			{
				AppDataException ape = new AppDataException("No existe el usuario con ese usuario y/o contraseña.");
				throw ape;
			}
		}
		catch (SQLException e )
		{
			AppDataException ape = new AppDataException(e, "Error en base de datos");
			throw ape;
		}
		
		finally 
		{
			try 
			{	
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return l;
	
	}
	public void add(Usuario u,int id) throws AppDataException
	{
		PreparedStatement stmt=null;
		try
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("INSERT  INTO usuarios(nombreUsuario,clave,tipo,idSocio) VALUES (?,?,?,?)");
			stmt.setString(1, u.getNombreUsuario());
			stmt.setString(2,u.getClave());
			stmt.setInt(3, u.getTipo());
			stmt.setInt(4, id);
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			AppDataException ape = new AppDataException(e, "Error en base de datos al agregar");
			throw ape;
		}
		finally 
		{
			try 
			{	
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	
	
	
}
