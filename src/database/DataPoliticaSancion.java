package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataPoliticaSancion 
{
	public PoliticaSancion getOne(int diasDif) //ARREGLAR
	{
		PoliticaSancion p=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from politica_sanciones where");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					p=new PoliticaSancion();
					
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
