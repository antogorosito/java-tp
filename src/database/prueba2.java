package database;

import java.sql.*;

import entidades.*;
public class prueba2 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException  {
		System.out.println("prueba");
		if(getL("antonella")==null) {System.out.println("no hay");}
		else {System.out.println("hay");}
		
	}
	
	public static Usuario getL(String i) throws SQLException, ClassNotFoundException 
	{
		
		Usuario l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		String queryString= "SELECT * FROM usuarios WHERE nombreUsuario=?;";
		System.out.println("prueba3");
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(queryString);
			System.out.println("prueba2");
			stmt.setString(1,i);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) 	
			{
				l=new Usuario();
				l.setNombreUsuario(rs.getString("nombreUsuario"));
				l.setContraseña(rs.getString("contraseña"));
				System.out.println("id: " +l.getNombreUsuario());
				System.out.println("titulo" + l.getContraseña());
			}
			else 
			{
				System.out.println("la consulta no devuelve nada");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			
				try {
					
					stmt.close();
					rs.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			
		
			 
		}
		return l;
	
	}
}
