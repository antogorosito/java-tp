package database;

import entidades.*;
import java.util.ArrayList;
import java.sql.*;

public class DataEjemplar 
{

	public void add(Ejemplar e){}
	
	public void delete(Ejemplar e) {}
	
	public void update(Ejemplar e) {}
	
	public ArrayList<Ejemplar> buscar(String t) 
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<Ejemplar> ejemplares=new ArrayList<Ejemplar>();
		try 
		{
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT idEjemplar,libros.idLibro,titulo FROM libros INNER JOIN ejemplares ON libros.idLibro=ejemplares.idLibro WHERE titulo like ?");
			stmt.setString(1,t +"%");
			
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					Ejemplar e=new Ejemplar();
					e.setIdEjemplar(rs.getInt("idEjemplar"));	
					Libro l =new Libro();
					l.setTitulo(rs.getString("titulo"));
					l.setIdLibro(rs.getInt("libros.idLibro"));
					e.setLibro(l);
					ejemplares.add(e);
					
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
				stmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return ejemplares;
		
	}
	
	/*public Ejemplar getOne(Ejemplar e) { }
	
	public ArrayList<Ejemplar> getAll() 
	{
				
	}*/
	
}
