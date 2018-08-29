package database;

import entidades.Socio;

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
	
/*	public Socio getOne(Socio s) { }
	
	public ArrayList<Socio> getAll() {}*/
}
