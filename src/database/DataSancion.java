package database;

import entidades.Sancion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSancion
{

	public void add(Sancion s)
	{

		ResultSet keyResultSet=null;//
		PreparedStatement stmt=null;
		try 
		{
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into sanciones(fechaSancion,fechaSancionHasta,idSocio) values(?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDate(1,s.getFechaSancion());
			stmt.setDate(2, s.getFechaSancionHasta());
			stmt.setInt(3, s.getSocio().getIdSocio());
			stmt.execute();		
			//obtener el id
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				s.setIdSancion(keyResultSet.getInt(1));
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
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	
	/*	public void delete(Sancion s) {}
	
	public void update(Sancion s) {}
	
	public Sancion getOne(Sancion s) { }
	
	public ArrayList<Sancion> getAll() {}*/
}
