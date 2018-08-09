package entidades;

import java.sql.Date;

public class LineaDePrestamo 
{
	private int idLineaPrestamo; 
	private Date fechaDevolucion;
	private boolean devuelvo;
	private Sancion sancion;
	private Socio socio;
	private Prestamo prestamo;
	private Ejemplar ejemplar;
	
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
	public boolean isDevuelvo() 
	{
		return devuelvo;
	}
	public void setDevuelvo(boolean devuelvo) 
	{
		this.devuelvo = devuelvo;
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
