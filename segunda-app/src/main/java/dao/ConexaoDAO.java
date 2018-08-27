package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class ConexaoDAO {
	
	//private static final String url = "jdbc:hsqldb:file:C:/hsqldb/demo/estudo;hsqldb.write_delay=false;shutdown=true";
	
	@Resource(lookup="java:/jdbc/hsqldb")
	private DataSource ds;

	public Connection getConexao(){
		
		Connection con = null;
	    try {
	    	//DriverManager.getConnection("url","usuario","senha");
	    	con = ds.getConnection();
	    	
			} catch (SQLException e) {	
				System.out.println("SQLException " + e.getMessage());
				e.printStackTrace();
			}	
	    return con;
	}
	
	
	public static void main(String[] args) throws SQLException, NamingException {
		
		ConexaoDAO teste = new ConexaoDAO();
		String sql = "select * from cliente";

	    try {
			PreparedStatement stmt = teste.getConexao().prepareStatement(sql);
			
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 System.out.println(rs.getString("primnome"));
			 }
				
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	
}
