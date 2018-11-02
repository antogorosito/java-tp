package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
			stmt.executeUpdate();		
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
				if(keyResultSet!=null)keyResultSet.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	public Sancion getOne(LineaDePrestamo l)
	{
		Sancion s=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 	
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select sanciones.* from sanciones"
					+ " inner join lineas_de_prestamos on lineas_de_prestamos.idSancion=sanciones.idSancion"
					+ " where lineas_de_prestamos.idPrestamo=? and sanciones.fechaSancion=current_date()");
			stmt.setInt(1,l.getPrestamo().getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					s=new Sancion();
								
				s.setIdSancion(rs.getInt("idSancion"));
				s.setFechaSancion(rs.getDate("fechaSancion"));
				s.setFechaSancionHasta(rs.getDate("fechaSancionHasta"));
				Socio ss=new Socio();
				ss.setIdSocio(rs.getInt("idSocio"));
				s.setSocio(ss);
				
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
		return s;
	}
	
}
