package database;

import entidades.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DataLineaDePrestamo 
{
	public boolean getOne(int socio, int ejem)//  veo si ya tengo otros ejemplares de mismo libro
	{
		boolean rta=false;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(" select *  from lineas_de_prestamos where fechaDevolucion is null and devuelto=false and idSocio=? and idEjemplar in(select idEjemplar from ejemplares where idLibro in (select idLibro from ejemplares where idEjemplar=?))");
			stmt.setInt(1,socio);
			stmt.setInt(2, ejem);
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
		ResultSet keyResultSet=null;
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("insert into lineas_de_prestamos(fechaDevolucion,devuelto,idSancion,idSocio,idPrestamo,idEjemplar) values(null,?,null,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setBoolean(1, lp.getDevuelto());
			stmt.setInt(2, lp.getSocio().getIdSocio());
			stmt.setInt(3, lp.getPrestamo().getIdPrestamo());
			stmt.setInt(4, lp.getEjemplar().getIdEjemplar());
			stmt.execute();		
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next())
			{
				lp.setIdLineaPrestamo(keyResultSet.getInt(1));
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
				stmt.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	public boolean buscarLinea(LineaDePrestamo lp) //me fijo si la linea de prestamo ya existe
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
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return rta;
	}
	public ArrayList<LineaDePrestamo> getAll(Prestamo p) //obtener todas las lineas de un prestamo
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
	
	public int minimoDias(Prestamo p) //obtener el tope de dias que se puede sacar un prestamo( se toma el minimo)
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
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return dias;
	}
	
	
	public LineaDePrestamo getOne(int id)//me fijo si esta pendiente de devolucion
	{
		LineaDePrestamo lp=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select idLineaPrestamo,socios.idSocio,socios.apellido,socios.nombre,ejemplares.idEjemplar,prestamos.idPrestamo,fechaPrestamo,diasPrestamo,fechaADevolver,titulo  from lineas_de_prestamos inner join prestamos on prestamos.idPrestamo=lineas_de_prestamos.idPrestamo inner join ejemplares on lineas_de_prestamos.idEjemplar=ejemplares.idEjemplar inner join libros on libros.idLibro=ejemplares.idLibro inner join socios on socios.idSocio=lineas_de_prestamos.idSocio where fechaDevolucion is null and devuelto=false and lineas_de_prestamos.idEjemplar=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					lp=new LineaDePrestamo();			
					lp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
					Ejemplar ej=new Ejemplar();
					ej.setIdEjemplar(rs.getInt("ejemplares.idEjemplar"));
					Libro l=new Libro();
					l.setTitulo(rs.getString("titulo"));
					ej.setLibro(l);
					lp.setEjemplar(ej);
					Socio so=new Socio();
					so.setIdSocio(rs.getInt("socios.idSocio"));
					so.setApellido(rs.getString("socios.apellido"));
					so.setNombre(rs.getString("socios.nombre"));
					lp.setSocio(so);
					Prestamo pr=new Prestamo();
					pr.setIdPrestamo(rs.getInt("prestamos.idPrestamo"));
					pr.setDiasPrestamo(rs.getInt("diasPrestamo"));
					pr.setFechaADevolver(rs.getDate("fechaADevolver"));
					pr.setFechaPrestamo(rs.getDate("fechaPrestamo"));
					lp.setPrestamo(pr);
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
				stmt.close();
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return lp;
	}
	public void update(LineaDePrestamo lp,Sancion s) throws ParseException 
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("update lineas_de_prestamos set fechaDevolucion=?,devuelto=?, idSancion=? where idEjemplar=?");
			java.util.Date fecha = new  java.util.Date();
			DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
			String fechaActu=Formato.format(fecha);
			java.sql.Date sDate=convertUtilToSql(Formato.parse(fechaActu));
			stmt.setDate(1,sDate);	
			stmt.setBoolean(2, true);
			if (s ==null)
			{
				stmt.setInt(3, 0);
			}
			else
			{
				stmt.setInt(3, s.getIdSancion());
			}
			stmt.setInt(4, lp.getEjemplar().getIdEjemplar());
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
				stmt.close();				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	public void update(LineaDePrestamo lp) throws ParseException 
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("update lineas_de_prestamos set fechaDevolucion=?,devuelto=? where idEjemplar=?");
			java.util.Date fecha = new  java.util.Date();
			DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
			String fechaActu=Formato.format(fecha);
			java.sql.Date sDate=convertUtilToSql(Formato.parse(fechaActu));
			stmt.setDate(1,sDate);	
			stmt.setBoolean(2, true);
			stmt.setInt(3,lp.getEjemplar().getIdEjemplar());
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
				stmt.close();				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate)
	{
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	public void delete(LineaDePrestamo lp) 
	{
		PreparedStatement stmt=null;
		try 
		{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("delete from lineas_de_prestamos where idLineaPrestamo=?");
			stmt.setInt(1, lp.getIdLineaPrestamo());
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
				stmt.close();				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
	}
	
	public ArrayList<LineaDePrestamo> getAll(int id)//obtener todas las lineas pendientes de un socio
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<LineaDePrestamo> lineas=new ArrayList<LineaDePrestamo>();
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select idLineaPrestamo,socios.idSocio,socios.apellido,socios.nombre,ejemplares.idEjemplar,prestamos.idPrestamo,fechaPrestamo,diasPrestamo,fechaADevolver,titulo  from lineas_de_prestamos inner join prestamos on prestamos.idPrestamo=lineas_de_prestamos.idPrestamo inner join ejemplares on lineas_de_prestamos.idEjemplar=ejemplares.idEjemplar inner join libros on libros.idLibro=ejemplares.idLibro inner join socios on socios.idSocio=lineas_de_prestamos.idSocio where fechaDevolucion is null and devuelto=false and socios.idSocio=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					LineaDePrestamo lp=new LineaDePrestamo();
					lp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
					Ejemplar ej=new Ejemplar();
					ej.setIdEjemplar(rs.getInt("ejemplares.idEjemplar"));
					Libro l=new Libro();
					l.setTitulo(rs.getString("titulo"));
					ej.setLibro(l);
					lp.setEjemplar(ej);
					Socio so=new Socio();
					so.setIdSocio(rs.getInt("socios.idSocio"));
					so.setApellido(rs.getString("socios.apellido"));
					so.setNombre(rs.getString("socios.nombre"));
					lp.setSocio(so);
					Prestamo pr=new Prestamo();
					pr.setIdPrestamo(rs.getInt("prestamos.idPrestamo"));
					pr.setDiasPrestamo(rs.getInt("diasPrestamo"));
					pr.setFechaADevolver(rs.getDate("fechaADevolver"));			
					pr.setFechaPrestamo(rs.getDate("fechaPrestamo"));				
					lp.setPrestamo(pr);
					lineas.add(lp);
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
	public LineaDePrestamo obtener(int id) //obtener toda la info sobre las lineas seleccionadas (checkbox)
	{
		LineaDePrestamo lp=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		try 
		{
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select idLineaPrestamo,socios.idSocio,socios.apellido,socios.nombre,ejemplares.idEjemplar,prestamos.idPrestamo,fechaPrestamo,diasPrestamo,fechaADevolver,titulo  from lineas_de_prestamos inner join prestamos on prestamos.idPrestamo=lineas_de_prestamos.idPrestamo inner join ejemplares on lineas_de_prestamos.idEjemplar=ejemplares.idEjemplar inner join libros on libros.idLibro=ejemplares.idLibro inner join socios on socios.idSocio=lineas_de_prestamos.idSocio where lineas_de_prestamos.idLineaPrestamo=?");
			stmt.setInt(1,id);
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					lp=new LineaDePrestamo();
					lp.setIdLineaPrestamo(rs.getInt("idLineaPrestamo"));
					Ejemplar ej=new Ejemplar();
					ej.setIdEjemplar(rs.getInt("ejemplares.idEjemplar"));
					Libro l=new Libro();
					l.setTitulo(rs.getString("titulo"));
					ej.setLibro(l);
					lp.setEjemplar(ej);
					Socio so=new Socio();
					so.setIdSocio(rs.getInt("socios.idSocio"));
					so.setApellido(rs.getString("socios.apellido"));
					so.setNombre(rs.getString("socios.nombre"));
					lp.setSocio(so);
					Prestamo pr=new Prestamo();
					pr.setIdPrestamo(rs.getInt("prestamos.idPrestamo"));
					pr.setDiasPrestamo(rs.getInt("diasPrestamo"));
					pr.setFechaADevolver(rs.getDate("fechaADevolver"));
					pr.setFechaPrestamo(rs.getDate("fechaPrestamo"));
					lp.setPrestamo(pr);
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
				stmt.close();
				rs.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			} 
		}
		return lp;
	}
	public ArrayList<LineaDePrestamo> getAllPendiente()
	{
		PreparedStatement stmt=null;
		ResultSet rs= null;
		ArrayList<LineaDePrestamo> lineas=new ArrayList<LineaDePrestamo>();
		try 
		{	
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("select prestamos.idPrestamo,titulo,ejemplares.idEjemplar,apellido,nombre,socios.idSocio,fechaADevolver  from lineas_de_prestamos inner join ejemplares on ejemplares.idEjemplar=lineas_de_prestamos.idEjemplar inner join libros on libros.idLibro=ejemplares.idLibro inner join prestamos on prestamos.idPrestamo=lineas_de_prestamos.idPrestamo inner join socios on socios.idSocio=lineas_de_prestamos.idSocio  where fechaDevolucion is null and devuelto=false");
			rs=stmt.executeQuery();
			if(rs!=null) 	
			{
				while(rs.next())
				{
					LineaDePrestamo lp=new LineaDePrestamo();
					Ejemplar ej=new Ejemplar();
					ej.setIdEjemplar(rs.getInt("ejemplares.idEjemplar"));
					Libro l=new Libro();
					l.setTitulo(rs.getString("titulo"));
					ej.setLibro(l);
					lp.setEjemplar(ej);
					Socio so=new Socio();
					so.setIdSocio(rs.getInt("socios.idSocio"));
					so.setApellido(rs.getString("apellido"));
					so.setNombre(rs.getString("nombre"));
					lp.setSocio(so);
					Prestamo pr=new Prestamo();
					pr.setIdPrestamo(rs.getInt("prestamos.idPrestamo"));
					pr.setFechaADevolver(rs.getDate("fechaADevolver"));							
					lp.setPrestamo(pr);
					lineas.add(lp);
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
}
