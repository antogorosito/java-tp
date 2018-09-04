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
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into lineas_de_prestamos(fechaDevolucion,devuelto,idSancion,idSocio,idPrestamo,idEjemplar) values(null,?,null,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
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
	public ArrayList<LineaDePrestamo> getAll(Prestamo p) 
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<LineaDePrestamo> lineas=new ArrayList<LineaDePrestamo>();
		try 
		{
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select lineas_de_prestamos.idLineaPrestamo,lineas_de_prestamos.idEjemplar,libros.titulo,libros.cantDiasMaxPrestamo from lineas_de_prestamos inner join ejemplares on ejemplares.idEjemplar=lineas_de_prestamos.idEjemplar inner join libros on libros.idLibro=ejemplares.idLibro where lineas_de_prestamos.idPrestamo=?");
			stmt.setInt(1,p.getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					LineaDePrestamo l=new LineaDePrestamo();
					l.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
					Ejemplar e=new Ejemplar();
					e.setIdEjemplar(rs.getInt("idEjemplar"));
					Libro li =new Libro();
					li.setTitulo(rs.getString("titulo"));
					li.setCantDiasMaxPrestamo(rs.getInt("cantDiasMaxPrestamo"));
					e.setLibro(li);
					l.setEjemplar(e);
					lineas.add(l);	
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
		return lineas;
	}
	
	public int minimoDias(Prestamo p)
	{
		int dias=0;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select min(libros.cantDiasMaxPrestamo) as minimo from lineas_de_prestamos inner join ejemplares on ejemplares.idEjemplar=lineas_de_prestamos.idEjemplar inner join libros on libros.idLibro=ejemplares.idLibro where lineas_de_prestamos.idPrestamo=? group by lineas_de_prestamos.idPrestamo");
			stmt.setInt(1,p.getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					dias=rs.getInt("minimo");
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
		return dias;
	}
	
	public LineaDePrestamo getOne(LineaDePrestamo lp)
	{
		LineaDePrestamo ldp=new LineaDePrestamo();
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select * from lineas_de_prestamos where idSocio=? and idEjemplar=? and idPrestamo=?");
			stmt.setInt(1, lp.getSocio().getIdSocio());
			stmt.setInt(2,lp.getEjemplar().getIdEjemplar());
			stmt.setInt(3, lp.getPrestamo().getIdPrestamo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next() ) 	
			{
				ldp.setDevuelto(rs.getBoolean("devuelto"));
				ldp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
				ldp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
				Ejemplar e= new Ejemplar();
				e.setIdEjemplar(rs.getInt("idEjemplar"));
				ldp.setEjemplar(e);
				Socio s=new Socio();
				s.setIdSocio(rs.getInt("idSocio"));
				ldp.setSocio(s);
				Prestamo p=new Prestamo();
				p.setIdPrestamo(rs.getInt("idPrestamo"));
				ldp.setPrestamo(p);
			}
			
		} catch (SQLException e) {
		
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
		return ldp;
	}
/*	public void delete(LineaDePrestamo lp) {}
	
	public void update(LineaDePrestamo lp) {}
	
    public LineaDePrestamo getOne(LineaDePrestamo lp) { }
	
	*/
}
