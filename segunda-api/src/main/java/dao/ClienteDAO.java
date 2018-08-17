package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Cliente;
import erros.ClienteException;

public class ClienteDAO {
	
	private Connection con;
	
	public ClienteDAO(){
		this.con = ConnectionFactory.getConnection();
	}
	
	public int adicionarCliente(Cliente novoCliente){
		
		int retorno = -1;
		String sql = "insert into CLIENTE (PRIMNOME,SEGNOME,RUA,CIDADE) values (?,?,?,?)";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			stmt.setString(1, novoCliente.getPrimeiroNome());
			stmt.setString(2, novoCliente.getSegundoNome());
			stmt.setString(3, novoCliente.getEndereco());
			stmt.setString(4, novoCliente.getCidade());
			
			retorno = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage() + "\n Classe ClienteDAO \n Metodo adicionarCliente.");
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	
	public Cliente recuperarCliente(int id) throws ClienteException{
		
		Cliente retorno = null;
		String sql = "select * from CLIENTE where ID=?";
		
		try {
			PreparedStatement  stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next() == false){
				rs.close();
				stmt.close();
				String msg = "O ID informado não correspende a um usuario.";
				throw new ClienteException(msg);
			}
			
			retorno = new Cliente(id);
			retorno.setPrimeiroNome(rs.getString(2));
			retorno.setSegundoNome(rs.getString(3));
			retorno.setEndereco(rs.getString(4));
			retorno.setCidade(rs.getString(5));
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage() + "\n Classe CostumerDAO \n Metodo recuperarCustomer(id)");
			e.printStackTrace();
		}
		
		return retorno;	
	}
	
	public Cliente recuperarCliente(String primeiroNome, String segundoNome) throws ClienteException{
		
		Cliente retorno = null;
		String sql = "select * from CLIENTE where PRIMNOME=? and SEGNOME=?";
		
		try {
			PreparedStatement  stmt = this.con.prepareStatement(sql);
			stmt.setString(1, primeiroNome);
			stmt.setString(2, segundoNome);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next() == false){
				rs.close();
				stmt.close();
				String msg = "O Nome informado não correspende a um usuario.";
				throw new ClienteException(msg);
			}
			
			retorno = new Cliente(rs.getInt(1));
			retorno.setPrimeiroNome(rs.getString(2));
			retorno.setSegundoNome(rs.getString(3));
			retorno.setEndereco(rs.getString(4));
			retorno.setCidade(rs.getString(5));
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage() + "\n Classe CostumerDAO \n Metodo recuperarCustomer(nome)");
			e.printStackTrace();
		}
		
		return retorno;	
	}
	
	public int deletarCliente(int id) throws ClienteException{
		int retorno = -1;
		String sql = "delete from CLIENTE where ID=?";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			retorno = stmt.executeUpdate();
			if(retorno == 0){				
				stmt.close();
				String msg = "O ID informado não correspende a um usuario.";
				throw new ClienteException(msg);				
			}
		} catch (SQLException e) {
			System.out.println("SQLException \n" + e.getMessage() + "\n Classe CostumerDAO \n Metodo deletarCliente");
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public int deletarCliente(String primeiroNome, String segundoNome) throws ClienteException{
		int retorno = -1;
		String sql = "delete from CLIENTE where PRIMNOME=? and SEGNOME=?";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			stmt.setString(1, primeiroNome);
			stmt.setString(2, segundoNome);
			
			retorno = stmt.executeUpdate();
			if(retorno == 0){				
				stmt.close();
				String msg = "O Nome informado não correspende a um usuario.";
				throw new ClienteException(msg);				
			}
		} catch (SQLException e) {
			System.out.println("SQLException \n" + e.getMessage() + "\n Classe CostumerDAO \n Metodo deletarCliente");
			e.printStackTrace();
		}
		
		return retorno;
	}
	

	
/*
	public static void main(String[] args) throws ClienteException {
		
		ClienteDAO teste = new ClienteDAO();
		//System.out.println(teste.adicionarCliente(new Cliente("teste","teste","teste","teste")));
		//System.out.println(teste.recuperarCliente("teste","teste").toString());
		System.out.println(teste.deletarCliente("teste","teste"));
		
		
	}
*/
	

}
