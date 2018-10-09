package database;

import java.sql.*;
import java.util.ArrayList;

import entidades.Ejemplar;
import entidades.Libro;
import entidades.Socio;


public class DataLibro 
{
/*
	
	
	public void delete(Libro l) {}
	
	public void update(Libro l) {
		
	}*/

	
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
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
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
	public void add(Libro l){
		ResultSet keyResultSet=null;//
		PreparedStatement stmt=null;
	
		try
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("INSERT INTO libros(titulo,ISBN,nroEdicion,fechaEdicion,cantDiasMaxPrestamo) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, l.getTitulo());
			stmt.setString(2, l.getIsbn());
			stmt.setInt(3, l.getNroEdicion());
			stmt.setDate(4, l.getFechaEdicion());
			stmt.setInt(5, l.getCantDiasMaxPrestamo());
			stmt.execute();
			//obtener el id
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				l.setIdLibro((keyResultSet.getInt(1)));
			
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
	}
	/*public ArrayList<Libro> getAll() {
	
			
	}*/
}
