package database;

import java.sql.*;
import java.util.ArrayList;

import entidades.Ejemplar;
import entidades.Libro;
import entidades.Socio;


public class DataLibro 
{

	public void add(Libro l){}
	
	public void delete(Libro l) {}
	
	public void update(Libro l) {
		
	}

	
	public Libro getOne(String ISBN) {
		
		Libro l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM libros WHERE ISBN=?");
			stmt.setString(1,ISBN);
			rs=stmt.executeQuery();
			if(rs!=null ) 	
			{
				while(rs.next())
				{
					l=new Libro();
					l.setTitulo(rs.getString("titulo"));
					l.setIsbn(rs.getString("ISBN"));
					l.setNroEdicion(rs.getInt("nroEdicion"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					//l.setCantMax(rs.getInt("cantDiasMaxPrestamo"));
					l.setIdLibro(rs.getInt("idLibro"));
									
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
		return l;
	}
	
	/*public ArrayList<Libro> getAll() {
	
			
	}*/
}
