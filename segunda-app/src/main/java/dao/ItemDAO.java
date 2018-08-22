package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import entidades.Item;
import entidades.Produto;
import entidades.Venda;
import erros.ItensException;

@Dependent
public class ItemDAO {
	
	@Inject
	private ConexaoDAO dao;	
	
	@Inject
	private VendaDAO vendas;
	
	@Inject
	private ProdutoDAO produtos;
	
	public int adicionarItem(Item novoItem) throws ItensException{
		int retorno = -1;
		String sql = "insert into ITENS (VENDAID,PRODUTOID,ITEM,QUANTIDADE,CUSTO) value (?,?,?,?,?)";
		
		if(this.vendas.verificarExistencia(novoItem.getVenda().getId()) && this.produtos.verificarExistenciaProduto(novoItem.getProduto().getId())){
			try {
				Connection con = dao.getConexao();
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setInt(1, novoItem.getVenda().getId());
				stmt.setInt(2, novoItem.getProduto().getId());
				stmt.setInt(3, novoItem.getCodItem());
				stmt.setInt(4, novoItem.getQuantidade());
				stmt.setInt(5, novoItem.getCusto());
				
				retorno = stmt.executeUpdate();
				stmt.close();			
				
				
			} catch (SQLException e) {
				System.out.println("SQLException " + e.getMessage());
				e.printStackTrace();
			}
		}else{
			String msg = "Item não possui produto e venda associados. Todo novo item deve ter um produto e venda associados.";
			throw new ItensException(msg);
		}
		
		
		return retorno;
	}
	
	public int removerVendaItens(int vendaID) throws ItensException{
		int retorno = -1;
		String sql = "delete from ITENS where VENDAID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, vendaID);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "Não há itens cadastrados na Venda informada.";
				throw new ItensException(msg);
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public int removerProdutosItens(int produtoID) throws ItensException{
		int retorno = -1;
		String sql = "delete from ITENS where PRODUTOID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, produtoID);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "Não há itens cadastrados na Venda informada.";
				throw new ItensException(msg);
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public int removerItens(int codItem) throws ItensException{
		int retorno = -1;
		String sql = "delete from ITENS where ITEM=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, codItem);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "Não há itens cadastrados na Venda informada.";
				throw new ItensException(msg);
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public int atualizarItem(Venda venda, Produto produto, int item, int quantidade, int custo) throws ItensException{
		
		int retorno = -1;
		String sql = "update ITENS set PRODUTOID=?,ITEM=?,QUANTIDADE=?,CUSTO=? where VENDAID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			stmt.setInt(2, item);
			stmt.setInt(3, quantidade);
			stmt.setInt(4, custo);
			stmt.setInt(5, venda.getId());
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "O ID informado não corresponde a um Item cadastrado.";
				throw new ItensException(msg);
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;	
	}
	
	public int atualizarItem(Venda venda, int quantidade, int custo) throws ItensException{
		
		int retorno = -1;
		String sql = "update ITENS set QUANTIDADE=?,CUSTO=? where VENDAID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, quantidade);
			stmt.setInt(2, custo);
			stmt.setInt(3, venda.getId());
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "O ID informado não corresponde a um Item cadastrado.";
				throw new ItensException(msg);
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;	
	}
/*	
	public static void main(String[] args) throws ItensException {
		
		ItemDAO teste = new ItemDAO();
		Venda venda = new Venda(200);
		Produto produto = new Produto(200);
		
		Item novoItem = new Item(venda,produto,10,10,10);
		teste.adicionarItem(novoItem);
	}
*/
}
