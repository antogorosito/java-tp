package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataLineaDePrestamo 
{
	public boolean getOne(int socio, int ejem)
	{
		boolean rta=false;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(" select idEjemplar from ejemplares where idLibro in ( select idLibro from ejemplares where ejemplares.idEjemplar in (select idEjemplar from lineas_de_prestamos where fechaDevolucion is null and devuelto=false and idEjemplar=? and idSocio=?))");
			stmt.setInt(1,ejem);
			stmt.setInt(2, socio);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					rta=true;
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
		return rta;
	}
	public void add(LineaDePrestamo lp)
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into lineas_de_prestamos(fechaDevolucion,devuelto,idSancion,idSocio,idPrestamo,idEjemplar) values(null,?,null.?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setBoolean(1, lp.getDevuelto());
			stmt.setInt(2, lp.getSocio().getIdSocio());
			stmt.setInt(3, lp.getPrestamo().getIdPrestamo());
			stmt.setInt(4, lp.getEjemplar().getIdEjemplar());
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
	public boolean buscarLinea(LineaDePrestamo lp)
	{
		boolean rta=false;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from prestamos inner join lineas_de_prestamos on lineas_de_prestamos.idPrestamo=prestamos.idPrestamo where fechaPrestamo= current_date() and prestamos.idSocio=? and prestamos.idPrestamo=? and idEjemplar in (select idEjemplar from ejemplares where idLibro in (select idLibro  from ejemplares where idEjemplar =?))");
			stmt.setInt(1,lp.getSocio().getIdSocio());
			stmt.setInt(2, lp.getPrestamo().getIdPrestamo());
			stmt.setInt(3, lp.getEjemplar().getIdEjemplar());
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					rta=true;
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
		return rta;
	}
	
/*	public void delete(LineaDePrestamo lp) {}
	
	public void update(LineaDePrestamo lp) {}
	
    public LineaDePrestamo getOne(LineaDePrestamo lp) { }
	
	public ArrayList<LineaDePrestamo> getAll() {}*/
}
