package entidades;

public class Usuario
{
	private String nombreUsuario;
	private String clave;
	private int tipo;
	
	public Usuario() {}
	public Usuario(String dni, String a,int t)
	{
		this.nombreUsuario=dni;
		this.clave=a;
		this.tipo=t; 
	}
	public String getNombreUsuario()  
	{
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) 
	{
		this.nombreUsuario = nombreUsuario;
	}
	public String getClave() 
	{
		return clave;
	}
	public void setClave(String clave) 
	{
		this.clave = clave;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	} 
	
	
}
