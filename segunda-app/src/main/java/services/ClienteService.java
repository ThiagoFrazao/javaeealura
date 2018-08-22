package services;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import dao.ClienteDAO;
import entidades.Cliente;
import erros.ClienteException;

@Dependent
public class ClienteService {
	
	@Inject
	private ClienteDAO clientes;
	
	public int adicionarNovoCliente(Cliente novoCliente) throws ClienteException{
		int retorno = -1;
		
		if(!clientes.verificarExistenciaCliente(novoCliente.getPrimeiroNome(), novoCliente.getSegundoNome())){
			retorno = clientes.adicionarCliente(novoCliente);
		}else{
			String msg = "Cliente "+ novoCliente.getPrimeiroNome()+" " + novoCliente.getSegundoNome() + " já cadastrado.";
			throw new ClienteException(msg);
		}		
		return retorno;
	}
	
	public List<Cliente> recuperarTodosClientes(){		
		return clientes.listarClientes();
	}
	
	public Cliente recuperarCliente(String primeiroNome, String segundoNome) throws ClienteException{
		return clientes.procurarCliente(primeiroNome, segundoNome);		
	}
		


}
