package controller;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

import dao.ClienteDAO;
import entidades.Cliente;
import erros.ClienteException;
import utils.Resposta;


@Path("/loja/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@GET
	public Response testeFuncionando(){		
		return Resposta.respostaSucesso("Servidor funcionando corretamente.");		
	}
	
	@GET
	@Path("/listar")
	public Response listarTodosClientes(){
		Response retorno = null;
		List<Cliente> clientes = null;
		try {
			clientes = clienteDAO.listarClientes();
			retorno = Resposta.montarResposta(Status.OK, clientes);
		} catch (ClienteException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		
		return retorno;
	}
	
	@GET
	@Path("/find/{id}")
	public Response recuperarCliente(@PathParam("id") int id){
		Response retorno = null;
		
		try {
			Cliente cliente = clienteDAO.procurarCliente(id);
			retorno = Resposta.montarResposta(Status.OK, cliente);
		} catch (ClienteException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;		
	}
	
	@GET
	@Path("/find")
	public Response recuperarCliente1(@QueryParam("primeiroNome") String primeiroNome, @QueryParam("segundoNome") String segundoNome){
		Response retorno = null;
		
		try {
			Cliente cliente = clienteDAO.procurarCliente(primeiroNome,segundoNome);
			System.out.println("segundo nome" + segundoNome);
			retorno = Resposta.montarResposta(Status.OK, cliente);
		} catch (ClienteException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;		
	}
	
	@POST
	@Path("/add")
	public Response adicionarCliente(Cliente novoCliente){
		Response retorno = null;
		
		if(novoCliente.verificarValidade()){			
			if(!clienteDAO.verificarExistenciaCliente(novoCliente.getPrimeiroNome(), novoCliente.getSegundoNome())){
				try {
					clienteDAO.adicionarCliente(novoCliente);
				} catch (ClienteException e) {
					retorno = Resposta.respostaErro(e.getMessage());
				}
				retorno = Resposta.respostaSucesso();
			}else{
				String msg = "Cliente "+ novoCliente.getPrimeiroNome()+ " " + novoCliente.getSegundoNome() + " já cadastrado.";
				retorno = Resposta.respostaErro(msg);
			}	
		}else{
			String msg = "Informações insuficientes para registrar cliente.";
			retorno = Resposta.respostaErro(msg);
		}			
		return retorno;
	}
	
	@POST
	@Path("/update")
	public Response atualizarCliente(Cliente cliente){
		Response retorno = null;
		
		String primeiroNome = cliente.getPrimeiroNome();
		String segundoNome  = cliente.getSegundoNome();
		String novaRua = cliente.getEndereco();
		String novaCidade = cliente.getCidade();
		
		try {
			clienteDAO.atualizarCliente(primeiroNome, segundoNome, novaRua, novaCidade);
			retorno = Resposta.respostaSucesso();
		} catch (ClienteException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@DELETE
	@Path("/delete")
	public Response deletarCliente(Cliente cliente){
		Response retorno = null;
		
		try {
			if(StringUtils.isNotBlank(cliente.getPrimeiroNome()) && StringUtils.isNotBlank(cliente.getSegundoNome())){
				clienteDAO.deletarCliente(cliente.getPrimeiroNome(),cliente.getSegundoNome());
			}
			else if(Integer.valueOf(cliente.getId())!= null){
				clienteDAO.deletarCliente(cliente.getId());
			}
			else{
				retorno = Resposta.respostaErro();
			}
			
			retorno = Resposta.respostaSucesso();
		} catch (ClienteException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		
		return retorno;
	}
	
	
	
	
	


}
