package database;

import java.sql.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entidades.*;
import negocio.CtrlLineaDePrestamo;
public class prueba2 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException  {
	
		
	/*	System.out.println("prueba");
		if(getL("antonella")==null) {System.out.println("no hay");}
		else {System.out.println("hay");}*/
	
		java.util.Date fecha = new  java.util.Date();
		System.out.println("fec util");
		System.out.println(fecha);
		 DateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
		 String fechaActu=Formato.format(fecha);
			System.out.println("fec actual");
			System.out.println(fechaActu);
			
			 CtrlLineaDePrestamo cl=new CtrlLineaDePrestamo();
			 int i=17;
			 LineaDePrestamo l=cl.obtener(i);
		
		String fechadev=Formato.format(l.getPrestamo().getFechaADevolver());

		System.out.println("fec dev");System.out.println(fechadev);
		int rta=fechaActu.compareTo(fechadev);
		System.out.println("comparacion");System.out.println(rta);
		
	
		
	/*    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date date1;
		try {
			date1 = sdf.parse(fechaActu);
		
		java.util.Date date2=sdf.parse(fechadev);
		System.out.println("actrual"); System.out.println(date1);
		System.out.println("dev");System.out.println(date2);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
	}
	
	public static Usuario getL(String i) throws SQLException, ClassNotFoundException 
	{
		
		Usuario l=null;
		PreparedStatement stmt=null;//
		
		ResultSet rs= null; // conj de resultado
		String queryString= "SELECT * FROM usuarios WHERE nombreUsuario=?;";
		System.out.println("prueba3");
		try {
			//stmt)=conn.cratestatement();
			//rs=stmt.executequery(...);
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(queryString);
			System.out.println("prueba2");
			stmt.setString(1,i);
			rs=stmt.executeQuery();//executequery espera un Select. excute solo acepta cualquiera
			if(rs!=null && rs.next()) 	// next se fija si hay un elemento siguiene. y si existe devuelve true y se para en el primer elemento.  y asi etc.
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
		/* try{if(rs!=null){rs.close();} if (stmt!=null)stmt.close() if(conn!=null && !!conn.isclosed())conn.close();}catch()...*/
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
