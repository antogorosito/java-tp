package database;

import java.sql.*;

import entidades.*;

public class dataPrueba {

	public static void main(String[] args) throws SQLException {
		
		getL(1);		
	}
	
	public static void getL(int i) throws SQLException
	{
		Libro l=null;
		PreparedStatement stmt=null;
		ResultSet rs= null; 
		
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement("select idLibro,titulo from libros where idLibro= ?");
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
			
		}
		finally 
		{
			if(rs!=null)
			{
				try {
					rs.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		
			 
		}
		FactoryConexion.getInstancia().releaseConn();
	
	}

}
