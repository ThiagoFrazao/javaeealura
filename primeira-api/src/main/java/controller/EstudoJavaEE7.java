package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import utils.Resposta;

@Path("/estudo/javaee7")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstudoJavaEE7 {
	
	@GET
	public Response sucesso(){
		String msg = "Servidor funcionando corretamente";
		return Resposta.respostaSucesso(msg,"GET");
	}
	
	
	@POST
	@Path("/teste")
	public Response teste(String msg){
		return Resposta.respostaSucesso(msg,"POST");		
	}
	

}
