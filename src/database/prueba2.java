package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import entidades.*;
public class prueba2 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException  {
		//Calendar cal=Calendar.getInstance();
		//java.util.Date d = new java.util.Date();
		//Date fechaActual = new java.sql.Date(d.getTime());
//		cal.setTime(fechaActual);
	//	System.out.println(cal.getTime());
	/*
		GregorianCalendar fecha = new GregorianCalendar();
		java.util.Date d = new java.util.Date();
		Date fechaActual = new java.sql.Date(d.getTime());
		System.out.println(fechaActual);
	*/
		
		System.out.println("prueba");
		if(getL("antonella")==null) {System.out.println("no hay");}
		else {System.out.println("hay");}

	/*	java.util.Date d = new java.util.Date();
		Date fechaPrestamo = new java.sql.Date(d.getTime());
		Time horaPrestamo=new java.sql.Time(d.getTime());
		System.out.println("hors:" + horaPrestamo);
		System.out.println("dia"+fechaPrestamo);	*/	
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
				l.setClave(rs.getString("clave"));
				System.out.println("id: " +l.getNombreUsuario());
				System.out.println("titulo" + l.getClave());
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
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
}
