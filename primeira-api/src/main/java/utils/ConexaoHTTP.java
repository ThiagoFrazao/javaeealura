package utils;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import erros.RecursoInvalidoException;


public class ConexaoHTTP {
	
	private final static String PATH = "http://localhost:8080/segunda-app/rest/loja/";	
	
	
	@SuppressWarnings("finally")
	public static Response get(String recurso){
		
		String caminho = PATH + recurso;	
		// Criando cliente para acessar recurso
		HttpClient cliente = HttpClients.createDefault();
		
		// Criando recurso tipo GET
		HttpGet get = new HttpGet(caminho);
		Response retorno = null;
		
		HttpResponse response = null;
		HttpEntity entidade = null;
	
		
		try {
			// Executando recurso criado atraves do cliente criado
			response = cliente.execute(get);
			
			// Recuperando resposta da requisicao
			entidade= response.getEntity();
			if(entidade != null){
				retorno = Response.status(Status.OK).entity(EntityUtils.toString(entidade)).build();
				
			}
			else{
				throw new RecursoInvalidoException();
			}

			
		} catch (ClientProtocolException e) {
			System.out.println("Erro protocolo de cliente " + e.getMessage());
			
			/*retorno.setMensagem("Ocorreu um erro inesperado.");
			retorno.setStatus(Status.BAD_REQUEST);*/
			
		} catch (IOException e) {
			System.out.println("Erro de I/O " + e.getMessage());
		/*	
			retorno.setMensagem("Ocorreu um erro inesperado.");
			retorno.setStatus(Status.BAD_REQUEST);*/
			
		} catch (RecursoInvalidoException e) {
			System.out.println(e.getMessage());
			/*
			retorno.setMensagem(e.getMessage());
			retorno.setStatus(Status.BAD_REQUEST);	*/
		} finally{
			return retorno;
		}
		
	}
	
	@SuppressWarnings("finally")
	public static Resposta post(String recurso, Object conteudo){
		
		// para escrever o Conteudo como JSON
		ObjectMapper mapper = new ObjectMapper();
		
		String caminho = PATH + recurso;
		HttpPost post = new HttpPost(caminho);
		HttpClient cliente = HttpClients.createDefault();
		Resposta retorno = new Resposta();
		
		HttpEntity entidade = null;
		StringEntity strEntidade = null;
		
		try {

			strEntidade = new StringEntity(mapper.writeValueAsString(conteudo),ContentType.APPLICATION_JSON);
			
			post.setEntity(strEntidade);		
			post.addHeader("Content-Type", "application/json");
			HttpResponse response = cliente.execute(post);
			entidade = response.getEntity();
			
			// Coloca conteudo na Resposta
			retorno.setMensagem(EntityUtils.toString(entidade));
			
			retorno.setStatus(Status.OK);
			
		} catch (ClientProtocolException e) {
			System.out.println("Classe " + ConexaoHTTP.class.getName());
			System.out.println("ClientProtocolException " + e.getMessage());
			
			retorno.setMensagem("Ocorreu um erro inesperado");
			retorno.setStatus(Status.BAD_REQUEST);
			
		} catch (UnsupportedCharsetException e1) {
			System.out.println("Classe " + ConexaoHTTP.class.getName());
			System.out.println("UnsupportedCharsetException " + e1.getMessage());
			
			retorno.setMensagem("Ocorreu um erro inesperado");
			retorno.setStatus(Status.BAD_REQUEST);		
			
		} catch (JsonProcessingException e1) {
			System.out.println("Classe " + ConexaoHTTP.class.getName());
			System.out.println("JsonProcessingException " + e1.getMessage());
			
			retorno.setMensagem("Ocorreu um erro inesperado");
			retorno.setStatus(Status.BAD_REQUEST);
			
		}catch (IOException e) {
			System.out.println("Classe " + ConexaoHTTP.class.getName());
			System.out.println("IOException " + e.getMessage());
			
			retorno.setMensagem("Ocorreu um erro inesperado");
			retorno.setStatus(Status.BAD_REQUEST);
		} finally{
			return retorno;
		}	
		
	}
	
}
