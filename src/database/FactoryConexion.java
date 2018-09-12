package database;
import java.sql.*;


public class FactoryConexion {
	
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String pass = "root";
	private String dbType = "mysql";
	private String db = "biblioteca";
	
	private Connection conn;
	private int cantConn = 0;
	
	FactoryConexion() {
		
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			System.out.println(e);

		}
		
	}
	
	private static FactoryConexion instancia;
	
	public static FactoryConexion getInstancia(){
		if (instancia == null){
			instancia = new FactoryConexion();
		}
		return instancia;
		}
	
	
	public Connection getConn() throws SQLException{
		
		if(conn==null || conn.isClosed()){
		
			conn = DriverManager.getConnection("jdbc:"+dbType+"://"+host+":"+port+"/"+db+"?&useSSL=false&serverTimezone=America/Argentina/Buenos_Aires",user,pass);
			
		}
		return conn;
	}
	
	public void releaseConn(){
		try{
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e){
			System.out.println(e);
				
		}
	}
		
}
	