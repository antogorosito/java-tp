package database;

import entidades.*;
import util.AppDataException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataPoliticaPrestamo
{

	public PoliticaPrestamo getOne() throws AppDataException
	{
		PoliticaPrestamo p=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from politica_prestamos where fechaVigenciaPolPrestamo >=current_date");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					p=new PoliticaPrestamo();
					p.setCantMaxLibrosPend(rs.getInt("cantMaxLibrosPend"));
					p.setIdPoliticaPrestamo(rs.getInt("idPoliticaPrestamo"));
				}
			}
		} 
		catch (SQLException e) 
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
		return p;
	}
	
}
