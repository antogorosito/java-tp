package entidades;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Prestamo 
{
	private int idPrestamo;
	private Date fechaPrestamo;	
	private Time horaPrestamo;
	private int diasPrestamo;
	private Date fechaADevolver;
	private Socio socio;
	
	public Prestamo()
	{
		
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate)
	{
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	private static java.sql.Time convertUtil2ToSql(java.util.Date uTime)
	{
		java.sql.Time sDate = new java.sql.Time(uTime.getTime());
		return sDate;
	}
	public Prestamo(Socio s) 
	{
		this.socio=s;
		java.util.Date fecha = new  java.util.Date();
		DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat Formato1 = new SimpleDateFormat("HH:mm:ss");
		String fechaActu=Formato.format(fecha);
		String horaActu=Formato1.format(fecha);
		try 
		{
			this.fechaPrestamo=convertUtilToSql(Formato.parse(fechaActu));
			this.horaPrestamo=convertUtil2ToSql(Formato1.parse(horaActu));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}	
	}
	
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
