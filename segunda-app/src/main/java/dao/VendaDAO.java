package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import entidades.Cliente;
import entidades.Venda;
import erros.ClienteException;
import erros.VendaException;

@Dependent
public class VendaDAO {
	
	@Inject
	private ConexaoDAO dao;	
	
	@Inject
	private ClienteDAO clientes;
	
	public int adicionarVenda(Venda novaVenda) throws VendaException{
		
		int retorno = -1;
		String sql = "insert into VENDA (CLIENTEID,TOTAL) value(?,?)";
		
		if(clientes.verificarExistenciaCliente(novaVenda.getId())){
			
			try {
				Connection con = dao.getConexao();
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setInt(1, novaVenda.getCliente().getId());
				stmt.setFloat(2, novaVenda.getTotal());
				
				retorno = stmt.executeUpdate();				
				stmt.close();
				
			} catch (SQLException e) {
				System.out.println("SQLException " + e.getMessage());
				e.printStackTrace();
			}
		}else{
			String msg = "Toda Venda deve ter um Cliente cadastrado para ser realizada.";
			throw new VendaException(msg);
		}
		
		return retorno;
	}
	
	public int deletarVenda(int id) throws VendaException{
		int retorno = -1;
		String sql = "delete from VENDA where ID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "O ID informado não corresponde a uma Venda.";
				throw new VendaException(msg);
			}
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		
		return retorno;
	}
	
	public int deletarVendasCliente(int idCliente) throws VendaException{
		int retorno = -1;
		String sql = "delete from VENDA where CLIENTEID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			if(retorno == 0){
				String msg = "O Cliente informado não possui Vendas cadastradas.";
				throw new VendaException(msg);
			}
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}		
		
		return retorno;
	}
	
	
	public Venda procurarVenda(int id) throws VendaException{
		
		Venda retorno = null;
		Cliente cliente = null;
		String sql = "select * from VENDA where ID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			

			
			if(!rs.next()){
				rs.close();
				stmt.close();
				String msg = "O ID informado não corresponde a uma Venda.";
				throw new VendaException(msg);
			}
			
			retorno = new Venda(id);
			cliente = this.clientes.procurarCliente(rs.getInt(2));
			
			retorno.setCliente(cliente);
			retorno.setTotal(rs.getFloat(3));
			
			rs.close();
			stmt.close();		
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		} catch (ClienteException e) {
			System.out.println("ClienteException " + e.getMessage());
			e.printStackTrace();
		}	
		
		return retorno;		
	}
	
	public ArrayList<Venda> procurarVendasCliente(int idCliente) throws VendaException{
		
		Venda venda = null;
		Cliente cliente = null;
		ArrayList<Venda> retorno = new ArrayList<Venda>();
		String sql = "select * from VENDA where CLIENTEID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idCliente);
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()){
				rs.close();
				stmt.close();
				String msg = "O Cliente informado não possui Vendas cadastradas.";
				throw new VendaException(msg);
			}
			
			venda = new Venda(rs.getInt(1));
			cliente = this.clientes.procurarCliente(rs.getInt(2));
			
			venda.setCliente(cliente);
			venda.setTotal(rs.getFloat(3));
			retorno.add(venda);
			
			while(rs.next()){				
				venda = new Venda(rs.getInt(1));
				cliente = this.clientes.procurarCliente(rs.getInt(2));
				
				venda.setCliente(cliente);
				venda.setTotal(rs.getFloat(3));		
				retorno.add(venda);
			}
			
			rs.close();
			stmt.close();		
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		} catch (ClienteException e) {
			System.out.println("ClienteException " + e.getMessage());
			e.printStackTrace();
		}	
		
		return retorno;		
	}
	
	public int atualizarVenda(int id, float total) throws VendaException{
		int retorno = -1;
		String sql = "update PRODUTO set TOTAL=? where ID=?";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setFloat(1, total);
			stmt.setInt(2, id);
			
			retorno = stmt.executeUpdate();
			stmt.close();
			
			if(retorno == 0){
				String msg = "O ID informado não corresponde a uma Venda.";
				throw new VendaException(msg);				
			}
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}		
		return retorno;
	}
	
	public boolean verificarExistencia(int id){
		boolean retorno = false;
		String sql = "select ID from VENDA where ID=? ";
		
		try {
			Connection con = dao.getConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			retorno = rs.next();
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException " + e.getMessage());
			e.printStackTrace();
		}
		
		return retorno;
	}
/*	
	public static void main(String[] args) throws VendaException {
		VendaDAO vendas = new VendaDAO();
		
		vendas.procurarVendasCliente(1).forEach(i -> System.out.println(i.toString()));
		
	}
*/


}
