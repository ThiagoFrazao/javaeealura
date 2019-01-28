package lojas.utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import javaee.loja.models.Pagamento;

public class ClientUtils {
	
	private static final String ROOT_PATH = "http://localhost:8080/rest-endpoint/endpoint/";
	
	
	public static String pagarCompra(Pagamento pagamento){
		
		Client client = ClientBuilder.newClient();
		Entity<Pagamento> json = Entity.json(pagamento);
		WebTarget target =  client.target(ROOT_PATH + "alura/pagamento");
		Builder request = target.request();
		return request.post(json,String.class);		
	}
}
