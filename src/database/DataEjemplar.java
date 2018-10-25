package database;

import entidades.*;
import java.util.ArrayList;
import java.sql.*;

public class DataEjemplar 
{
	public ArrayList<Ejemplar> buscar(String t) 
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<Ejemplar> ejemplares=new ArrayList<Ejemplar>();
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select ejemplares.idEjemplar,titulo,cantDiasMaxPrestamo,libros.idLibro,nroEdicion,fechaEdicion,ISBN from libros inner join ejemplares on ejemplares.idLibro=libros.idLibro where titulo like ? and idEjemplar not in (select ejemplares.idEjemplar from libros inner join ejemplares on ejemplares.idLibro=libros.idLibro inner join lineas_de_prestamos on lineas_de_prestamos.idEjemplar=ejemplares.idEjemplar where fechaDevolucion is null and devuelto= false and titulo like ?)");
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
		}
		catch(SQLException e)
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
		return ejemplares;	
	}
	
	public Ejemplar getOne(int id)
	{
		Ejemplar ee=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select titulo,cantDiasMaxPrestamo from libros inner join ejemplares on ejemplares.idLibro=libros.idLibro where idEjemplar=?");
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
		return ee;
	}
	
	public boolean existe(int id,Socio s)
	{
		boolean rta=false;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from libros inner join ejemplares on ejemplares.idLibro=libros.idLibro inner join lineas_de_prestamos on lineas_de_prestamos.idEjemplar=ejemplares.idEjemplar where fechaDevolucion is null and devuelto= false and ejemplares.idEjemplar=? and lineas_de_prestamos.idSocio!=?");
			stmt.setInt(1,id);
			stmt.setInt(2,s.getIdSocio());
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					rta=true;
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
		return rta;
	}
	public Ejemplar getEjemplar(int id)
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
