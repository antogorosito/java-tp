package entidades;

import java.sql.Date;
import java.sql.Time;

public class Prestamo 
{
	private int idPrestamo;
	private Date fechaPrestamo;	
	private Time horaPrestamo;
	private int diasPrestamo;
	private Date fechaADevolver;
	private Socio socio;
	
	public int getIdPrestamo() 
	{
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) 
	{
		this.idPrestamo = idPrestamo;
	}
	public Date getFechaPrestamo() 
	{
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) 
	{
		this.fechaPrestamo = fechaPrestamo;
	}
	public Time getHoraPrestamo() 
	{
		return horaPrestamo;
	}
	public void setHoraPrestamo(Time horaPrestamo) 
	{
		this.horaPrestamo = horaPrestamo;
	}
	public int getDiasPrestamo() 
	{
		return diasPrestamo;
	}
	public void setDiasPrestamo(int diasPrestamo) 
	{
		this.diasPrestamo = diasPrestamo;
	}
	public Date getFechaADevolver() 
	{
		return fechaADevolver;
	}
	public void setFechaADevolver(Date fechaADevolver) 
	{
		this.fechaADevolver = fechaADevolver;
	}
	public Socio getSocio() 
	{
		return socio;
	}
	public void setSocio(Socio socio) 
	{
		this.socio = socio;
	} 
	
	
	
}
