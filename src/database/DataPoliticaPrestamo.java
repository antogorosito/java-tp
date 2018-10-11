package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataPoliticaPrestamo
{

	public PoliticaPrestamo getOne()
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
		return p;
	}
	
}
