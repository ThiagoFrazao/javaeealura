package endpoints.javaEEAlura;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import static assincronous.GerarThread.run;

import models.Pagamento;

@Path("/alura")
public class JavaEEAluraEndpoint {
	
	@POST
	@Path("/pagamento")
	public void pagamentoPoint(Pagamento pagamento, @Suspended AsyncResponse response){			
		run(() ->{
			System.out.println("Recebi um pagamento de :" + pagamento.getValor());
			response.resume("Pagamento de R$ "+ pagamento.getValor() +" realizado com sucesso");			
		});		
	}
}