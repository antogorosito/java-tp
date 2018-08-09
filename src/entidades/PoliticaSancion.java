package entidades;

import java.sql.Date;

public class PoliticaSancion 
{
	private int idPoliticaSancion;
	private Date diasDeAtrasoDesde;
	private Date diasDeAtrasoHasta;
	private Date diasDeSancion;
	private PoliticaPrestamo politica;
	
	public int getIdPoliticaSancion() 
	{
		return idPoliticaSancion;
	}
	public void setIdPoliticaSancion(int idPoliticaSancion) 
	{
		this.idPoliticaSancion = idPoliticaSancion;
	}
	public Date getDiasDeAtrasoDesde() 
	{
		return diasDeAtrasoDesde;
	}
	public void setDiasDeAtrasoDesde(Date diasDeAtrasoDesde) 
	{
		this.diasDeAtrasoDesde = diasDeAtrasoDesde;
	}
	public Date getDiasDeAtrasoHasta() 
	{
		return diasDeAtrasoHasta;
	}
	public void setDiasDeAtrasoHasta(Date diasDeAtrasoHasta) 
	{
		this.diasDeAtrasoHasta = diasDeAtrasoHasta;
	}
	public Date getDiasDeSancion() 
	{
		return diasDeSancion;
	}
	public void setDiasDeSancion(Date diasDeSancion) 
	{
		this.diasDeSancion = diasDeSancion;
	}
	public PoliticaPrestamo getPolitica() 
	{
		return politica;
	}
	public void setPolitica(PoliticaPrestamo politica) 
	{
		this.politica = politica;
	} 
	
	
}
