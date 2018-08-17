package utils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class Resposta {
	
	private Status status;
	private String mensagem;

	
	public Resposta(){
		
	}
	
	public Resposta(String msg, Status st){
		this.mensagem = msg;
		this.status = st;
	}
	
	public static Response montarResposta(String msg){
		String mensagem = formatarResposta(msg);
		return Response.ok(mensagem).build();
	}
	
	public static Response respostaSucesso(String msg, String metodo){
		String mensagem = msg;
		if(metodo.equals("GET")){
			mensagem = formatarResposta(msg);
		}
		
		return Response.status(Status.OK).type(MediaType.APPLICATION_JSON).entity(mensagem).build();		
	}
	
	public static Response respostaErro(){
		String mensagem = "Ocorreu um erro ou serviço requisitado não existe.";
		mensagem = formatarResposta(mensagem);
		return Response.status(Status.BAD_REQUEST).entity(mensagem).build();
	}
	
	private static String formatarResposta(String msg){
		
		String resposta = "{ \"resposta\": \"" + msg + "\" }";
		
		return resposta;
	}
	
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	


}
