package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Produto;
import erros.ProdutoException;

public class ProdutoDAO {
	
	private Connection con; 
	
	public ProdutoDAO(){
		this.con = ConnectionFactory.getConnection();
	}
	
	public int adicionarProduto(Produto novoProduto){
		
		String sql = "insert into PRODUTO (NOME,PRECO) values(?,?)";
		int retorno = -1;
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			stmt.setString(1, novoProduto.getNome());
			stmt.setFloat(2, novoProduto.getPreco());
			
			retorno = stmt.executeUpdate();
			stmt.close();			
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;		
	}
	
	public int removerProduto(int id) throws ProdutoException{
		
		int retorno = -1;
		String sql = "delete from PRODUTO where id=?";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "O ID informado não corresponde a um produto";
				throw new ProdutoException(msg);
			}
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}		
		return retorno;		
	}
	
	public int atualizarProduto(int id, String novoNome, float novoPreco) throws ProdutoException{
		
		int retorno = -1;
		String sql = "update PRODUTO set nome=?, preco=? where id=?";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			stmt.setString(1, novoNome);
			stmt.setFloat(2, novoPreco);
			stmt.setInt(3, id);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "O ID informado não corresponde a um produto";
				throw new ProdutoException(msg);
			}			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}		
		return retorno;		
	}
	
	public ArrayList<Produto> listarProdutos(){
		
		ArrayList<Produto> retorno = new ArrayList<Produto>();
		String sql = "select * from PRODUTO";
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
				
		return retorno;
		
	}

}
