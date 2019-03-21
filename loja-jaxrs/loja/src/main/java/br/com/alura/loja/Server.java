package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {
	
	public static void main(String[] args) throws IOException {
		HttpServer server = Server.startServidor();
		System.out.println("Tudo ok...");
		System.in.read();
		server.stop();		
	}
	
	public static HttpServer startServidor(){
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI serverUri = URI.create("http://localhost:8080/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(serverUri,config);		
		return server;
	}
}
