package entidades;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Libro 
{
	private int idLibro;
	private String isbn;
	private String titulo;
	private int nroEdicion;
	private Date fechaEdicion; 
	private int cantDiasMaxPrestamo;
	
	public Libro() 
	{
		
	}
	private static java.sql.Date convertUtilToSql(java.util.Date uDate)
	{
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
	
	public Libro(String t, String i,int n,String f,int m) 
	{
		this.isbn=i;
		this.titulo=t;
		this.nroEdicion=n;
		DateFormat Formato=new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			this.fechaEdicion=convertUtilToSql(Formato.parse(f));
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		this.cantDiasMaxPrestamo=m;
	}
	public int getIdLibro() 
	{
		return idLibro;
	}
	public void setIdLibro(int idLibro) 
	{
		this.idLibro = idLibro;
	}
	public String getIsbn() 
	{
		return isbn;
	}
	public void setIsbn(String isbn) 
	{
		this.isbn = isbn;
	}
	public String getTitulo() 
	{
		return titulo;
	}
	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}
	public int getNroEdicion() 
	{
		return nroEdicion;
	}
	public void setNroEdicion(int nroEdicion) 
	{
		this.nroEdicion = nroEdicion;
	}
	public Date getFechaEdicion() 
	{
		return fechaEdicion;
	}
	public void setFechaEdicion(Date fechaEdicion) 
	{
		this.fechaEdicion = fechaEdicion;
	}
	public int getCantDiasMaxPrestamo() 
	{
		return cantDiasMaxPrestamo;
	}
	public void setCantDiasMaxPrestamo(int cantDiasMaxPrestamo) 
	{
		this.cantDiasMaxPrestamo = cantDiasMaxPrestamo;
	} 
	
	
}
