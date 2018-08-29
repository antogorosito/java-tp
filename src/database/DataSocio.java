package database;

import entidades.*;

import java.sql.*;


import database.FactoryConexion;

public class DataSocio 
{
	public void add(Socio s)   
	{
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
	
	
	public void delete(Socio s) {}
	
	public void update(Socio s) {}
	
	public boolean getOne(String dni) 
	{
		boolean rta=false;
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
		return rta;
	}
	
/*	public ArrayList<Socio> getAll() {}*/
}
