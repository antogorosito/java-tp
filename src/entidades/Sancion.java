package entidades;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Sancion
{
	private int idSancion;
	private Date fechaSancion;
	private Date fechaSancionHasta;
	private boolean activo;
	private Socio socio;
	
	public Sancion(){}
	
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) 
	{
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	public Sancion(int d,Socio s) 
	{
		java.util.Date fecha=new java.util.Date();
		DateFormat Formato=new SimpleDateFormat("yyyy-MM-dd");
		String fechaAct=Formato.format(fecha);
		try 
		{
			this.fechaSancion=convertUtilToSql(Formato.parse(fechaAct));
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(this.fechaSancion);
		calendar.add(Calendar.DAY_OF_YEAR,d);
		java.sql.Date sDate = convertUtilToSql(calendar.getTime());
		
		this.fechaSancionHasta=sDate;
		this.activo=true;
		this.socio=s;
	}

	public int getIdSancion() 
	{
		return idSancion;
	}
	public void setIdSancion(int idSancion) 
	{
		this.idSancion = idSancion;
	}
	public Date getFechaSancion() 
	{
		return fechaSancion;
	}
	public void setFechaSancion(Date fechaSancion) 
	{
		this.fechaSancion = fechaSancion;
	}
	public void setFechaSancionHasta(Date fechaSancionHasta)
	{
		this.fechaSancionHasta=fechaSancionHasta;
	}
	public void setActivo(boolean activo)
	{
		this.activo=activo;
	}
	public Date getFechaSancionHasta()
	{
		return fechaSancionHasta;
	}
	public boolean getActivo()
	{
		return activo;
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
