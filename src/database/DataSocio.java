package database;

import entidades.*;

import java.sql.*;
import java.util.GregorianCalendar;

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
			stmt.execute();
			//obtener el id
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
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
				stmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	
	
	public Socio getOne(String dni) 
	{
		/*boolean rta=false;
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
					rta=true;
				
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
		return rta;*/
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
		return s;
	}
	
	public void update(Socio s,boolean est) 
	{
		PreparedStatement stmt=null;
		try {
		
			
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("update socios set estado=? where idSocio=?");
			stmt.setBoolean(1, est);	
			stmt.setInt(2,s.getIdSocio());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	
/*	public ArrayList<Socio> getAll() {}
 * 	public void delete(Socio s) {}*/
}
