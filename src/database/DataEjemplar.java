package database;

import entidades.*;
import util.AppDataException;

import java.util.ArrayList;
import java.sql.*;

public class DataEjemplar 
{
	public ArrayList<Ejemplar> buscar(String t) throws AppDataException 
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<Ejemplar> ejemplares=	new ArrayList<Ejemplar>();;
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(" select ejemplares.idEjemplar,titulo,"
					+ "cantDiasMaxPrestamo,libros.idLibro,nroEdicion,fechaEdicion,ISBN from ejemplares "
					+ "inner join libros on libros.idLibro=ejemplares.idLibro where titulo like ? and "
					+ "ejemplares.idEjemplar not in (select lineas_de_prestamos.idEjemplar from lineas_de_prestamos where "
					+ "fechaDevolucion is null and lineas_de_prestamos.idEjemplar in(select ejemplares.idEjemplar from libros "
					+ "inner join ejemplares on ejemplares.idLibro=libros.idLibro where titulo like ?))");
			stmt.setString(1,t +"%");
			stmt.setString(2,t +"%");
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
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					l.setFechaEdicion(rs.getDate("fechaEdicion"));
					l.setIsbn(rs.getString("ISBN"));
					l.setNroEdicion(rs.getInt("nroEdicion"));
					e.setLibro(l);
					ejemplares.add(e);
				}
			}
			if(ejemplares.isEmpty()==true)
			{
				AppDataException ape = new AppDataException("No existen libros con el titulo " + t);
				throw ape;
			}
		}
		catch(SQLException e)
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
		return ejemplares;	
	}
	
	public Ejemplar getOne(int id) throws AppDataException // NO TOCAR
	{
		Ejemplar ee=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select titulo,cantDiasMaxPrestamo from libros "
					+ "inner join ejemplares on ejemplares.idLibro=libros.idLibro where idEjemplar=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					ee=new Ejemplar();
					ee.setIdEjemplar(id);
					Libro l=new Libro();
					l.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					l.setTitulo(rs.getString("titulo"));
					ee.setLibro(l);
				}
			}
			if(ee==null)
			{
				AppDataException ape = new AppDataException("No existe el ejemplar.");
				throw ape;
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
		return ee;
	}

	public Ejemplar getEjemplar(int id) throws AppDataException
	{
		Ejemplar ee=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from ejemplares where idEjemplar=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					ee=new Ejemplar();
					ee.setIdEjemplar(rs.getInt("idEjemplar"));
					Libro l=new Libro();
					l.setIdLibro(rs.getInt("idLibro"));
					ee.setLibro(l);

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
		return ee;
	}
	
	public void add(int i, Libro l)
	{
		PreparedStatement stmt=null;
		try
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("INSERT  INTO ejemplares(idEjemplar,idLibro) VALUES (?,?)");
			stmt.setInt(1, i);
			stmt.setInt(2, l.getIdLibro());
			stmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{	
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
}
