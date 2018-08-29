package entidades;

public class Socio 
{
	private int idSocio;
	private String apellido;
	private String nombre;
	private String email;
	private String domicilio;
	private String telefono;
	private String dni;
	private boolean estado;
	
	
	public Socio() 
	{
		
	}
	
	public Socio(String a,String n,String e,String d,String t,String dni)
	{
		this.apellido=a;
		this.nombre=n;
		this.email=e;
		this.domicilio=d;
		this.telefono=t;
		this.dni=dni;
		this.estado=true; 
		
	}
	public int getIdSocio() 
	{
		return idSocio;
	}
	public void setIdSocio(int idSocio) 
	{
		this.idSocio = idSocio;
	}
	public String getApellido() 
	{
		return apellido;
	}
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getDomicilio() 
	{
		return domicilio;
	}
	public void setDomicilio(String domicilio) 
	{
		this.domicilio = domicilio;
	}
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	public String getDni() 
	{
		return dni;
	}
	public void setDni(String dni) 
	{
		this.dni = dni;
	}
	public boolean getEstado() 
	{
		return estado;
	}
	public void setEstado(boolean estado) 
	{
		this.estado = estado;
	} 
	
	
}
