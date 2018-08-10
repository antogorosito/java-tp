package database;

import java.sql.*;

import entidades.*;

public class dataPrueba {

	public static void main(String[] args) throws SQLException, ClassNotFoundException  {
		System.out.println("prueba");
		if(getL(1)==null) {System.out.println("no hay");}
		else {System.out.println("hay");}
		
	}
	
	public static Libro getL(int i) throws SQLException, ClassNotFoundException 
	{
		
		Libro l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		String queryString= "SELECT idLibro,titulo FROM libros WHERE idLibro=?;";
		System.out.println("prueba3");
		try {
			
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(queryString);
			System.out.println("prueba2");
			stmt.setInt(1,i);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) 	
			{
				l = new Libro();
				l.setIdLibro(rs.getInt("idLibro"));
				l.setTitulo(rs.getString("titulo"));
				System.out.println("id: " +l.getIdLibro());
				System.out.println("titulo" + l.getTitulo());
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
