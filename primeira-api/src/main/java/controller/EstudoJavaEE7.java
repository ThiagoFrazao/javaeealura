package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import utils.ConexaoHTTP;
import utils.Resposta;

@Path("/java")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudoJavaEE7 {
	
	@GET
	public Response sucesso(){
		String msg = "Servidor funcionando corretamente";
		return Resposta.respostaSucesso(msg,"GET");
	}
	
	@GET
	@Path("/teste")
	public Response testando(){
		String recurso = "produto/listar";	
		
		return ConexaoHTTP.get(recurso);
	}
	
	

}
