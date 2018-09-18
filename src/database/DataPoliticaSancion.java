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
		return p;
	}
	/*	public void add(PoliticaSancion ps){}
	
	public void delete(PoliticaSancion ps) {}
	
	public void update(PoliticaSancion ps) {}
	

	
	public ArrayList<PoliticaSancion> getAll() {}*/
}
