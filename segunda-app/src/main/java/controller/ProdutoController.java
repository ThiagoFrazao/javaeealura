package controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.ProdutoDAO;
import entidades.Produto;
import erros.ProdutoException;
import utils.Resposta;

@Path("/loja/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {
	
	@Inject
	private ProdutoDAO produtoDAO;
	
	@GET
	public Response testandoServidor(){
		return Resposta.respostaSucesso("Servidor funcionando corretamente");
	}	
	
	@GET
	@Path("/find/{id}")
	public Response procurarProdutoNome(@PathParam("id")int id){
		Response retorno = null;		
		try {
			Produto produtos = produtoDAO.procurarProduto(id);
			retorno = Resposta.montarResposta(Status.OK, produtos);
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
				
		return retorno;
	}
	
	@GET
	@Path("/find/maior/{preco}")
	public Response procurarProdutoPrecoMaior(@PathParam("preco") float preco){
		Response retorno = null;
		try {
			ArrayList<Produto> produtos = produtoDAO.procurarProdutoMaior(preco);
			retorno = Resposta.montarResposta(Status.OK, produtos);
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("/find/menor/{preco}")
	public Response procurarProdutoPrecoMenor(@PathParam("preco") float preco){
		Response retorno = null;
		try {
			ArrayList<Produto> produtos = produtoDAO.procurarProdutoMenor(preco);
			retorno = Resposta.montarResposta(Status.OK, produtos);
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("/find/faixa")
	public Response procurarProdutoPrecoFaixa(@QueryParam("menor") float menor, @QueryParam("maior") float maior){
		Response retorno = null;
		try {
			ArrayList<Produto> produtos = produtoDAO.procurarProdutoFaixa(menor, maior);
			retorno = Resposta.montarResposta(Status.OK, produtos);
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
			e.printStackTrace();
		}
		return retorno;
	}
	
	@GET
	@Path("/listar")
	public Response procurarProdutoNome(){
		Response retorno = null;		
		try {
			ArrayList<Produto> produtos = produtoDAO.listarProdutos();
			retorno = Resposta.montarResposta(Status.OK, produtos);
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
				
		return retorno;
	}
	
	@POST
	@Path("/add")
	public Response adicionarProdutoNovo(Produto novoProduto){
		Response retorno = null;
		
		try {
			produtoDAO.adicionarProduto(novoProduto);
			retorno = Resposta.respostaSucesso();
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	
	@POST
	@Path("/update")
	public Response atualizarProduto(Produto novoProduto){
		Response retorno = null;
		
		if(novoProduto.verificarValidade()){
			try {
				produtoDAO.atualizarProduto(novoProduto.getId(), novoProduto.getNome(), novoProduto.getPreco());
				retorno = Resposta.respostaSucesso();
			} catch (ProdutoException e) {
				retorno = Resposta.respostaErro(e.getMessage());
			}
		}else{
			retorno = Resposta.respostaErro("Informações insuficientes ");
		}		
		return retorno;
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response removerProduto(@PathParam("id") int id){
		Response retorno = null;
		
		try {
			produtoDAO.removerProduto(id);
			retorno = Resposta.respostaSucesso();
		} catch (ProdutoException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}

}
