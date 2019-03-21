package br.com.alura.loja;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import junit.framework.Assert;

public class ClientTest {
	
	private static final String URL_BASE = "http://localhost:8080";
	
	private Client client;	
	private WebTarget target;
	private HttpServer server;
	
	@Before
	public void startServer(){	
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		this.client = ClientBuilder.newClient(config);
		this.server = Server.startServidor();
		this.target = this.client.target(URL_BASE);
	}
	
	@Test
	public void testaConexao(){	
		String conteudo = this.target.path("/carrinhos/xml/1").request().get(String.class);
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());		
	}	
	
	@Test
	public void testPostAndGet(){
		Carrinho carrinhoEnviado = new Carrinho("Rua do Teste Unitario","Nova Iguacu",2l);		
		String carrinhoXml = carrinhoEnviado.toJson();
		
		Entity<String> entity = Entity.entity(carrinhoXml, MediaType.APPLICATION_XML);
		Response responseTeste = this.target.path("/carrinhos/add").request().post(entity);
		Assert.assertEquals(201, responseTeste.getStatus());		
		
		String conteudo = this.target.path("/carrinhos/json/2").request().get(String.class);		
		Carrinho carrinhoRecuperado = new Gson().fromJson(conteudo, Carrinho.class);
		Assert.assertEquals(true, carrinhoRecuperado.equals(carrinhoEnviado));		
	}
	
	@After
	public void turnOffServer(){
		server.stop();
	}

}
