package database;

import entidades.*;
import java.sql.*;
import java.util.ArrayList;

import database.FactoryConexion;

public class DataSocio 
{
	public void add(Socio s)   
	{
		ResultSet keyResultSet=null;//
		PreparedStatement stmt=null;
		try
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("INSERT INTO socios(apellido,nombre,email,domicilio,telefono,dni,estado) VALUES (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getApellido());
			stmt.setString(2, s.getNombre());
			stmt.setString(3, s.getEmail());
			stmt.setString(4, s.getDomicilio());
			stmt.setString(5, s.getTelefono());
			stmt.setString(6, s.getDni());
			stmt.setBoolean(7, s.getEstado());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next())
			{
				s.setIdSocio(keyResultSet.getInt(1));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{	
				if(keyResultSet!=null)keyResultSet.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	
	
	public Socio getOne(String dni) 
	{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM socios WHERE dni=?");
			stmt.setString(1,dni);
			rs=stmt.executeQuery();
			if(rs!=null ) 	
			{
				while(rs.next())
				{
					s=new Socio();
					s.setApellido(rs.getString("apellido"));
					s.setDni(rs.getString("dni"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setEmail(rs.getString("email"));
					s.setEstado(rs.getBoolean("estado"));
					s.setIdSocio(rs.getInt("idSocio"));
					s.setNombre(rs.getString("nombre"));
					s.setTelefono(rs.getString("telefono"));				
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
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return s;
	}
	
	public Socio getOne(int id)
	{
		Socio s=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM socios WHERE idSocio=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null ) 	
			{
				while(rs.next())
				{
					s=new Socio();
					s.setApellido(rs.getString("apellido"));
					s.setDni(rs.getString("dni"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setEmail(rs.getString("email"));
					s.setEstado(rs.getBoolean("estado"));
					s.setIdSocio(id);
					s.setNombre(rs.getString("nombre"));
					s.setTelefono(rs.getString("telefono"));				
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
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return s;
	}
	
	public void update(Socio s,boolean est) 
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("update socios set estado=? where idSocio=?");
			stmt.setBoolean(1, est);	
			stmt.setInt(2,s.getIdSocio());
			stmt.executeUpdate();
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
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
	public ArrayList<Socio> getAllAInhabilitar() 
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<Socio> socios=new ArrayList<Socio>();
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select socios.* from socios inner join lineas_de_prestamos on lineas_de_prestamos.idSocio=socios.idSocio inner join prestamos on prestamos.idPrestamo=lineas_de_prestamos.idPrestamo where lineas_de_prestamos.fechaDevolucion is null and prestamos.fechaADevolver<current_date() and socios.estado=true group by socios.idSocio");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					Socio s=new Socio();
					s.setApellido(rs.getString("apellido"));
					s.setNombre(rs.getString("nombre"));
					s.setDni(rs.getString("dni"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setEmail(rs.getString("email"));
					s.setEstado(rs.getBoolean("estado"));
					s.setIdSocio(rs.getInt("idSocio"));
					s.setTelefono(rs.getString("telefono"));
					socios.add(s);					
				}
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
		return socios;
		
	}
	public ArrayList<Socio> getAllAHabilitar()  
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<Socio> socios=new ArrayList<Socio>();
		try 
		{	
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(" select *  from sanciones inner join socios on socios.idSocio=sanciones.idSancion where adddate(fechaSancionHasta,1)=current_date() and estado=false");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					Socio s=new Socio();
					s.setApellido(rs.getString("apellido"));
					s.setNombre(rs.getString("nombre"));
					s.setDni(rs.getString("dni"));
					s.setDomicilio(rs.getString("domicilio"));
					s.setEmail(rs.getString("email"));
					s.setEstado(rs.getBoolean("estado"));
					s.setIdSocio(rs.getInt("socios.idSocio"));
					s.setTelefono(rs.getString("telefono"));
					socios.add(s);	
				}
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
		return socios;
		
	}
	
	public ArrayList<Socio> getAllInhabilitados() 
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<Socio> listaSocios=new ArrayList<Socio>();
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from socios where estado=false");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					Socio ss=new Socio();
					ss.setIdSocio(rs.getInt("idSocio"));
					ss.setApellido(rs.getString("apellido"));
					ss.setNombre(rs.getString("nombre"));
					ss.setDni(rs.getString("dni"));
					ss.setDomicilio(rs.getString("domicilio"));
					ss.setEmail(rs.getString("email"));
					ss.setEstado(rs.getBoolean("estado"));
					ss.setTelefono(rs.getString("telefono"));
					listaSocios.add(ss);	
				}
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
		return listaSocios;
		
	}

}
