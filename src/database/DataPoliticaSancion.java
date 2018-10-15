package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataPoliticaSancion 
{
	public PoliticaSancion getOne(int diasDif) 
	{
		PoliticaSancion p=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 	
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from politica_sanciones where ? between diasDeAtrasoDesde and diasDeAtrasoHasta");
			stmt.setInt(1,diasDif);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					p=new PoliticaSancion();
					p.setDiasDeAtrasoDesde(rs.getInt("diasDeAtrasoDesde"));
					p.setDiasDeAtrasoHasta(rs.getInt("diasDeAtrasoHasta"));
					p.setDiasDeSancion(rs.getInt("diasDeSancion"));
					p.setIdPoliticaSancion(rs.getInt("idPoliticaSancion"));
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
		return p;
	}
	
	public PoliticaSancion getMax() 
	{
		PoliticaSancion p=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select max(diasDeSancion) as maximo from politica_sanciones");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					p=new PoliticaSancion();
					p.setDiasDeSancion(rs.getInt("maximo"));
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
		return p;
	}
	
}
