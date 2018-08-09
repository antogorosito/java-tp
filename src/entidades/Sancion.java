package entidades;

import java.sql.Date;

public class Sancion
{
	private int idSancion;
	private Date fechaSancion;
	private int diasSancion; 
	private Socio socio;
	
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
	public int getDiasSancion() 
	{
		return diasSancion;
	}
	public void setDiasSancion(int diasSancion) 
	{
		this.diasSancion = diasSancion;
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
