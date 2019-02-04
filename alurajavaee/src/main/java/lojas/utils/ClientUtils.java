package lojas.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import javaee.loja.models.Compra;
import javaee.loja.models.Pagamento;

@ApplicationScoped
public class ClientUtils {
	
	private final String ROOT_PATH = "http://localhost:8080/rest-endpoint/endpoint/";
	private static ExecutorService executor = Executors.newFixedThreadPool(20);
	
	@Resource(mappedName="java:/jboss/mail/gmail")
	private Session sessionMail;
	
	public String pagarCompra(float valor){		
		Client client = ClientBuilder.newClient();
		Pagamento pagamento = new Pagamento(valor);
		Entity<Pagamento> json = Entity.json(pagamento);
		WebTarget target =  client.target(ROOT_PATH + "alura/pagamento");
		Builder request = target.request();
		return request.post(json,String.class);		
	}
	
	public void createEmail(Compra compra){		
		String to = "compra@alura.com.br";
		String from = compra.getUsuarioCompra().getEmail();
		String titulo = "Nova compra na Loja";
		String conteudo = "Sua compra foi realizada com sucesso. O código da sua compra é " + compra.getUuId();
		
		MimeMessage email = new MimeMessage(sessionMail);		
		
		try {
			email.setFrom( new InternetAddress(from));
			email.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			email.setSubject(titulo);
			email.setContent(conteudo, "text/html");		
			//Transport.send(email);			
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	

}
