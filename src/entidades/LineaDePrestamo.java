package entidades;

import java.sql.Date;

public class LineaDePrestamo 
{
	private int idLineaPrestamo; 
	private Date fechaDevolucion;
	private boolean devuelto;
	private Sancion sancion;
	private Socio socio;
	private Prestamo prestamo;
	private Ejemplar ejemplar;
	
	public LineaDePrestamo()
	{
		
	}
	public LineaDePrestamo(Socio s, Prestamo p, Ejemplar e)
	{
		this.socio=s;
		this.prestamo=p;
		this.ejemplar=e;
		this.devuelto=false;
	}
	public int getIdLineaPrestamo() 
	{
		return idLineaPrestamo;
	}
	public void setIdLineaPrestamo(int idLineaPrestamo) 
	{
		this.idLineaPrestamo = idLineaPrestamo;
	}
	public Date getFechaDevolucion() 
	{
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) 
	{
		this.fechaDevolucion = fechaDevolucion;
	}
	public boolean getDevuelto() 
	{
		return devuelto;
	}
	public void setDevuelto(boolean devuelto) 
	{
		this.devuelto = devuelto;
	}
	public Sancion getSancion() 
	{
		return sancion;
	}
	public void setSancion(Sancion sancion) 
	{
		this.sancion = sancion;
	}
	public Socio getSocio() 
	{
		return socio;
	}
	public void setSocio(Socio socio) 
	{
		this.socio = socio;
	}
	public Prestamo getPrestamo() 
	{
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) 
	{
		this.prestamo = prestamo;
	}
	public Ejemplar getEjemplar() 
	{
		return ejemplar;
	}
	public void setEjemplar(Ejemplar ejemplar) 
	{
		this.ejemplar = ejemplar;
	}
	
	
}
