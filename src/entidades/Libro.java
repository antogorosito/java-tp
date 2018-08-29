package entidades;

import java.sql.Date;

public class Libro 
{
	private int idLibro;
	private String isbn;
	private String titulo;
	private int nroEdicion;
	private Date fechaEdicion; 
	private int cantDiasMaxPrestamo;
	
	
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
