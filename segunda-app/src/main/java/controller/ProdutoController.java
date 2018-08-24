package controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ProdutoDAO;
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
	

}
