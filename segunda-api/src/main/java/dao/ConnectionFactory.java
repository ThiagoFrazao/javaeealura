package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String url = "jdbc:hsqldb:file:C:/hsqldb/demo/estudo;hsqldb.write_delay=false;shutdown=true";


	public static Connection getConnection(){
		
		Connection con = null;
	    try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			con = DriverManager.getConnection(url, "sa","");
			
			} catch (ClassNotFoundException e) {	
				System.out.println("ClassNotFoundException " + e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {	
				System.out.println("SQLException " + e.getMessage());
				e.printStackTrace();
			}	
	    return con;
	}
	
/*	public static void main(String[] args) {
		
		ConnectionFactory teste = new ConnectionFactory();
		
		Statement stmt = null;
		ResultSet res = null;
		
		String sql = "select * from customer";

	    try {
			stmt = teste.getConnection().createStatement();
			
			 stmt.executeQuery(sql);
			 res = stmt.getResultSet();
			 while(res.next()){
				 System.out.println(res.getString("lastname"));
			 }
				
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
	   
		
	}
*/
}
