package database;

import entidades.*;
import java.sql.*;
import java.util.GregorianCalendar;

public class DataPrestamo 
{
	
	public int obtenerCantidad(int id)
	{
		int cant=0;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select count(*) from  prestamos inner join lineas_de_prestamos on lineas_de_prestamos.idPrestamo=prestamos.idPrestamo where (fechaDevolucion is null) and prestamos.idSocio=? group by prestamos.idSocio");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next() ) 	
			{
				cant=rs.getInt(1);
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
		return cant;
	
	}
	public void add(Prestamo p)
	{
		ResultSet keyResultSet=null;//
		PreparedStatement stmt=null;
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into prestamos(fechaPrestamo,horaPrestamo,diasPrestamo,fechaADevolver,idSocio) values(?,?,null,null,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, p.getFechaPrestamo());
			stmt.setTime(2, p.getHoraPrestamo());
			stmt.setInt(3,p.getSocio().getIdSocio());
			stmt.execute();		
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setIdPrestamo(keyResultSet.getInt(1));
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
	
	public void update(Prestamo p,int di,Date sDate)
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("update prestamos set diasPrestamo=?,fechaADevolver=? where idPrestamo=?");
			stmt.setInt(1, di);	
			stmt.setInt(3, p.getIdPrestamo());
			stmt.setDate(2, sDate);
			stmt.executeUpdate();
		}
		catch (SQLException e) 
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
	
	
	public void delete(Prestamo p) 
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("delete from prestamos where idPrestamo=?");
			stmt.setInt(1, p.getIdPrestamo());
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
	
	public Prestamo getOne(int id)
	{
		Prestamo p=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from prestamos where idPrestamo=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					p=new Prestamo();
					p.setDiasPrestamo(rs.getInt("diasPrestamo"));
					p.setFechaADevolver(rs.getDate("fechaADevolver"));
					p.setFechaPrestamo(rs.getDate("fechaPrestamo"));
					p.setHoraPrestamo(rs.getTime("horaPrestamo"));
					p.setIdPrestamo(rs.getInt("idPrestamo"));
					Socio s=new Socio();
					s.setIdSocio(rs.getInt("idSocio"));
					p.setSocio(s);
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
