package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class CarrinhoResource {
	
	private CarrinhoDAO carrinhoDao = new CarrinhoDAO();
	
	@GET
	public Response listarCarrinhos(){
		String carrinhos = new Gson().toJson(this.carrinhoDao.getBanco().values().toArray(), Carrinho[].class);
		return Response.ok(carrinhos).build();
	}
	@GET
	@Path("/xml/{id}")
	public Response buscaXml(@PathParam("id") long id){
		Carrinho carrinho = this.carrinhoDao.busca(id);
		if(carrinho != null){
			return Response.ok(carrinho.toXml()).build();		
		}
		else{
			return Response.status(404).entity("<resposta> O id enviado nao existe. </resposta>").build();
		}
		
	}
	
	@GET
	@Path("/json/{id}")
	public Response buscaJson(@PathParam("id") long id){
		Carrinho carrinho = this.carrinhoDao.busca(id);
		if(carrinho != null){
			return Response.ok(carrinho.toJson()).build();		
		}
		else{
			return Response.status(404).entity("{ \"resposta\": \"O id enviado nao existe.\" }").build();
		}
	}
	
	@POST
	@Path("/add")
	public Response adicionarCarrinho(String novoCarrinho){
		Carrinho carrinho = new Gson().fromJson(novoCarrinho, Carrinho.class);
		carrinhoDao.adiciona(carrinho);
		URI uri = URI.create(("/carrinhos/") + carrinho.getId());
		return Response.created(uri).entity("Carrinho adicionado com sucesso.").build();
	}
	
	@PUT
	@Path("/update/{id}")
	public Response atualizarCarrinho(@PathParam("id")long id, String carrinhoAtualizado){
		Carrinho carrinho = new Gson().fromJson(carrinhoAtualizado, Carrinho.class);
		if(carrinhoDao.atualiza(id, carrinho) != null){
			return Response.ok("Carrinho modificado com sucesso.").build();
		}
		else{
			return Response.status(404).entity("O id enviado nao existe.").build();
		}	
	}
	
	@PUT
	@Path("/del/{id}/produtos/{produtoId}")
	public Response atualizarProduto(String novoProdutoJson, @PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = this.carrinhoDao.busca(id);
		Produto novoProduto = new Gson().fromJson(novoProdutoJson, Produto.class);
		if(carrinho.troca(novoProduto)){
			this.carrinhoDao.atualiza(id,carrinho);
			return Response.ok("Produto do carrinho atualizado com sucesso.").build();
		}
		else{
			return Response.status(404).entity("Produto nao encontrado no carrinho.").build();
		}		
	}
	
	@DELETE
	@Path("/del/{id}")
	public Response removeCarrinho(@PathParam("id") long id){
		Carrinho carrinho = this.carrinhoDao.busca(id);
		if(this.carrinhoDao.remove(carrinho.getId()) != null){
			return Response.ok("Carrinho removido com sucesso.").build();
		}
		else{
			return Response.status(404).entity("O id enviado nao existe.").build();
		}	
	}
	
	@DELETE
	@Path("/del/{id}/produtos/{produtoId}")
	public Response removeCarrinho(@PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = this.carrinhoDao.busca(id);
		if(carrinho.remove(produtoId)){
			this.carrinhoDao.atualiza(id,carrinho);
			return Response.ok("Produto removido do carrinho com sucesso.").build();
		}
		else{
			return Response.status(404).entity("Produto nao encontrado no carrinho.").build();
		}	
	}	
}
