package controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.ItemDAO;
import entidades.Item;
import entidades.Produto;
import entidades.Venda;
import erros.ItensException;
import erros.ProdutoException;
import erros.VendaException;
import utils.Resposta;

@Path("/loja/item")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemController {
	
	@Inject
	private ItemDAO itemDAO;
	
	@GET
	public Response testeFuncionando(){
		return Resposta.respostaSucesso("Servidor funcionando corretamente.");	
	}
	
	@GET
	@Path("/venda/{idVenda}")
	public Response procurarItensVenda(@PathParam("idVenda") int idVenda){
		Response retorno = null;
		try {
			List<Item> itens = itemDAO.procurarItensVenda(idVenda);
			retorno = Resposta.montarResposta(Status.OK, itens);
		} catch (VendaException | ProdutoException | ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@GET
	@Path("/produto/{idProduto}")
	public Response procurarItensProduto(@PathParam("idProduto") int idProduto){
		Response retorno = null;
		try {
			List<Item> itens = itemDAO.procurarItensProduto(idProduto);
			retorno = Resposta.montarResposta(Status.OK, itens);
		} catch (VendaException | ProdutoException | ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@GET
	@Path("/{codItem}")
	public Response procurarItem(@PathParam("codItem") int codItem){
		Response retorno = null;
		try {
			Item item = itemDAO.procurarItem(codItem);
			retorno = Resposta.montarResposta(Status.OK, item);
		} catch (VendaException | ProdutoException | ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@GET
	@Path("/listar")
	public Response listarTodosItens(){
		Response retorno = null;
		try {
			List<Item> todosItens = itemDAO.listarTodosItens();
			retorno = Resposta.montarResposta(Status.OK, todosItens);
		} catch (VendaException | ProdutoException | ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@POST
	@Path("/add")
	public Response adicionarItem(Item novoItem){
		Response retorno = null;
		
		try {
			itemDAO.adicionarItem(novoItem);
			retorno = Resposta.respostaSucesso();
		} catch (ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	
	@POST
	@Path("/update")
	public Response atualizarItem(Item novoItem){
		Response retorno = null;
		
		Venda venda = novoItem.getVenda();
		Produto produto = novoItem.getProduto();
		int codItem = novoItem.getCodItem();
		int quantidade = novoItem.getQuantidade();
		int custo = novoItem.getCusto();
		
		try {
			if(novoItem.validarItem()){				
				itemDAO.atualizarItem(venda, produto, codItem, quantidade, custo);
				retorno = Resposta.respostaSucesso();
			}
			else if(novoItem.validarItem(1)){
				itemDAO.atualizarItem(venda, quantidade, custo);
				retorno = Resposta.respostaSucesso();
			}
			else{
				String msg = "Dados insuficientes para a atualizacao.";
				retorno = Resposta.respostaErro(msg);
			} 
		} catch (ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@DELETE
	@Path("/delete/venda/{idVenda}")
	public Response removerItensVenda(@PathParam("idVenda") int idVenda){
		Response retorno = null;
		
		try {
			itemDAO.removerVendaItens(idVenda);
			retorno = Resposta.respostaSucesso();
		} catch (ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	
	@DELETE
	@Path("/delete/produto/{idProduto}")
	public Response removerItensProduto(@PathParam("idProduto") int idProduto){
		Response retorno = null;
		
		try {
			itemDAO.removerProdutosItens(idProduto);
			retorno = Resposta.respostaSucesso();
		} catch (ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	
	@DELETE
	@Path("/delete/{codItem}")
	public Response removerItens(@PathParam("codItem") int codItem){
		Response retorno = null;
		
		try {
			itemDAO.removerItens(codItem);
			retorno = Resposta.respostaSucesso();
		} catch (ItensException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	

}
