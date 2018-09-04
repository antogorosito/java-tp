package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		return cant;
	
	}
	public void add(Prestamo p)
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into prestamos(fechaPrestamo,horaPrestamo,diasPrestamo,fechaADevolver,idSocio) values(?,?,null,null,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDate(1, p.getFechaPrestamo());
			stmt.setTime(2, p.getHoraPrestamo());
			stmt.setInt(3,p.getSocio().getIdSocio());
			stmt.execute();		
			
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
	public Prestamo getOne(Prestamo p)
	{
		Prestamo pp=new Prestamo();
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from  prestamos where fechaPrestamo=? and horaPrestamo=? and idSocio=?");
			stmt.setDate(1, p.getFechaPrestamo());
			stmt.setTime(2, p.getHoraPrestamo());
			stmt.setInt(3, p.getSocio().getIdSocio());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next() ) 	
			{
				pp.setIdPrestamo(rs.getInt("idPrestamo"));
				pp.setDiasPrestamo(rs.getInt("diasPrestamo"));
				pp.setFechaADevolver(rs.getDate("fechaADevolver"));
				pp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				pp.setHoraPrestamo(rs.getTime("horaPrestamo"));
				Socio s=new Socio();
				s.setIdSocio(rs.getInt("idSocio"));
				pp.setSocio(s);
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
		return pp;
	}
	public Prestamo getOne(Socio s)
	{
		Prestamo pp=new Prestamo();
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from  prestamos where fechaPrestamo=current_date() and idSocio=?");

			stmt.setInt(1,s.getIdSocio());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next() ) 	
			{
				pp.setIdPrestamo(rs.getInt("idPrestamo"));
				pp.setDiasPrestamo(rs.getInt("diasPrestamo"));
				pp.setFechaADevolver(rs.getDate("fechaADevolver"));
				pp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
				pp.setHoraPrestamo(rs.getTime("horaPrestamo"));
				Socio so=new Socio();
				so.setIdSocio(rs.getInt("idSocio"));
				pp.setSocio(so);
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
		return pp;
	}
	/*
	public void delete(Prestamo p) {}
	
	public void update(Prestamo p) {}
	
	
	
	public ArrayList<Prestamo> getAll() {}*/
}
