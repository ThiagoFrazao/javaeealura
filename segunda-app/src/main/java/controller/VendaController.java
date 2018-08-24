package controller;

import java.util.ArrayList;
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

import dao.VendaDAO;
import entidades.Cliente;
import entidades.Venda;
import erros.ClienteException;
import erros.VendaException;
import utils.Resposta;

@Path("/loja/vendas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VendaController {
	
	@Inject
	private VendaDAO vendaDAO;
	
	@GET
	public Response testeFuncionando(){		
		return Resposta.respostaSucesso("Servidor funcionando corretamente.");		
	}
	
	@GET
	@Path("/listar")
	public Response listarTodasVendas(){
		Response retorno = null;
		
		try {
			ArrayList<Venda> vendas = vendaDAO.listarVendas();
			retorno = Resposta.montarResposta(Status.OK, vendas);
		} catch (ClienteException | VendaException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	
	@GET
	@Path("/find/{id}")
	public Response procurarVenda(@PathParam("id") int id){
		Response retorno = null;
		List<Venda> vendas;
		
		try {
			vendas = vendaDAO.procurarVendasCliente(id);
			retorno = Resposta.montarResposta(Status.OK, vendas);			
		} catch (VendaException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		return retorno;
	}
	
	@GET
	@Path("/find")
	public Response procurarVendasCliente(@QueryParam("primeiroNome") String primeiroNome, @QueryParam("segundoNome") String segundoNome){
		Response retorno = null;
		List<Venda> vendas;
		
		try {
			vendas = vendaDAO.procurarVendasCliente(primeiroNome, segundoNome);
			retorno = Resposta.montarResposta(Status.OK, vendas);
		} catch (ClienteException | VendaException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;		
	}
	
	@GET
	@Path("/total/{id}")
	public Response calcularTotalVenda(@PathParam("id") int id){
		Response retorno = null;
		float totalVendas;
		
		try {
			totalVendas = vendaDAO.totalVendasCliente(id);
			String msg = "O total do custo de vendas do ID " + id  +  " é R$ " + totalVendas + " .";
			retorno = Resposta.respostaSucesso(msg);
			
			
		} catch (VendaException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@GET
	@Path("/total")
	public Response calcularTotalVendaCliente(@QueryParam("primeiroNome") String primeiroNome, @QueryParam("segundoNome") String segundoNome){
		Response retorno = null;
		float totalVendas;
		
		try {
			totalVendas = vendaDAO.totalVendasCliente(primeiroNome, segundoNome);
			String msg = "O total do custo de vendas do Cliente "+ primeiroNome + " " + segundoNome + " é R$ " + totalVendas + " .";
			retorno = Resposta.respostaSucesso(msg);
		} catch (ClienteException | VendaException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;		
	}
	
	@POST
	@Path("/add")
	public Response cadastrarNovaVenda(Venda novaVenda){
		Response retorno = null;
		
		if(novaVenda.getTotal() >= 0){
			try {
				vendaDAO.adicionarVenda(novaVenda);
				retorno = Resposta.respostaSucesso();
			} catch (VendaException | ClienteException e) {
				retorno = Resposta.respostaErro(e.getMessage());
			}
		}		
		return retorno;
	}
	
	@DELETE
	@Path("/delete/{id}")
	public Response deletarVenda(@PathParam("id") int idVenda){
		Response retorno = null;
		
		try {
			vendaDAO.deletarVenda(idVenda);
			retorno = Resposta.respostaSucesso();
		} catch (VendaException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}
		
		return retorno;
	}
	
	@DELETE
	@Path("/delete")
	public Response deletarVendaCliente(Cliente cliente){
		Response retorno = null;
		try {			
				if(StringUtils.isNotBlank(cliente.getPrimeiroNome()) && StringUtils.isNotBlank(cliente.getSegundoNome())){
					vendaDAO.deletarVendasCliente(cliente.getPrimeiroNome(), cliente.getSegundoNome());
					retorno = Resposta.respostaSucesso();
				}
				else if(cliente.getId() >= 0){
					vendaDAO.deletarVendasCliente(cliente.getId());
					retorno = Resposta.respostaSucesso();
				}			
				else{
					String msg = "Informações insuficientes para deletar vendas.";
					retorno = Resposta.respostaErro(msg);
				}
		}catch (VendaException | ClienteException e) {
			retorno = Resposta.respostaErro(e.getMessage());
		}		
		
		return retorno;
	}

}
